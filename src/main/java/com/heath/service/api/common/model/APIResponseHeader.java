package com.heath.service.api.common.model;

import lombok.Builder;
import lombok.Getter;

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
}
