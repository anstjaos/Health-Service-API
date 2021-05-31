package com.health.service.api.user.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.common.exception.UserNotFoundException;
import com.health.service.api.user.model.command.request.UserCreateRequest;
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
}
