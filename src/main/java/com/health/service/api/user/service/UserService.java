package com.health.service.api.user.service;

import com.health.service.api.user.command.request.UserCreateRequest;

public interface UserService {

    Integer createUser(UserCreateRequest userCreateRequest);
}
