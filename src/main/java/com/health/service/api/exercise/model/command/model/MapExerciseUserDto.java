package com.health.service.api.exercise.model.command.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MapExerciseUserDto {

    private final Integer mapId;

    private final Integer exerciseId;

    private final Integer userNum;

    private final LocalDateTime date;

    private final Integer exerciseCount;

    private final Integer setCount;

    @Builder
    public MapExerciseUserDto(Integer mapId, Integer exerciseId, Integer userNum, LocalDateTime date, Integer exerciseCount, Integer setCount) {
        this.mapId = mapId;
        this.exerciseId = exerciseId;
        this.userNum = userNum;
        this.date = date;
        this.exerciseCount = exerciseCount;
        this.setCount = setCount;
    }
}
