package com.health.service.api.user.service;

import com.health.service.api.user.command.request.UserCreateRequest;
import com.health.service.api.user.command.request.UserUpdateRequest;

public interface UserService {

    Integer createUser(UserCreateRequest userCreateRequest);

    void updateUser(Integer userNum, UserUpdateRequest userUpdateRequest);
}
