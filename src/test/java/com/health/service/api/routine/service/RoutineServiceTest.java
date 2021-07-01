package com.health.service.api.routine.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.routine.exception.RoutineNotFoundException;
import com.health.service.api.routine.exception.RoutineNotMatchedException;
import com.health.service.api.routine.exception.RoutineRequestException;
import com.health.service.api.routine.model.command.model.RoutineDto;
import com.health.service.api.routine.model.command.request.CreateRoutineRequest;
import com.health.service.api.routine.model.command.request.UpdateRoutineRequest;
import com.health.service.api.user.exception.UserNotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

    @Test(expected = RoutineRequestException.class)
    public void fail_create_routine_routineName() {
        // given
        CreateRoutineRequest request = new CreateRoutineRequest();
        request.setDayOfWeek(4);

        Integer userNum = 1;
        // when
        Integer routineId = routineService.createRoutine(userNum, request);
        // then
    }

    @Test(expected = RoutineRequestException.class)
    public void fail_create_routine_dayOfWeek_null() {
        // given
        CreateRoutineRequest request = new CreateRoutineRequest();
        request.setRoutineName("test");

        Integer userNum = 1;
        // when
        Integer routineId = routineService.createRoutine(userNum, request);
        // then
    }

    @Test(expected = RoutineRequestException.class)
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

    @Test
    public void success_update_routine() {
        // given
        UpdateRoutineRequest request = new UpdateRoutineRequest();
        request.setRoutineName("asdf");
        request.setDayOfWeek(2);
        Integer userNum = 1;
        Integer routineId = 1;
        // when
        routineService.updateRoutine(userNum, routineId, request);
        RoutineDto routineDto = routineService.getRoutine(userNum, routineId);
        // then
        assertEquals("asdf", routineDto.getRoutineName());
        assertEquals(2, routineDto.getDayOfWeek());
    }

    @Test(expected = UserNotFoundException.class)
    public void fail_update_routine_userNotFound() {
        // given
        UpdateRoutineRequest request = new UpdateRoutineRequest();
        Integer userNum = 20;
        Integer routineId = 1;
        // when
        routineService.updateRoutine(userNum, routineId, request);
        // then
    }

    @Test(expected = RoutineNotFoundException.class)
    public void fail_update_routine_routineNotFound() {
        // given
        UpdateRoutineRequest request = new UpdateRoutineRequest();
        Integer userNum = 1;
        Integer routineId = 10;
        // when
        routineService.updateRoutine(userNum, routineId, request);
        // then
    }
    
    @Test(expected = RoutineRequestException.class)
    public void fail_update_routine_requestException() {
        // given
        UpdateRoutineRequest request = new UpdateRoutineRequest();
        request.setDayOfWeek(40);
        Integer userNum = 1;
        Integer routineId = 1;
        // when
        routineService.updateRoutine(userNum, routineId, request);
        // then
    }
    
    @Test
    public void success_get_routine() {
        // given
        Integer userNum = 1;
        Integer routineId = 1;
        // when
        RoutineDto routineDto = routineService.getRoutine(userNum, routineId);
        // then
        assertEquals(routineId, routineDto.getRoutineId());
        assertEquals(userNum, routineDto.getUserNum());
        assertEquals("test", routineDto.getRoutineName());
        assertEquals(0, routineDto.getDayOfWeek());
    }

    @Test(expected = UserNotFoundException.class)
    public void fail_get_routine_userNotFound() {
        // given
        Integer userNum = 1234;
        Integer routineId = 1;
        // when
        routineService.getRoutine(userNum, routineId);
        // then
    }
    
    @Test(expected = RoutineNotFoundException.class)
    public void fail_get_routine_routineNotFound() {
        // given
        Integer userNum = 1;
        Integer routineId = 41;
        // when
        routineService.getRoutine(userNum, routineId);
        // then
    }

    @Test(expected = RoutineNotFoundException.class)
    public void success_delete_routine() {
        // given
        Integer userNum = 1;
        Integer routineId = 1;
        // when
        routineService.deleteRoutine(userNum, routineId);
        // then
        routineService.getRoutine(userNum, routineId);
    }

    @Test(expected = UserNotFoundException.class)
    public void fail_delete_routine_userNotFound() {
        // given
        Integer userNum = 41;
        Integer routineId = 1;
        // when
        routineService.deleteRoutine(userNum, routineId);
        // then
    }
    
    @Test(expected = RoutineNotFoundException.class)
    public void fail_delete_routine_routineNotFound() {
        // given
        Integer userNum = 1;
        Integer routineId = 41;
        // when
        routineService.deleteRoutine(userNum, routineId);
        // then
    }

    @Test(expected = RoutineNotMatchedException.class)
    public void fail_delete_routine_routineNotMatched() {
        // given
        Integer userNum = 2;
        Integer routineId = 1;
        // when
        routineService.deleteRoutine(userNum, routineId);
        // then
    }

    @Test
    public void success_get_routine_list() {
        // given
        Integer userNum = 1;
        // when
        List<RoutineDto> routineList = routineService.getRoutineList(userNum);
        // then
        assertEquals(3, routineList.size());
    }
    
    @Test
    public void success_get_routine_list_empty() {
        // given
        Integer userNum = 2;
        // when
        List<RoutineDto> routineList = routineService.getRoutineList(userNum);
        // then
        assertEquals(0, routineList.size());
    }

    @Test(expected = UserNotFoundException.class)
    public void fail_get_routine_list_userNotFound() {
        // given
        Integer userNum = 1234;
        // when
        routineService.getRoutineList(userNum);
        // then
    }
}
