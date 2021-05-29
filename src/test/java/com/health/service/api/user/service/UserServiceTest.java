package com.health.service.api.user.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.user.command.request.UserCreateRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@DatabaseSetup(value = {
        "/database/user.xml"
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
        assertEquals(1, userNum);
    }
}
