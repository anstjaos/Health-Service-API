package com.health.service.api.exercise.repository;

import com.health.service.api.exercise.entity.MapExerciseUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MapExerciseUserRepository extends JpaRepository<MapExerciseUserEntity, Integer> {

    List<MapExerciseUserEntity> findAllByUserNum(Integer userNum);
}
