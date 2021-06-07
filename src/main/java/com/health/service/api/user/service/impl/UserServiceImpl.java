package com.health.service.api.user.service.impl;

import com.health.service.api.user.exception.UserLoginFailException;
import com.health.service.api.user.exception.UserNotFoundException;
import com.health.service.api.user.model.command.request.UserCreateRequest;
import com.health.service.api.user.model.command.request.UserLoginRequest;
import com.health.service.api.user.model.command.request.UserUpdateRequest;
import com.health.service.api.user.entity.UserEntity;
import com.health.service.api.user.model.dto.UserDto;
import com.health.service.api.user.model.dto.mapper.UserDtoMapper;
import com.health.service.api.user.repository.UserRepository;
import com.health.service.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional
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
    @Transactional
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

    @Override
    @Transactional
    public void deleteUser(Integer userNum) {
        UserEntity user = userRepository.findById(userNum)
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);
    }

    @Override
    public UserDto getUser(Integer userNum) {
        UserEntity userEntity = userRepository.findById(userNum)
                .orElseThrow(UserNotFoundException::new);

        return UserDtoMapper.convert(userEntity);
    }

    @Override
    public String loginUser(UserLoginRequest request) {
        UserEntity userEntity = userRepository.findByUserId(request.getUserId())
                .orElseThrow(UserNotFoundException::new);

        if (!userEntity.getPassword().equals(request.getPassword())) {
            throw new UserLoginFailException();
        }

        return userEntity.getToken();
    }
}
