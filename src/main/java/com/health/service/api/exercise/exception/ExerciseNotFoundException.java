package com.health.service.api.exercise.exception;

import com.health.service.api.common.exception.HealthServiceException;
import com.health.service.api.common.model.command.ResultCode;

public class ExerciseNotFoundException extends HealthServiceException {
    public ExerciseNotFoundException() { super("not found exercise", ResultCode.NOT_FOUND); }
}
