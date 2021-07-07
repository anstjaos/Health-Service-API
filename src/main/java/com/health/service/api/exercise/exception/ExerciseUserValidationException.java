package com.health.service.api.exercise.exception;

import com.health.service.api.common.exception.HealthServiceException;
import com.health.service.api.common.model.command.ResultCode;

public class ExerciseUserValidationException extends HealthServiceException {

    public ExerciseUserValidationException() { super("user exercise request validation fault", ResultCode.BAD_REQUEST); }
}
