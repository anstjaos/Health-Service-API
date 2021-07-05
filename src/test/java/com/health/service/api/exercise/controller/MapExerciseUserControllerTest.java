package com.health.service.api.exercise.controller;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.exercise.exception.ExerciseNotFoundException;
import com.health.service.api.exercise.exception.ExerciseUserCreateValidationException;
import com.health.service.api.exercise.model.command.request.ExerciseUserCreateRequest;
import com.health.service.api.exercise.service.MapExerciseUserService;
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

import java.time.LocalDateTime;

import static com.health.service.api.utility.ObjectToJsonUtil.objectToString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DatabaseSetup(value = {
        "/database/exercise.xml",
        "/database/service_user.xml",
        "/database/map_exercise_user.xml"
})
public class MapExerciseUserControllerTest extends DbUnitTestContext {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @MockBean
    private MapExerciseUserService mapExerciseUserService;

    @Test
    public void success_create_user_exercise() throws Exception {
        // given
        ExerciseUserCreateRequest request = new ExerciseUserCreateRequest();
        request.setDate(LocalDateTime.now());
        request.setExerciseCount(10);
        request.setSetCount(3);
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/users/1/exercises/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void fail_create_exercise_user_userNotFound() throws Exception {
        // given
        ExerciseUserCreateRequest request = new ExerciseUserCreateRequest();
        request.setDate(LocalDateTime.now());
        request.setExerciseCount(10);
        request.setSetCount(3);
        // when
        doThrow(new UserNotFoundException()).when(mapExerciseUserService).createMapUserExercise(any(), any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/users/1/exercises/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"not found user\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void fail_create_exercise_user_exerciseNotFound() throws Exception {
        // given
        ExerciseUserCreateRequest request = new ExerciseUserCreateRequest();
        request.setDate(LocalDateTime.now());
        request.setExerciseCount(10);
        request.setSetCount(3);
        // when
        doThrow(new ExerciseNotFoundException()).when(mapExerciseUserService).createMapUserExercise(any(), any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/users/1/exercises/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"not found exercise\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void fail_create_exercise_user_validationFail() throws Exception {
        // given
        ExerciseUserCreateRequest request = new ExerciseUserCreateRequest();
        request.setDate(LocalDateTime.now());
        request.setExerciseCount(10);
        request.setSetCount(3);
        // when
        doThrow(new ExerciseUserCreateValidationException()).when(mapExerciseUserService).createMapUserExercise(any(), any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/users/1/exercises/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":400,\"message\":\"create user exercise request validation fault\",\"successful\":false},\"body\":null}"));
    }
}
