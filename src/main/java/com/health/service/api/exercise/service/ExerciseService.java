package com.health.service.api.exercise.service;

import com.health.service.api.exercise.model.command.model.ExerciseDto;
import com.health.service.api.exercise.model.command.request.ExerciseCreateRequest;
import com.health.service.api.exercise.model.command.request.ExerciseUpdateRequest;

public interface ExerciseService {

    Integer createExercise(ExerciseCreateRequest request);

    void updateExercise(Integer exerciseId, ExerciseUpdateRequest request);

    ExerciseDto getExercise(Integer exerciseId);
}
