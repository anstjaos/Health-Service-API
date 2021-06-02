package com.health.service.api.common.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class APIResponseHeader {
    private boolean isSuccessful;

    private int statusCode;

    private String message;

    public APIResponseHeader() {}

    @Builder
    public APIResponseHeader(boolean isSuccessful, int statusCode, String message) {
        this.isSuccessful = isSuccessful;
        this.statusCode = statusCode;
        this.message = message;
    }

    public static APIResponseHeader create(boolean isSuccessful, int statusCode, String message) {
        return APIResponseHeader.builder()
                .isSuccessful(isSuccessful)
                .statusCode(statusCode)
                .message(message)
                .build();
    }

    public static APIResponseHeader success() {
        return APIResponseHeader.create(true, HttpStatus.OK.value(), "success");
    }
}
