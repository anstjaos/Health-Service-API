package com.heath.service.api.entity;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.heath.service.api.DbUnitTestContext;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DatabaseSetup(value = {
        "/database/user.xml",
        "/database/exercise_routine.xml"
})
public class ExerciseRoutineEntityTest extends DbUnitTestContext {

    @Test
    public void success_get_set_routine() {
        ExerciseRoutineEntity exerciseRoutineEntity = new ExerciseRoutineEntity();

        exerciseRoutineEntity.setUserId(1);
        exerciseRoutineEntity.setDayOfWeek(0);

        assertEquals(1, exerciseRoutineEntity.getUserId());
        assertEquals(0, exerciseRoutineEntity.getDayOfWeek());
    }
}
