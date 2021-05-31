package com.health.service.api.user.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.user.entity.UserEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DatabaseSetup(value = {
        "/database/service_user.xml"
})
public class UserRepositoryTest extends DbUnitTestContext {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {

    }
}
