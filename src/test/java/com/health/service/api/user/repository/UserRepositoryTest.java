package com.health.service.api.user.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.user.entity.UserEntity;
import com.health.service.api.user.exception.UserNotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DatabaseSetup(value = {
        "/database/service_user.xml"
})
public class UserRepositoryTest extends DbUnitTestContext {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void success_findByUserId() {
        // given
        String userId = "test@naver.com";
        // when
        UserEntity user = userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);
        // then
        assertEquals(userId, user.getUserId());
        assertEquals("test", user.getNickName());
    }

    @Test(expected = UserNotFoundException.class)
    public void fail_findByUserId() {
        // given
        String userId = "wrong@naver.com";
        // when
        UserEntity user = userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);
        // then
    }
}
