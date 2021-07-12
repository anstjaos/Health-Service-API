package com.health.service.api.routine.exception;

import com.health.service.api.common.exception.HealthServiceException;
import com.health.service.api.common.model.command.ResultCode;

public class RoutineNotFoundException extends HealthServiceException {
    public RoutineNotFoundException() { super("routine not found!", ResultCode.NOT_FOUND); }
}
