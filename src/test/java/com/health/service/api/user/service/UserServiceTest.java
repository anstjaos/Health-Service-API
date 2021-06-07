package com.health.service.api.user.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.user.exception.UserLoginFailException;
import com.health.service.api.user.exception.UserNotFoundException;
import com.health.service.api.user.model.command.request.UserCreateRequest;
import com.health.service.api.user.model.command.request.UserLoginRequest;
import com.health.service.api.user.model.command.request.UserUpdateRequest;
import com.health.service.api.user.model.dto.UserDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DatabaseSetup({
        "classpath:database/service_user.xml"
})
public class UserServiceTest extends DbUnitTestContext {

    @Autowired
    private UserService userService;

    @Test
    public void success_save_user() {
        // given
        UserCreateRequest request = new UserCreateRequest();

        request.setUserId("testtest");
        request.setPassword("Test");
        request.setNickName("test");
        request.setEmail("test@asdf.com");

        // when
        Integer userNum = userService.createUser(request);

        // then
        assertEquals(6, userNum);
    }

    @Test
    public void success_update_user() {
        // given
        UserUpdateRequest request = new UserUpdateRequest();

        request.setNickname("test");
        request.setPassword("asdf");
        request.setEmail("asdf");

        // when
        userService.updateUser(1, request);

        // then
        UserDto userDto = userService.getUser(1);
        assertEquals("test", userDto.getNickName());
        assertEquals("asdf", userDto.getEmail());
    }

    @Test(expected = UserNotFoundException.class)
    public void fail_update_user() {
        // given
        UserUpdateRequest request = new UserUpdateRequest();

        request.setNickname("test");
        request.setPassword("asdf");
        request.setEmail("asdf");

        // when
        userService.updateUser(30, request);
    }

    @Test
    public void success_get_user() {
        // given
        Integer userNum = 1;
        // when
        UserDto user = userService.getUser(userNum);
        // then
        assertEquals("test@naver.com", user.getUserId());
        assertEquals("test", user.getNickName());
        assertEquals("test@naver.com", user.getEmail());
    }

    @Test(expected = UserNotFoundException.class)
    public void fail_get_user() {
        Integer userNum = 1111;

        UserDto user = userService.getUser(userNum);
    }

    @Test
    public void success_login_user() {
        // given
        UserLoginRequest request = new UserLoginRequest();
        request.setUserId("test@naver.com");
        request.setPassword("test");
        // when
        String token = userService.loginUser(request);
        // then
        assertEquals("1234", token);
    }

    @Test(expected = UserNotFoundException.class)
    public void fail_login_user_not_found() {
        // given
        UserLoginRequest request = new UserLoginRequest();
        request.setUserId("wrong@naver.com");
        request.setPassword("test");
        // when
        String token = userService.loginUser(request);
        // then
    }
    
    @Test(expected = UserLoginFailException.class)
    public void fail_login_user_not_valid() {
        // given
        UserLoginRequest request = new UserLoginRequest();
        request.setUserId("test@naver.com");
        request.setPassword("tes123t");
        // when
        String token = userService.loginUser(request);
        // then
    }
}
