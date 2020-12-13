package com.api.country.util;

import org.apache.commons.validator.routines.InetAddressValidator;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Predicate;

public interface CommonUtils {

    interface Params {
        String API = "/api";
        String V1 = "/v1";
        String BASE_PATH = "/";//API + V1;
        String NORTH_COUNTRIES = "/northcountries";
        int MIN_ALLOWED_IPS = 1;
        int MAX_ALLOWED_IPS = 5;
    }

    interface Predicates {
        Predicate<Object> CHECK_NOT_NULL_RETURN_TRUE = Objects::nonNull;

        /**
         * Check on latitude value. The northern hemisphere has range from 0.0 to 90.0
         **/
        Predicate<BigDecimal> CHECKIFLATITUDEINNORTHERN = input -> (CHECK_NOT_NULL_RETURN_TRUE.test(input)
                && input.compareTo(BigDecimal.ZERO) >= 0 && input.compareTo(BigDecimal.valueOf(90)) <= 0);

        Predicate<String> VALID_IP_ADDRESS = input -> InetAddressValidator.getInstance().isValid(input);
    }

}
