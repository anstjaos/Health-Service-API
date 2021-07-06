package com.health.service.api.exercise.service;

import com.health.service.api.exercise.model.command.request.ExerciseUserCreateAndUpdateRequest;

public interface MapExerciseUserService {

    void createMapExerciseUser(Integer userNum, Integer exerciseId, ExerciseUserCreateAndUpdateRequest request);

    void updateMapExerciseUser(Integer userNum, Integer exerciseId, Integer mapId, ExerciseUserCreateAndUpdateRequest request);
}
