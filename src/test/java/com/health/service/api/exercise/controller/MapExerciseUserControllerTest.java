package com.health.service.api.exercise.controller;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.exercise.model.command.request.ExerciseUserCreateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static com.health.service.api.utility.ObjectToJsonUtil.objectToString;

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
}
