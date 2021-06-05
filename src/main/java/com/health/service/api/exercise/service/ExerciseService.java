package com.health.service.api.exercise.service;

import com.health.service.api.exercise.model.command.request.ExerciseCreateRequest;

public interface ExerciseService {

    Integer createExercise(ExerciseCreateRequest request);
}
