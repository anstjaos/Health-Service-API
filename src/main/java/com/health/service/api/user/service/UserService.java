package com.health.service.api.user.service;

import com.health.service.api.user.model.command.request.UserCreateRequest;
import com.health.service.api.user.model.command.request.UserLoginRequest;
import com.health.service.api.user.model.command.request.UserUpdateRequest;
import com.health.service.api.user.model.dto.UserDto;

public interface UserService {

    Integer createUser(UserCreateRequest userCreateRequest);

    void updateUser(Integer userNum, UserUpdateRequest userUpdateRequest);

    void deleteUser(Integer userNum);

    UserDto getUser(Integer userNum);

    String loginUser(UserLoginRequest request);
}
