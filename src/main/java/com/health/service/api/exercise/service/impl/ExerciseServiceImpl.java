package com.health.service.api.exercise.service.impl;

import com.health.service.api.exercise.entity.ExerciseEntity;
import com.health.service.api.exercise.model.command.request.ExerciseCreateRequest;
import com.health.service.api.exercise.repository.ExerciseRepository;
import com.health.service.api.exercise.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    @Transactional
    public Integer createExercise(ExerciseCreateRequest request) {
        ExerciseEntity exerciseEntity = new ExerciseEntity();
        exerciseEntity.setExerciseName(request.getExerciseName());
        exerciseEntity.setExerciseTypeId(request.getExerciseTypeId());
        exerciseEntity.setBodyPartId(request.getBodyPardId());

        exerciseEntity = exerciseRepository.save(exerciseEntity);
        return exerciseEntity.getExerciseId();
    }
}
