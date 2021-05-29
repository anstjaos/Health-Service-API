package com.health.service.api.user.command.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequest {

    private String userId;

    private String nickName;

    private String password;

    private String email;
}
