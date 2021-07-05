package com.health.service.api.exercise.service;

import com.health.service.api.exercise.model.command.request.ExerciseUserCreateRequest;

public interface MapExerciseUserService {

    void createMapUserExercise(Integer userNum, Integer exerciseId, ExerciseUserCreateRequest request);
}
