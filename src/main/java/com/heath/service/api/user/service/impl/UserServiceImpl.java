package com.heath.service.api.user.service.impl;

import com.heath.service.api.user.command.request.UserCreateRequest;
import com.heath.service.api.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public Integer createUser(UserCreateRequest userCreateRequest) {
        return null;
    }
}
