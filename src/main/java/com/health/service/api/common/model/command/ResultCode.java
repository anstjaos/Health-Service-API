package com.health.service.api.common.model.command;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(200, true, "success"),
    BAD_REQUEST(400, false, "bad request"),
    NOT_FOUND(404, false, "not found");

    private Integer code;

    private Boolean isSuccess;

    private String message;

    ResultCode(Integer code, Boolean isSuccess, String message) {
        this.code = code;
        this.isSuccess = isSuccess;
        this.message = message;
    }
}
