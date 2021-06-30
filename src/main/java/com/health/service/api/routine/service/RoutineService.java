package com.health.service.api.routine.service;

import com.health.service.api.routine.model.command.model.RoutineDto;
import com.health.service.api.routine.model.command.request.CreateRoutineRequest;
import com.health.service.api.routine.model.command.request.UpdateRoutineRequest;

public interface RoutineService {

    Integer createRoutine(Integer userNum, CreateRoutineRequest request);

    void updateRoutine(Integer userNum, Integer routineId, UpdateRoutineRequest request);

    RoutineDto getRoutine(Integer routineId);
}
