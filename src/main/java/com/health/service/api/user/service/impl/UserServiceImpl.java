package com.health.service.api.user.service.impl;

import com.health.service.api.common.exception.UserNotFoundException;
import com.health.service.api.user.command.request.UserCreateRequest;
import com.health.service.api.user.command.request.UserUpdateRequest;
import com.health.service.api.user.entity.UserEntity;
import com.health.service.api.user.repository.UserRepository;
import com.health.service.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Integer createUser(UserCreateRequest userCreateRequest) {
        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(userCreateRequest.getUserId());
        userEntity.setPassword(userCreateRequest.getPassword());
        userEntity.setNickName(userCreateRequest.getNickName());
        userEntity.setEmail(userCreateRequest.getEmail());
        userEntity.setCreatedOn(LocalDateTime.now());

        userEntity = userRepository.save(userEntity);
        return userEntity.getUserNum();
    }

    @Override
    public void updateUser(Integer userNum, UserUpdateRequest userUpdateRequest) {
        UserEntity user = userRepository.findById(userNum)
                .orElseThrow(UserNotFoundException::new);

        Optional.ofNullable(userUpdateRequest.getEmail())
                .ifPresent(user::setEmail);

        Optional.ofNullable(userUpdateRequest.getPassword())
                .ifPresent(user::setPassword);

        Optional.ofNullable(userUpdateRequest.getNickname())
                .ifPresent(user::setNickName);

        user.setModifiedOn(LocalDateTime.now());
    }
}
