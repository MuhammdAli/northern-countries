package com.api.country.util;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class PredicatesTest implements CommonUtils.Predicates {

    @Test
    public void testIsNortherCountry() {
        BigDecimal latitude = null;
        latitude = BigDecimal.ONE;
        assertTrue(CHECKIFLATITUDEINNORTHERN.test(latitude));
        latitude = BigDecimal.ZERO;
        assertTrue(CHECKIFLATITUDEINNORTHERN.test(latitude));
        latitude = new BigDecimal("90");
        assertTrue(CHECKIFLATITUDEINNORTHERN.test(latitude));
        latitude = new BigDecimal("37.38600"); // For US
        assertTrue(CHECKIFLATITUDEINNORTHERN.test(latitude));
        latitude = new BigDecimal("-1");
        assertTrue(!CHECKIFLATITUDEINNORTHERN.test(latitude));
        latitude = new BigDecimal("-1333");
        assertTrue(!CHECKIFLATITUDEINNORTHERN.test(latitude));
        latitude = new BigDecimal("90.1");
        assertTrue(!CHECKIFLATITUDEINNORTHERN.test(latitude));
        latitude = new BigDecimal("100");
        assertTrue(!CHECKIFLATITUDEINNORTHERN.test(latitude));
        latitude = new BigDecimal("10000000");
        assertTrue(!CHECKIFLATITUDEINNORTHERN.test(latitude));
        latitude = new BigDecimal(
                "100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        assertTrue(!CHECKIFLATITUDEINNORTHERN.test(latitude));

    }

}
