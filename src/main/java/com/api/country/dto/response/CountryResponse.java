package com.api.country.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Collection;

@Builder
@Getter
public class CountryResponse implements Serializable {
    private static final long serialVersionUID = 6014256002050112228L;

    private Collection<String> northcountries;

}
