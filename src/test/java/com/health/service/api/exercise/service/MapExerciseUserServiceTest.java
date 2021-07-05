package com.health.service.api.exercise.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.exercise.exception.ExerciseNotFoundException;
import com.health.service.api.exercise.exception.ExerciseUserCreateValidationException;
import com.health.service.api.exercise.model.command.request.ExerciseUserCreateRequest;
import com.health.service.api.user.exception.UserNotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@DatabaseSetup(value = {
        "/database/exercise.xml",
        "/database/service_user.xml",
        "/database/map_exercise_user.xml"
})
public class MapExerciseUserServiceTest extends DbUnitTestContext {

    @Autowired
    private MapExerciseUserService mapExerciseUserService;

    @Test
    public void success_create_user_exercise() {
        // given
        Integer userNum = 1;
        Integer exerciseId = 1;
        LocalDateTime now = LocalDateTime.now();
        ExerciseUserCreateRequest request = new ExerciseUserCreateRequest();
        request.setDate(now);
        request.setExerciseCount(15);
        request.setSetCount(5);
        // when
        mapExerciseUserService.createMapUserExercise(userNum, exerciseId, request);

        // then
    }

    @Test(expected = UserNotFoundException.class)
    public void fail_create_user_exercise_userNotFound() {
        // given
        Integer userNum = 1123;
        Integer exerciseId = 1;
        LocalDateTime now = LocalDateTime.now();
        ExerciseUserCreateRequest request = new ExerciseUserCreateRequest();
        request.setDate(now);
        request.setExerciseCount(15);
        request.setSetCount(5);
        // when
        mapExerciseUserService.createMapUserExercise(userNum, exerciseId, request);
        // then
    }

    @Test(expected = ExerciseNotFoundException.class)
    public void fail_create_user_exercise_exerciseNotFound() {
        // given
        Integer userNum = 1;
        Integer exerciseId = 154545;
        LocalDateTime now = LocalDateTime.now();
        ExerciseUserCreateRequest request = new ExerciseUserCreateRequest();
        request.setDate(now);
        request.setExerciseCount(15);
        request.setSetCount(5);
        // when
        mapExerciseUserService.createMapUserExercise(userNum, exerciseId, request);
        // then
    }

    @Test(expected = ExerciseUserCreateValidationException.class)
    public void fail_create_user_exercise_validation() {
        // given
        Integer userNum = 1;
        Integer exerciseId = 1;
        LocalDateTime now = LocalDateTime.now();
        ExerciseUserCreateRequest request = new ExerciseUserCreateRequest();
        request.setDate(now);
        request.setExerciseCount(-1);
        request.setSetCount(100);
        // when
        mapExerciseUserService.createMapUserExercise(userNum, exerciseId, request);
        // then
    }

    @Test(expected = ExerciseUserCreateValidationException.class)
    public void fail_create_user_exercise_validation2() {
        // given
        Integer userNum = 1;
        Integer exerciseId = 1;
        LocalDateTime now = LocalDateTime.now();
        ExerciseUserCreateRequest request = new ExerciseUserCreateRequest();
        request.setDate(now);
        request.setExerciseCount(100);
        request.setSetCount(-1);
        // when
        mapExerciseUserService.createMapUserExercise(userNum, exerciseId, request);
        // then
    }
}
