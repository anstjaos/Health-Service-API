package com.health.service.api.exercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.exercise.model.command.request.ExerciseCreateRequest;
import com.health.service.api.exercise.service.ExerciseService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@DatabaseSetup(value = {
        "/database/exercise.xml"
})
public  class ExerciseControllerTest extends DbUnitTestContext {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @MockBean
    private ExerciseService exerciseService;

    @Test
    public void success_create_user() throws Exception {
        ExerciseCreateRequest request = new ExerciseCreateRequest();
        request.setExerciseName("test");
        request.setBodyPardId(1);
        request.setExerciseTypeId(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/exercises")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private static String objectToString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
