package com.health.service.api.exercise.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.common.exception.ExerciseNotFoundException;
import com.health.service.api.exercise.model.command.model.ExerciseDto;
import com.health.service.api.exercise.model.command.request.ExerciseCreateRequest;
import com.health.service.api.exercise.model.command.request.ExerciseUpdateRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DatabaseSetup(value = {
        "/database/exercise.xml",
        "/database/body_part.xml",
        "/database/exercise_type.xml"
})
public class ExerciseServiceTest extends DbUnitTestContext {

    @Autowired
    private ExerciseService exerciseService;

    @Test
    public void success_create_exercise() {
        // given
        ExerciseCreateRequest request = new ExerciseCreateRequest();
        request.setExerciseName("test");
        request.setExerciseTypeId(1);
        request.setBodyPardId(1);
        // when
        Integer exerciseId = exerciseService.createExercise(request);
        // then
        assertEquals(3, exerciseId);
    }

    @Test
    public void success_update_exercise() {
        // given
        ExerciseUpdateRequest request = new ExerciseUpdateRequest();
        request.setExerciseName("test");
        request.setExerciseTypeId(7);
        request.setBodyPartId(7);
        // when
        exerciseService.updateExercise(1, request);
        // then
        ExerciseDto exerciseDto = exerciseService.getExercise(1);
        assertEquals("test", exerciseDto.getExerciseName());
        assertEquals(7, exerciseDto.getExerciseTypeId());
        assertEquals(7, exerciseDto.getBodyPartId());
    }

    @Test(expected = ExerciseNotFoundException.class)
    public void fail_update_exercise() {
        // given
        ExerciseUpdateRequest request = new ExerciseUpdateRequest();
        request.setExerciseName("test");
        request.setExerciseTypeId(7);
        request.setBodyPartId(7);
        // when
        exerciseService.updateExercise(10, request);
        // then
    }

    @Test
    public void success_get_exercise() {
        // given
        // when
        ExerciseDto result = exerciseService.getExercise(1);
        // then
        assertEquals(1, result.getExerciseId());
        assertEquals("deadlift", result.getExerciseName());
        assertEquals(1, result.getExerciseTypeId());
        assertEquals(2, result.getBodyPartId());
    }

    @Test(expected = ExerciseNotFoundException.class)
    public void fail_get_exercise() {
        // given
        // when
        exerciseService.getExercise(14);
        // then
    }

    @Test(expected = ExerciseNotFoundException.class)
    public void success_delete_exercise() {
        // given
        // when
        exerciseService.deleteExercise(1);
        // then
        exerciseService.getExercise(1);
    }

    @Test(expected = ExerciseNotFoundException.class)
    public void fail_delete_exercise() {
        // given
        // when
        exerciseService.deleteExercise(36);
        // then
    }
}
