package com.health.service.api.user.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserDto {

    private final Integer userNum;

    private final String userId;

    private final String nickName;

    private final String email;

    private final LocalDateTime createdOn;

    private final LocalDateTime modifiedOn;

    @Builder
    public UserDto(Integer userNum,
                   String userId,
                   String nickName,
                   String email,
                   LocalDateTime createdOn,
                   LocalDateTime modifiedOn) {
        this.userNum = userNum;
        this.userId = userId;
        this.nickName = nickName;
        this.email = email;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }
}
