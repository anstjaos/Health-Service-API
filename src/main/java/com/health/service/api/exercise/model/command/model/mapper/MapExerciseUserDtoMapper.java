package com.health.service.api.exercise.model.command.model.mapper;

import com.health.service.api.exercise.entity.MapExerciseUserEntity;
import com.health.service.api.exercise.model.command.model.MapExerciseUserDto;

public class MapExerciseUserDtoMapper {

    public static MapExerciseUserDto convert(MapExerciseUserEntity mapExerciseUserEntity) {
        return MapExerciseUserDto.builder()
                .mapId(mapExerciseUserEntity.getMapId())
                .exerciseId(mapExerciseUserEntity.getExerciseId())
                .userNum(mapExerciseUserEntity.getUserNum())
                .date(mapExerciseUserEntity.getDate())
                .exerciseCount(mapExerciseUserEntity.getExerciseCount())
                .setCount(mapExerciseUserEntity.getSetCount())
                .build();
    }
}
