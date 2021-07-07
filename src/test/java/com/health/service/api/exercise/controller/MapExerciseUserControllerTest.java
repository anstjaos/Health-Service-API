package com.health.service.api.exercise.controller;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.exercise.exception.ExerciseNotFoundException;
import com.health.service.api.exercise.exception.ExerciseUserValidationException;
import com.health.service.api.exercise.exception.MapExerciseUserNotFoundException;
import com.health.service.api.exercise.model.command.request.ExerciseUserCreateAndUpdateRequest;
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
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
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
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
        request.setDate(LocalDateTime.now());
        request.setExerciseCount(10);
        request.setSetCount(3);
        // when
        doThrow(new UserNotFoundException()).when(mapExerciseUserService).createMapExerciseUser(any(), any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/users/1/exercises/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"not found user\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void fail_create_exercise_user_exerciseNotFound() throws Exception {
        // given
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
        request.setDate(LocalDateTime.now());
        request.setExerciseCount(10);
        request.setSetCount(3);
        // when
        doThrow(new ExerciseNotFoundException()).when(mapExerciseUserService).createMapExerciseUser(any(), any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/users/1/exercises/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"not found exercise\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void fail_create_exercise_user_validationFail() throws Exception {
        // given
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
        request.setDate(LocalDateTime.now());
        request.setExerciseCount(10);
        request.setSetCount(3);
        // when
        doThrow(new ExerciseUserValidationException()).when(mapExerciseUserService).createMapExerciseUser(any(), any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/users/1/exercises/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":400,\"message\":\"user exercise request validation fault\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void success_update_exercise_user() throws Exception {
        // given
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
        request.setDate(LocalDateTime.now());
        request.setExerciseCount(10);
        request.setSetCount(4);
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/users/1/exercises/1/maps/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void fail_update_exercise_user_notFoundUser() throws Exception {
        // given
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
        request.setDate(LocalDateTime.now());
        request.setExerciseCount(10);
        request.setSetCount(3);

        Integer userNum = 1234;
        Integer exerciseId = 1;
        // when
        doThrow(new UserNotFoundException()).when(mapExerciseUserService).updateMapExerciseUser(any(), any(), any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/users/" + userNum + "/exercises/" + exerciseId + "/maps/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"not found user\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void fail_update_exercise_user_exerciseNotFound() throws Exception {
        // given
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
        request.setDate(LocalDateTime.now());
        request.setExerciseCount(10);
        request.setSetCount(3);

        Integer userNum = 1;
        Integer exerciseId = 1231;
        // when
        doThrow(new ExerciseNotFoundException()).when(mapExerciseUserService).updateMapExerciseUser(any(), any(), any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/users/" + userNum + "/exercises/" + exerciseId + "/maps/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"not found exercise\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void fail_update_exercise_user_validation() throws Exception {
        // given
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
        request.setDate(LocalDateTime.now());
        request.setExerciseCount(-1);
        request.setSetCount(3);

        Integer userNum = 1;
        Integer exerciseId = 1;
        // when
        doThrow(new ExerciseUserValidationException()).when(mapExerciseUserService).updateMapExerciseUser(any(), any(), any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/users/" + userNum + "/exercises/" + exerciseId + "/maps/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":400,\"message\":\"user exercise request validation fault\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void fail_update_exercise_user_mapIdNotFound() throws Exception {
        // given
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
        request.setDate(LocalDateTime.now());
        request.setExerciseCount(10);
        request.setSetCount(3);

        Integer userNum = 1;
        Integer exerciseId = 1;
        // when
        doThrow(new MapExerciseUserNotFoundException()).when(mapExerciseUserService).updateMapExerciseUser(any(), any(), any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/users/" + userNum + "/exercises/" + exerciseId + "/maps/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"map id is not found\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void success_get_exercise_user() throws Exception {
        // given
        Integer userNum = 1;
        Integer exerciseId = 1;
        Integer mapId = 1;
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + userNum + "/exercises/" + exerciseId + "/maps/" + mapId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void fail_get_exercise_user_userNotFound() throws Exception {
        // given
        Integer userNum = 1234;
        Integer exerciseId = 1;
        Integer mapId = 1;
        // when
        doThrow(new UserNotFoundException()).when(mapExerciseUserService).getMapExerciseUser(any(), any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + userNum + "/exercises/" + exerciseId + "/maps/" + mapId))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"not found user\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void fail_get_exercise_user_exerciseNotFound() throws Exception {
        // given
        Integer userNum = 1;
        Integer exerciseId = 1234;
        Integer mapId = 1;
        // when
        doThrow(new ExerciseNotFoundException()).when(mapExerciseUserService).getMapExerciseUser(any(), any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + userNum + "/exercises/" + exerciseId + "/maps/" + mapId))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"not found exercise\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void fail_get_exercise_user_mapIdNotFound() throws Exception {
        // given
        Integer userNum = 1;
        Integer exerciseId = 1;
        Integer mapId = 1234;
        // when
        doThrow(new MapExerciseUserNotFoundException()).when(mapExerciseUserService).getMapExerciseUser(any(), any(), any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + userNum + "/exercises/" + exerciseId + "/maps/" + mapId))
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"map id is not found\",\"successful\":false},\"body\":null}"));
    }
}
