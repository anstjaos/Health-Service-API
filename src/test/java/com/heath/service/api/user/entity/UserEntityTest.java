package com.heath.service.api.user.entity;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.heath.service.api.DbUnitTestContext;
import com.heath.service.api.user.entity.UserEntity;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DatabaseSetup(value = {
        "/database/user.xml"
})
public class UserEntityTest extends DbUnitTestContext {

    @Test
    public void success_user_getter() {
        UserEntity user = new UserEntity();

        LocalDateTime curTime = LocalDateTime.now();
        user.setUserId("test");
        user.setNickName("hi");
        user.setCreatedOn(curTime);
        user.setModifiedOn(curTime);
        user.setEmail("test@naver.com");

        assertEquals("test", user.getUserId());
        assertEquals("hi", user.getNickName());
        assertEquals(curTime, user.getCreatedOn());
        assertEquals(curTime, user.getModifiedOn());
        assertEquals("test@naver.com", user.getEmail());
    }
}
