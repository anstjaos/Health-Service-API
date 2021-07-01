package com.health.service.api.routine.repository;

import com.health.service.api.routine.entity.ExerciseRoutineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRepository extends JpaRepository<ExerciseRoutineEntity, Integer> {
    List<ExerciseRoutineEntity> findAllByUserNum(Integer userNum);
}
