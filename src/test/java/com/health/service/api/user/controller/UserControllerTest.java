package com.health.service.api.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.common.exception.UserNotFoundException;
import com.health.service.api.user.command.request.UserCreateRequest;
import com.health.service.api.user.command.request.UserUpdateRequest;
import com.health.service.api.user.service.UserService;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DatabaseSetup(value = {
        "/database/service_user.xml"
})
public class UserControllerTest extends DbUnitTestContext {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @MockBean
    private UserService userService;

    @Test
    public void success_create_user() throws Exception {
        UserCreateRequest request = new UserCreateRequest();
        request.setUserId("test");
        request.setNickName("test");
        request.setPassword("test");
        request.setEmail("test@asdf.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void success_update_user() throws Exception {
        // given
        UserUpdateRequest request = new UserUpdateRequest();
        request.setEmail("test@naver.com");
        request.setNickname("test");
        request.setPassword("test");

        // when
        doNothing().when(userService).updateUser(any(), any());

        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/users/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectToString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void fail_update_user() throws Exception {
        // given
        UserUpdateRequest request = new UserUpdateRequest();
        request.setEmail("test@naver.com");
        request.setNickname("test");
        request.setPassword("test");

        // when
        doThrow(new UserNotFoundException()).when(userService).updateUser(any(), any());

        // then
        mockMvc.perform(MockMvcRequestBuilders.put("/users/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectToString(request)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"not found user\",\"successful\":false},\"body\":null}"));
    }

    private static String objectToString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
