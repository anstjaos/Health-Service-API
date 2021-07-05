package com.health.service.api.routine.controller;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.routine.exception.RoutineNotFoundException;
import com.health.service.api.routine.exception.RoutineNotMatchedException;
import com.health.service.api.routine.exception.RoutineRequestException;
import com.health.service.api.routine.model.command.request.CreateRoutineRequest;
import com.health.service.api.routine.model.command.request.UpdateRoutineRequest;
import com.health.service.api.routine.service.RoutineService;
import com.health.service.api.user.exception.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.health.service.api.utility.ObjectToJsonUtil.objectToString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DatabaseSetup(value = {
        "/database/exercise_routine.xml",
        "/database/service_user.xml"
})
public class RoutineControllerTest extends DbUnitTestContext {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @MockBean
    private RoutineService routineService;

    @Test
    public void success_create_routine() throws Exception {
        // given
        CreateRoutineRequest request = new CreateRoutineRequest();
        request.setRoutineName("test");
        request.setDayOfWeek(4);
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/users/1/routines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void fail_create_routine_routineName() throws Exception {
        // given
        CreateRoutineRequest request = new CreateRoutineRequest();
        request.setDayOfWeek(4);
        // when
        doThrow(new RoutineRequestException("routine name must be not null!")).when(routineService).createRoutine(any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/users/1/routines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":400,\"message\":\"routine name must be not null!\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void fail_create_routine_dayOfWeek() throws Exception {
        // given
        CreateRoutineRequest request = new CreateRoutineRequest();
        request.setDayOfWeek(8);
        // when
        doThrow(new RoutineRequestException("routine day of week value must set between 0, 6")).when(routineService).createRoutine(any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/users/1/routines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":400,\"message\":\"routine day of week value must set between 0, 6\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void success_update_routine() throws Exception {
        // given
        UpdateRoutineRequest request = new UpdateRoutineRequest();
        request.setRoutineName("asdf");
        request.setDayOfWeek(2);
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/users/1/routines/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void fail_update_routine() throws Exception {
        // given
        UpdateRoutineRequest request = new UpdateRoutineRequest();
        request.setRoutineName("asdf");
        request.setDayOfWeek(2);
        // when
        doThrow(new RoutineNotFoundException()).when(routineService).updateRoutine(any(), any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/users/10/routines/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"routine not found!\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void fail_update_routine_dayOfWeek() throws Exception {
        // given
        UpdateRoutineRequest request = new UpdateRoutineRequest();
        request.setRoutineName("asdf");
        request.setDayOfWeek(10);
        // when
        doThrow(new RoutineRequestException("routine day of week value must set between 0, 6")).when(routineService).updateRoutine(any(), any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/users/10/routines/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":400,\"message\":\"routine day of week value must set between 0, 6\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void success_get_routine() throws Exception {
        // given
        Integer userNum = 1;
        Integer routineId = 1;
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + userNum + "/routines/" + routineId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void fail_get_routine_notFoundRoutine() throws Exception {
        // given
        Integer userNum = 1;
        Integer routineId = 1;
        // when
        doThrow(new RoutineNotFoundException()).when(routineService).getRoutine(any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + userNum + "/routines/" + routineId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"routine not found!\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void success_delete_routine() throws Exception {
        // given
        Integer userNum = 1;
        Integer routineId = 1;
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/" + userNum + "/routines/" + routineId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void fail_delete_routine_notFoundUser() throws Exception {
        // given
        Integer userNum = 145;
        Integer routineId = 1;
        // when
        doThrow(new UserNotFoundException()).when(routineService).deleteRoutine(any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/" + userNum + "/routines/" + routineId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"not found user\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void fail_delete_routine_routineNotMatched() throws Exception {
        // given
        Integer userNum = 2;
        Integer routineId = 1;
        // when
        doThrow(new RoutineNotMatchedException()).when(routineService).deleteRoutine(any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/" + userNum + "/routines/" + routineId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":400,\"message\":\"user num is not matched this routine\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void fail_delete_routine_routineNotFound() throws Exception {
        // given
        Integer userNum = 1;
        Integer routineId = 177;
        // when
        doThrow(new RoutineNotFoundException()).when(routineService).deleteRoutine(any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/" + userNum + "/routines/" + routineId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"routine not found!\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void success_get_routine_list() throws Exception {
        // given
        Integer userNum = 1;
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + userNum + "/routines")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void fail_get_routine_list_userNotFound() throws Exception {
        // given
        Integer userNum = 1;
        // when
        doThrow(new UserNotFoundException()).when(routineService).getRoutineList(any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + userNum + "/routines")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"not found user\",\"successful\":false},\"body\":null}"));
    }
}
