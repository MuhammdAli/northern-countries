package com.api.country.controller;

import com.api.country.CountryFinderApplication;
import com.api.country.util.CommonUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CountryFinderApplication.class)
@SpringBootTest
public class CountryControllerTest implements CommonUtils {
    private static final Logger logger = LoggerFactory.getLogger(CountryControllerTest.class);

    private MockMvc mockMvc;

    private static String baseURL = Params.NORTH_COUNTRIES;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetCountryWithNullArgs() throws Exception {
        String url = baseURL + "?null";

        logger.info("URL {}", url);
        mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andDo(print());

    }

    @Test
    public void testGetCountryEmptyArgs() throws Exception {
        String url = baseURL + "?ip=";
        logger.info("URL {}", url);
        mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    public void testGetCountryValidIP() throws Exception {
        String url = baseURL + "?ip=8.8.8.8";

        logger.info("URL {}", url);
        mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void testGetCountryMoreThan5IP() throws Exception {
        String url = baseURL + "?ip=8.8.8.8&ip=8.8.8.8&ip=8.8.8.8&ip=8.8.8.8&ip=8.8.8.8&ip=8.8.8.8&ip=8.8.8.8";
        logger.info("URL {}", url);
        mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andDo(print());

    }

    @Test
    public void testGetCountryMoreThanOneIPAndOneInvalidIP() throws Exception {
        String url = baseURL + "?ip=8.8.8.8&ip=8.8.8.8&ip=@InvalidIP&ip=8.8.8.8&ip=8.8.8.8&ip=8.8.8.8&ip=8.8.8.8";
        logger.info("URL {}", url);
        mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andDo(print());

    }
}
