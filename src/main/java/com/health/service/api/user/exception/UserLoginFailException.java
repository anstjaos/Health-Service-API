package com.health.service.api.user.exception;

import com.health.service.api.common.exception.HealthServiceException;
import com.health.service.api.common.model.command.ResultCode;

public class UserLoginFailException extends HealthServiceException {
    public UserLoginFailException() { super("login info is not valid", ResultCode.ACCESS_DENIED); }
}
