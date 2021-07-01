package com.health.service.api.routine.service;

import com.health.service.api.routine.model.command.model.RoutineDto;
import com.health.service.api.routine.model.command.request.CreateRoutineRequest;
import com.health.service.api.routine.model.command.request.UpdateRoutineRequest;

import java.util.List;

public interface RoutineService {

    Integer createRoutine(Integer userNum, CreateRoutineRequest request);

    void updateRoutine(Integer userNum, Integer routineId, UpdateRoutineRequest request);

    RoutineDto getRoutine(Integer userNum, Integer routineId);

    void deleteRoutine(Integer userNum, Integer routineId);

    List<RoutineDto> getRoutineList(Integer userNum);
}
