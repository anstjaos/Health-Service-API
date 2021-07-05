package com.health.service.api.exercise.exception;

import com.health.service.api.common.exception.HealthServiceException;
import com.health.service.api.common.model.command.ResultCode;

public class ExerciseUserCreateValidationException extends HealthServiceException {

    public ExerciseUserCreateValidationException() { super("create user exercise request validation fault", ResultCode.BAD_REQUEST); }
}
