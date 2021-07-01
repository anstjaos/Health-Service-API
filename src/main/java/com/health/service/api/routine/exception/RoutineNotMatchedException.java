package com.health.service.api.routine.exception;

import com.health.service.api.common.exception.HealthServiceException;
import com.health.service.api.common.model.command.ResultCode;

public class RoutineNotMatchedException extends HealthServiceException {
    public RoutineNotMatchedException() { super("user num is not matched this routine", ResultCode.BAD_REQUEST); }
}
