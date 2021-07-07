package com.health.service.api.user.controller;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.user.exception.UserLoginFailException;
import com.health.service.api.user.exception.UserNotFoundException;
import com.health.service.api.user.model.command.request.UserCreateRequest;
import com.health.service.api.user.model.command.request.UserLoginRequest;
import com.health.service.api.user.model.command.request.UserUpdateRequest;
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

import static com.health.service.api.utility.ObjectToJsonUtil.objectToString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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

        mockMvc.perform(MockMvcRequestBuilders.post("/users/sign-in")
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

    @Test
    public void success_login_user() throws Exception {
        // given
        UserLoginRequest request = new UserLoginRequest();
        request.setUserId("test@naver.com");
        request.setPassword("test");
        // when
        when(userService.loginUser(request)).thenReturn("1234");
        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void fail_login_user_not_found() throws Exception {
        // given
        UserLoginRequest request = new UserLoginRequest();
        request.setUserId("asdf@naver.com");
        request.setPassword("asedf");
        // when
        doThrow(new UserNotFoundException()).when(userService).loginUser(any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":404,\"message\":\"not found user\",\"successful\":false},\"body\":null}"));
    }

    @Test
    public void fail_login_user_not_valid() throws Exception{
        // given
        UserLoginRequest request = new UserLoginRequest();
        request.setUserId("asdf@naver.com");
        request.setPassword("asedf");
        // when
        doThrow(new UserLoginFailException()).when(userService).loginUser(any());
        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToString(request)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string("{\"header\":{\"statusCode\":403,\"message\":\"login info is not valid\",\"successful\":false},\"body\":null}"));
    }
}
