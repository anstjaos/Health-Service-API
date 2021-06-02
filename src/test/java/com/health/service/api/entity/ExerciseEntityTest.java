package com.health.service.api.entity;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

@DatabaseSetup(value = {
        "/database/body_part.xml",
        "/database/exercise_type.xml",
        "/database/exercise.xml"
})
public class ExerciseEntityTest extends DbUnitTestContext {

    @Test
    public void success_get_set_exercise() {
        ExerciseEntity exerciseEntity = new ExerciseEntity();

        exerciseEntity.setExerciseName("test");
        exerciseEntity.setExerciseTypeId(1);
        exerciseEntity.setBodyPartId(2);

        assertEquals("test", exerciseEntity.getExerciseName());
        assertEquals(1, exerciseEntity.getExerciseTypeId());
        assertEquals(2, exerciseEntity.getBodyPartId());
    }
}
