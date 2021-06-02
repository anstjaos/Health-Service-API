package com.health.service.api.entity;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DatabaseSetup(value = {
        "/database/service_user.xml",
        "/database/exercise_routine.xml"
})
public class ExerciseRoutineEntityTest extends DbUnitTestContext {

    @Test
    public void success_get_set_routine() {
        ExerciseRoutineEntity exerciseRoutineEntity = new ExerciseRoutineEntity();

        exerciseRoutineEntity.setUserNum(1);
        exerciseRoutineEntity.setDayOfWeek(0);

        assertEquals(1, exerciseRoutineEntity.getUserNum());
        assertEquals(0, exerciseRoutineEntity.getDayOfWeek());
    }
}
