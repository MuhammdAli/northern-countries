package com.api.country.dto.response;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorResponse extends BaseResponse {

    public ErrorResponse(Date timestamp, String message, HttpStatus httpStatus) {
        super(timestamp, message, httpStatus);
    }

    public ErrorResponse(String message, HttpStatus httpStatus) {
        super(new Date(), message, httpStatus);
    }
}
