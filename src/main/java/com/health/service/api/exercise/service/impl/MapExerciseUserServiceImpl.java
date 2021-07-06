package com.health.service.api.exercise.service.impl;

import com.health.service.api.exercise.entity.MapExerciseUserEntity;
import com.health.service.api.exercise.exception.ExerciseUserValidationException;
import com.health.service.api.exercise.exception.MapExerciseUserNotFoundException;
import com.health.service.api.exercise.model.command.request.ExerciseUserCreateAndUpdateRequest;
import com.health.service.api.exercise.repository.MapExerciseUserRepository;
import com.health.service.api.exercise.service.ExerciseService;
import com.health.service.api.exercise.service.MapExerciseUserService;
import com.health.service.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional
    public void createMapExerciseUser(Integer userNum, Integer exerciseId, ExerciseUserCreateAndUpdateRequest request) {
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

    @Override
    @Transactional
    public void updateMapExerciseUser(Integer userNum, Integer exerciseId, Integer mapId, ExerciseUserCreateAndUpdateRequest request) {
        // exist validation
        userService.getUser(userNum);
        exerciseService.getExercise(exerciseId);

        requestValidation(request);

        MapExerciseUserEntity mapExerciseUserEntity = mapExerciseUserRepository.findById(mapId)
                .orElseThrow(MapExerciseUserNotFoundException::new);

        Optional.ofNullable(request.getDate())
                .ifPresent(mapExerciseUserEntity::setDate);

        Optional.ofNullable(request.getExerciseCount())
                .ifPresent(mapExerciseUserEntity::setExerciseCount);

        Optional.ofNullable(request.getSetCount())
                .ifPresent(mapExerciseUserEntity::setSetCount);
    }

    private void requestValidation(ExerciseUserCreateAndUpdateRequest request) {
        Optional.ofNullable(request.getExerciseCount())
                .ifPresent(exerciseCount -> {
                    if (exerciseCount < 0) throw new ExerciseUserValidationException();
                });

        Optional.ofNullable(request.getSetCount())
                .ifPresent(setCount -> {
                    if (setCount < 0) throw new ExerciseUserValidationException();
                });
    }
}
