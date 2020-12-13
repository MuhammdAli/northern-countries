package com.api.country.service.impl;

import com.api.country.CountryFinderApplication;
import com.api.country.dto.response.CountryResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CountryFinderApplication.class)
@SpringBootTest
public class AggregatorServiceTest {

    @Autowired
    private AggregatorServiceImpl service;

    @Test
    public void testAggregateValidIPs() {
        CountryResponse response = service.aggregate(Arrays.asList("8.8.8.8", "180.0.0.0"));
        assertEquals(2, response.getNorthcountries().size());
    }

    @Test
    public void testAggregateInvalidIPs() {
        CountryResponse response = service.aggregate(Arrays.asList("8.8.8", "some"));
        assertEquals(0, response.getNorthcountries().size());
    }

    @Test
    public void testAggregateMixValidAndInvalidIPs() {
        CountryResponse response = service.aggregate(Arrays.asList("8.8.8.8", "some"));
        assertEquals(1, response.getNorthcountries().size());
    }
}
