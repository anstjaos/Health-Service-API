package com.heath.service.api.entity;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.heath.service.api.DbUnitTestContext;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

@DatabaseSetup(value = {
        "/database/map_exercise_routine.xml"
})
public class MapExerciseRoutineEntityTest extends DbUnitTestContext {

    @Test
    public void success_get_set_map_exercise_routine() {
        MapExerciseRoutineEntity mapExerciseRoutineEntity = new MapExerciseRoutineEntity();

        mapExerciseRoutineEntity.setExerciseId(1);
        mapExerciseRoutineEntity.setRoutineId(1);

        assertEquals(1, mapExerciseRoutineEntity.getExerciseId());
        assertEquals(1, mapExerciseRoutineEntity.getRoutineId());
    }
}
