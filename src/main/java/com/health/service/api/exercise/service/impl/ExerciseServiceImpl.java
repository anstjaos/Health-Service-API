package com.health.service.api.exercise.service.impl;

import com.health.service.api.common.exception.ExerciseNotFoundException;
import com.health.service.api.exercise.entity.ExerciseEntity;
import com.health.service.api.exercise.model.command.model.ExerciseDto;
import com.health.service.api.exercise.model.command.request.ExerciseCreateRequest;
import com.health.service.api.exercise.model.command.request.ExerciseUpdateRequest;
import com.health.service.api.exercise.repository.ExerciseRepository;
import com.health.service.api.exercise.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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

    @Override
    @Transactional
    public void updateExercise(Integer exerciseId, ExerciseUpdateRequest request) {
        ExerciseEntity exerciseEntity = exerciseRepository.findById(exerciseId)
                .orElseThrow(ExerciseNotFoundException::new);

        Optional.ofNullable(request.getExerciseName())
                .ifPresent(exerciseEntity::setExerciseName);

        Optional.ofNullable(request.getExerciseTypeId())
                .ifPresent(exerciseEntity::setExerciseTypeId);

        Optional.ofNullable(request.getBodyPartId())
                .ifPresent(exerciseEntity::setBodyPartId);
    }

    @Override
    public ExerciseDto getExercise(Integer exerciseId) {
        ExerciseEntity exerciseEntity = exerciseRepository.findById(exerciseId)
                .orElseThrow(ExerciseNotFoundException::new);

        return ExerciseDto.builder()
                .exerciseId(exerciseEntity.getExerciseId())
                .exerciseName(exerciseEntity.getExerciseName())
                .exerciseTypeId(exerciseEntity.getExerciseTypeId())
                .bodyPartId(exerciseEntity.getBodyPartId())
                .build();
    }

    @Override
    @Transactional
    public void deleteExercise(Integer exerciseId) {
        ExerciseEntity exerciseEntity = exerciseRepository.findById(exerciseId)
                .orElseThrow(ExerciseNotFoundException::new);

        exerciseRepository.delete(exerciseEntity);
    }
}
