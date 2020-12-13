package com.api.country.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Builder
@AllArgsConstructor
@Getter
@Setter
class BaseResponse {
    private Date timestamp;
    private String message;
    private HttpStatus httpStatus;

}
