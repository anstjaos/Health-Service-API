package com.health.service.api.common.exception;

import com.health.service.api.common.model.command.ResultCode;
import lombok.Getter;

@Getter
public class HealthServiceException extends RuntimeException {

    private final ResultCode resultCode;

    public HealthServiceException(String message, ResultCode resultCode) {
        super(message);
        this.resultCode = resultCode;
    }
}
