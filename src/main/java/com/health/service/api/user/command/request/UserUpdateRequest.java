package com.health.service.api.user.command.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {

    private String nickname;

    private String password;

    private String email;
}
