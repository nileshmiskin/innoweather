package com.innoweather.platform.model;

import lombok.Value;

@Value
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;
}
