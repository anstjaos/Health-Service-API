package com.health.service.api.routine.model.command.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RoutineDto {

    private final Integer routineId;

    private final Integer userNum;

    private final String routineName;

    private final Integer dayOfWeek;

    @Builder
    public RoutineDto(Integer routineId, Integer userNum, String routineName, Integer dayOfWeek) {
        this.routineId = routineId;
        this.userNum = userNum;
        this.routineName = routineName;
        this.dayOfWeek = dayOfWeek;
    }
}
