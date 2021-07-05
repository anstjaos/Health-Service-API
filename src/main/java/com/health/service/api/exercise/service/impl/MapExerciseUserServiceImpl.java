package com.health.service.api.exercise.service.impl;

import com.health.service.api.exercise.entity.MapExerciseUserEntity;
import com.health.service.api.exercise.exception.ExerciseUserCreateValidationException;
import com.health.service.api.exercise.model.command.request.ExerciseUserCreateRequest;
import com.health.service.api.exercise.repository.MapExerciseUserRepository;
import com.health.service.api.exercise.service.ExerciseService;
import com.health.service.api.exercise.service.MapExerciseUserService;
import com.health.service.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MapExerciseUserServiceImpl implements MapExerciseUserService {

    private final UserService userService;
    private final ExerciseService exerciseService;
    private final MapExerciseUserRepository mapExerciseUserRepository;

    @Autowired
    public MapExerciseUserServiceImpl(UserService userService,
                                      ExerciseService exerciseService,
                                      MapExerciseUserRepository mapExerciseUserRepository) {
        this.userService = userService;
        this.exerciseService = exerciseService;
        this.mapExerciseUserRepository = mapExerciseUserRepository;
    }

    @Override
    public void createMapUserExercise(Integer userNum, Integer exerciseId, ExerciseUserCreateRequest request) {
        // exist validation
        userService.getUser(userNum);
        exerciseService.getExercise(exerciseId);

        requestValidation(request);

        MapExerciseUserEntity mapExerciseUserEntity = new MapExerciseUserEntity();
        mapExerciseUserEntity.setUserNum(userNum);
        mapExerciseUserEntity.setExerciseId(exerciseId);
        mapExerciseUserEntity.setDate(request.getDate());
        mapExerciseUserEntity.setExerciseCount(request.getExerciseCount());
        mapExerciseUserEntity.setSetCount(request.getSetCount());

        mapExerciseUserRepository.save(mapExerciseUserEntity);
    }

    private void requestValidation(ExerciseUserCreateRequest request) {
        Optional.ofNullable(request.getExerciseCount())
                .ifPresent(exerciseCount -> {
                    if (exerciseCount < 0) throw new ExerciseUserCreateValidationException();
                });

        Optional.ofNullable(request.getSetCount())
                .ifPresent(setCount -> {
                    if (setCount < 0) throw new ExerciseUserCreateValidationException();
                });
    }
}
