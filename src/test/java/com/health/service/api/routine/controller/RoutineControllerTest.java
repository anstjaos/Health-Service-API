package com.health.service.api.routine.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.routine.exception.CreateRoutineRequestException;
import com.health.service.api.routine.model.command.request.CreateRoutineRequest;
import com.health.service.api.routine.service.RoutineService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DatabaseSetup(value = {
        "/database/exercise_routine.xml"
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
        doThrow(new CreateRoutineRequestException("routine name must be not null!")).when(routineService).createRoutine(any(), any());
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
        doThrow(new CreateRoutineRequestException("routine day of week value must set between 0, 6")).when(routineService).createRoutine(any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/users/1/routines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":400,\"message\":\"routine day of week value must set between 0, 6\",\"successful\":false},\"body\":null}"));
    }

    private static String objectToString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
