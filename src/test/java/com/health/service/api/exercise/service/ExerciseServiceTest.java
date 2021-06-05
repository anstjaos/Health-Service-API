package com.health.service.api.exercise.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.exercise.model.command.request.ExerciseCreateRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

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
}
