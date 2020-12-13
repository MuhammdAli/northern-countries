package com.api.country.service;

import me.darksidecode.kantanj.ipapi.IPAddress;

import java.util.concurrent.CompletableFuture;

public interface CountryService {

    CompletableFuture<IPAddress> callIPDetailsApi(final String ip);
}
