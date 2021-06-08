package com.health.service.api.common.exception;

import com.health.service.api.common.model.command.ResultCode;

public class TokenNotMatchException extends HealthServiceException {
    public TokenNotMatchException() { super("token is not valid", ResultCode.ACCESS_DENIED); }
}
