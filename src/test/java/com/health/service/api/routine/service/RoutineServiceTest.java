package com.health.service.api.routine.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.routine.exception.CreateRoutineRequestException;
import com.health.service.api.routine.model.command.request.CreateRoutineRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DatabaseSetup(value = {
        "/database/exercise_routine.xml",
        "/database/service_user.xml",
})
public class RoutineServiceTest extends DbUnitTestContext {

    @Autowired
    private RoutineService routineService;

    @Test
    public void success_create_routine() {
        // given
        CreateRoutineRequest request = new CreateRoutineRequest();
        request.setRoutineName("test");
        request.setDayOfWeek(1);

        Integer userNum = 1;
        // when
        Integer routineId = routineService.createRoutine(userNum, request);
        // then
        assertEquals(4, routineId);
    }

    @Test(expected = CreateRoutineRequestException.class)
    public void fail_create_routine_routineName() {
        // given
        CreateRoutineRequest request = new CreateRoutineRequest();
        request.setDayOfWeek(4);

        Integer userNum = 1;
        // when
        Integer routineId = routineService.createRoutine(userNum, request);
        // then
    }

    @Test(expected = CreateRoutineRequestException.class)
    public void fail_create_routine_dayOfWeek_null() {
        // given
        CreateRoutineRequest request = new CreateRoutineRequest();
        request.setRoutineName("test");

        Integer userNum = 1;
        // when
        Integer routineId = routineService.createRoutine(userNum, request);
        // then
    }

    @Test(expected = CreateRoutineRequestException.class)
    public void fail_create_routine_dayOfWeek_range() {
        // given
        CreateRoutineRequest request = new CreateRoutineRequest();
        request.setRoutineName("test");
        request.setDayOfWeek(10);

        Integer userNum = 1;
        // when
        Integer routineId = routineService.createRoutine(userNum, request);
        // then
    }
}
