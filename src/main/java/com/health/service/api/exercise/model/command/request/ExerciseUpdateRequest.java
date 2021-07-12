package com.health.service.api.exercise.model.command.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseUpdateRequest {

    private String exerciseName;

    private Integer exerciseTypeId;

    private Integer bodyPartId;
}
