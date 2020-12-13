package com.api.country.service.impl;

import com.api.country.dto.response.CountryResponse;
import com.api.country.exception.ExtenalAPICallingException;
import com.api.country.exception.ServiceException;
import com.api.country.service.AggregatorService;
import com.api.country.service.CountryService;
import com.api.country.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import me.darksidecode.kantanj.ipapi.IPAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AggregatorServiceImpl implements AggregatorService {

    @Autowired
    private CountryService countryService;

    public CountryResponse aggregate(final List<String> ips) {
        try {

            return CountryResponse.builder()
                    .northcountries(filterByLatitudeAndSortByName(getLatitudeAndCountryMap(aggregateAllResponses(ips))))
                    .build();

        } catch (Exception e) {
            log.info(e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }

    }

    private Set<String> filterByLatitudeAndSortByName(final Map<BigDecimal, String> mapping) {

        return mapping.entrySet().stream().filter(es -> CommonUtils.Predicates.CHECKIFLATITUDEINNORTHERN.test(es.getKey()))
                .map(Map.Entry::getValue)
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private Map<BigDecimal, String> getLatitudeAndCountryMap(
            final List<CompletableFuture<IPAddress>> responseCompletableFuture) {

        final Map<BigDecimal, String> mapping = new HashMap<>();
        responseCompletableFuture.forEach(futureResponse -> {
            try {
                IPAddress response = futureResponse.get();
                if (response.getStatus().equalsIgnoreCase("success")) {
                    log.info("Got Valid Response: {}", response);
                    mapping.put(BigDecimal.valueOf(response.getLat()),
                            response.getCountry());
                } else
                    log.info("Request Failed:", response);

            } catch (InterruptedException | ExecutionException e) {
                log.error("Got Error {} Will Not Retry", e.getMessage());
                throw new ExtenalAPICallingException("Got Error Please try after some time: " + e.getCause(), e);
            }
        });
        return mapping;
    }

    private List<CompletableFuture<IPAddress>> aggregateAllResponses(final List<String> receivedIPAddress) {
        return receivedIPAddress.parallelStream().filter(CommonUtils.Predicates.VALID_IP_ADDRESS)
                .peek(ip -> log.info("Calling IP:{} ", ip)).map(ip -> countryService.callIPDetailsApi(ip)).collect(Collectors.toList());
    }

}
