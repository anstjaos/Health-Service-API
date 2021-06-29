package com.health.service.api.routine.exception;

import com.health.service.api.common.exception.HealthServiceException;
import com.health.service.api.common.model.command.ResultCode;

public class CreateRoutineRequestException extends HealthServiceException {
    public CreateRoutineRequestException(String message) { super(message, ResultCode.BAD_REQUEST); }
}
