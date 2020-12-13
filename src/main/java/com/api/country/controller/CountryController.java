package com.api.country.controller;

import com.api.country.dto.response.CountryResponse;
import com.api.country.service.impl.AggregatorServiceImpl;
import com.api.country.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Validated
@RestController
@RequestMapping(CommonUtils.Params.BASE_PATH)
public class CountryController {
    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private AggregatorServiceImpl service;

    @GetMapping(CommonUtils.Params.NORTH_COUNTRIES)
    public CountryResponse get(
            @Valid
            @RequestParam(value = "ip")
            @Size(min = CommonUtils.Params.MIN_ALLOWED_IPS, max = CommonUtils.Params.MAX_ALLOWED_IPS, message = "Minimum: " + CommonUtils.Params.MIN_ALLOWED_IPS + " Maximum: " + CommonUtils.Params.MAX_ALLOWED_IPS)
                    List<String> ip) {
        logger.info("Total {} Request Received for IP's:{} ", ip.size(), String.join(",", ip));
        return service.aggregate(ip);
    }

}
