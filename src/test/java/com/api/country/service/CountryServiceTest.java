package com.api.country.service;

import com.api.country.CountryFinderApplication;
import me.darksidecode.kantanj.formatting.CommonJson;
import me.darksidecode.kantanj.ipapi.IPAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CountryFinderApplication.class)
@SpringBootTest
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    public void testIPApiWithValidIP() throws ExecutionException, InterruptedException {
        IPAddress ipAddress = CommonJson.fromJson("{\n" +
                "  \"query\": \"8.8.8.8\",\n" +
                "  \"status\": \"success\",\n" +
                "  \"country\": \"United States\",\n" +
                "  \"countryCode\": \"US\",\n" +
                "  \"region\": \"VA\",\n" +
                "  \"regionName\": \"Virginia\",\n" +
                "  \"city\": \"Ashburn\",\n" +
                "  \"zip\": \"20149\",\n" +
                "  \"lat\": 39.03,\n" +
                "  \"lon\": -77.5,\n" +
                "  \"timezone\": \"America/New_York\",\n" +
                "  \"isp\": \"Google LLC\",\n" +
                "  \"org\": \"Google Public DNS\",\n" +
                "  \"as\": \"AS15169 Google LLC\"\n" +
                "}", IPAddress.class);


        CompletableFuture<IPAddress> response = countryService.callIPDetailsApi("8.8.8.8");

        assertEquals(response.get().getCountry(), ipAddress.getCountry());
    }

    @Test
    public void testIPApiWithInValidIP() throws ExecutionException, InterruptedException {

        CompletableFuture<IPAddress> response = countryService.callIPDetailsApi("8.8.8");

        assertNull(response.get().getCountry());
    }
}

