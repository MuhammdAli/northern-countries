package com.api.country.service.impl;

import com.api.country.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import me.darksidecode.kantanj.ipapi.IPAPI;
import me.darksidecode.kantanj.ipapi.IPAddress;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class CountryServiceImpl implements CountryService {

    @Async
    public CompletableFuture<IPAddress> callIPDetailsApi(final String ip) {
        log.info("Calling Host: {}", IPAPI.BASE_URL);
        return CompletableFuture.completedFuture(IPAPI.info(ip));
    }

}
