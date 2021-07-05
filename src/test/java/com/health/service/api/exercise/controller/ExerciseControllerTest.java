package com.health.service.api.exercise.controller;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.config.interceptor.JwtAuthInterceptor;
import com.health.service.api.exercise.exception.ExerciseNotFoundException;
import com.health.service.api.exercise.model.command.model.ExerciseDto;
import com.health.service.api.exercise.model.command.request.ExerciseCreateRequest;
import com.health.service.api.exercise.model.command.request.ExerciseUpdateRequest;
import com.health.service.api.exercise.service.ExerciseService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.health.service.api.utility.ObjectToJsonUtil.objectToString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@DatabaseSetup(value = {
        "/database/exercise.xml",
        "/database/service_user.xml"
})
public class ExerciseControllerTest extends DbUnitTestContext {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @MockBean
    private ExerciseService exerciseService;

    @MockBean
    JwtAuthInterceptor interceptor;

    @BeforeEach
    public void initTest() throws Exception {
        when(interceptor.preHandle(any(), any(), any())).thenReturn(true);
    }

    @Test
    public void success_create_exercise() throws Exception {
        // given
        ExerciseCreateRequest request = new ExerciseCreateRequest();
        request.setExerciseName("test");
        request.setBodyPardId(1);
        request.setExerciseTypeId(1);
        //when
        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/exercises")
                .accept(MediaType.APPLICATION_JSON)
                .header("userNum", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void fail_update_exercise() throws Exception {
        // given
        ExerciseUpdateRequest request = new ExerciseUpdateRequest();
        request.setExerciseName("test");

        // when
        doThrow(new ExerciseNotFoundException()).when(exerciseService).updateExercise(any(), any());

        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/exercises/7")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"not found exercise\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void success_update_exercise() throws Exception{
        // given
        ExerciseUpdateRequest request = new ExerciseUpdateRequest();
        request.setExerciseName("test");
        request.setBodyPartId(6);
        request.setExerciseTypeId(7);
        // when

        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/exercises/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void fail_get_exercise() throws Exception {
        // given
        // when
        doThrow(new ExerciseNotFoundException()).when(exerciseService).getExercise(any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/exercises/7"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"not found exercise\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void success_get_exercise() throws Exception {
        // given
        ExerciseDto exerciseDto = ExerciseDto.builder()
                .exerciseId(1)
                .exerciseName("test")
                .exerciseTypeId(1)
                .bodyPartId(1)
                .build();
        // when
        when(exerciseService.getExercise(1)).thenReturn(exerciseDto);
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/exercises/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":200,\"message\":\"success\",\"successful\":true},\"body\":{\"content\":{\"exerciseId\":1,\"exerciseName\":\"test\",\"exerciseTypeId\":1,\"bodyPartId\":1}}}"));
    }

    @Test
    public void fail_delete_exercise() throws Exception {
        // given
        // when
        doThrow(new ExerciseNotFoundException()).when(exerciseService).deleteExercise(any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/exercises/7"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"not found exercise\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void success_delete_exercise() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/exercises/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
