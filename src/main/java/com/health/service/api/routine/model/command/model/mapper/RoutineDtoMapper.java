package com.health.service.api.routine.model.command.model.mapper;

import com.health.service.api.routine.entity.ExerciseRoutineEntity;
import com.health.service.api.routine.model.command.model.RoutineDto;

import java.util.List;
import java.util.stream.Collectors;

public class RoutineDtoMapper {

    public static RoutineDto convert(ExerciseRoutineEntity routineEntity) {
        return RoutineDto.builder()
                .routineId(routineEntity.getRoutineId())
                .userNum(routineEntity.getUserNum())
                .routineName(routineEntity.getRoutineName())
                .dayOfWeek(routineEntity.getDayOfWeek())
                .build();
    }

    public static List<RoutineDto> convert(List<ExerciseRoutineEntity> routineEntityList) {
        return routineEntityList.stream().map(RoutineDtoMapper::convert).collect(Collectors.toList());
    }
}
