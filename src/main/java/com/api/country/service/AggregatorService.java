package com.api.country.service;

import com.api.country.dto.response.CountryResponse;

import java.util.List;

public interface AggregatorService {

    CountryResponse aggregate(final List<String> ips);
}
