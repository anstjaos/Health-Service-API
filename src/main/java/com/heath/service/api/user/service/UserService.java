package com.heath.service.api.user.service;

import com.heath.service.api.user.command.request.UserCreateRequest;

public interface UserService {

    Integer createUser(UserCreateRequest userCreateRequest);
}
