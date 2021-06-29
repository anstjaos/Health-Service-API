package com.health.service.api.routine.service;

import com.health.service.api.routine.model.command.request.CreateRoutineRequest;

public interface RoutineService {

    Integer createRoutine(Integer userNum, CreateRoutineRequest request);
}
