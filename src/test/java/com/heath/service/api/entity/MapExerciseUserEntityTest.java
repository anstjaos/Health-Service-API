package com.heath.service.api.entity;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.heath.service.api.DbUnitTestContext;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DatabaseSetup(value = {
        "/database/exercise.xml",
        "/database/user.xml",
        "/database/map_exercise_user.xml"
})
public class MapExerciseUserEntityTest extends DbUnitTestContext {

    @Test
    public void success_get_set_map_exercise_user() {
        MapExerciseUserEntity mapExerciseUserEntity = new MapExerciseUserEntity();

        LocalDateTime date = LocalDateTime.now();

        mapExerciseUserEntity.setExerciseId(1);
        mapExerciseUserEntity.setUserId(1);
        mapExerciseUserEntity.setDate(date);
        mapExerciseUserEntity.setExerciseCount(10);
        mapExerciseUserEntity.setSetCount(3);

        assertEquals(1, mapExerciseUserEntity.getExerciseId());
        assertEquals(1, mapExerciseUserEntity.getUserId());
        assertEquals(date, mapExerciseUserEntity.getDate());
        assertEquals(10, mapExerciseUserEntity.getExerciseCount());
        assertEquals(3, mapExerciseUserEntity.getSetCount());
    }
}
