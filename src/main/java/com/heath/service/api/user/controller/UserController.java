package com.heath.service.api.user.controller;

import com.heath.service.api.common.model.APIResponse;
import com.heath.service.api.common.model.APIResponseHeader;
import com.heath.service.api.common.model.SingleResponse;
import com.heath.service.api.user.command.request.UserCreateRequest;
import com.heath.service.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public APIResponse createUser(@RequestBody UserCreateRequest userCreateRequest) {
        Integer userNum = userService.createUser(userCreateRequest);
        return new APIResponse(APIResponseHeader.create(true, HttpStatus.OK.value(), "success"), new SingleResponse<>(userNum));
    }
}
