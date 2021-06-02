package com.health.service.api.user.model.dto.mapper;

import com.health.service.api.user.entity.UserEntity;
import com.health.service.api.user.model.dto.UserDto;

public class UserDtoMapper {

    public static UserDto convert(UserEntity userEntity) {
        return UserDto.builder()
                .userNum(userEntity.getUserNum())
                .userId(userEntity.getUserId())
                .nickName(userEntity.getNickName())
                .email(userEntity.getEmail())
                .createdOn(userEntity.getCreatedOn())
                .modifiedOn(userEntity.getModifiedOn())
                .build();
    }
}
