package com.health.service.api.exercise.service;

import com.health.service.api.exercise.model.command.model.MapExerciseUserDto;
import com.health.service.api.exercise.model.command.request.ExerciseUserCreateAndUpdateRequest;

import java.util.List;

public interface MapExerciseUserService {

    Integer createMapExerciseUser(Integer userNum, Integer exerciseId, ExerciseUserCreateAndUpdateRequest request);

    void updateMapExerciseUser(Integer userNum, Integer exerciseId, Integer mapId, ExerciseUserCreateAndUpdateRequest request);

    MapExerciseUserDto getMapExerciseUser(Integer userNum, Integer exerciseId, Integer mapId);

    List<MapExerciseUserDto> getMapExerciseUserList(Integer userNum);

    void deleteMapExerciseUser(Integer userNum, Integer exerciseId, Integer mapId);
}
