package com.health.service.api.exercise.exception;

import com.health.service.api.common.exception.HealthServiceException;
import com.health.service.api.common.model.command.ResultCode;

public class MapExerciseUserNotFoundException extends HealthServiceException {
    public MapExerciseUserNotFoundException() { super("map id is not found", ResultCode.NOT_FOUND); }
}
