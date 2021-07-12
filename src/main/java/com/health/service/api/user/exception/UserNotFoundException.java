package com.health.service.api.user.exception;

import com.health.service.api.common.exception.HealthServiceException;
import com.health.service.api.common.model.command.ResultCode;

public class UserNotFoundException extends HealthServiceException {
    public UserNotFoundException() { super("not found user", ResultCode.NOT_FOUND); }
}
