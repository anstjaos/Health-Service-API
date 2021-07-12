package com.health.service.api.exercise.model.command.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExerciseDto {

    private final Integer exerciseId;

    private final String exerciseName;

    private final Integer exerciseTypeId;

    private final Integer bodyPartId;

    @Builder
    public ExerciseDto(Integer exerciseId, String exerciseName, Integer exerciseTypeId, Integer bodyPartId) {
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.exerciseTypeId = exerciseTypeId;
        this.bodyPartId = bodyPartId;
    }
}
