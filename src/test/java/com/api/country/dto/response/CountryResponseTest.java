package com.api.country.dto.response;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Ignore
public class CountryResponseTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void checkFormat() {
        List<String> northCountries = new ArrayList<>();
        northCountries.add("UAE");
        northCountries.add("Colombia");
        System.out.println(new Gson().toJson(CountryResponse.builder().northcountries(northCountries).build()));
    }
}
