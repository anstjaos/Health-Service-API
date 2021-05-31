package com.health.service.api.user.controller;

import com.health.service.api.common.model.APIResponse;
import com.health.service.api.common.model.APIResponseHeader;
import com.health.service.api.common.model.SingleResponse;
import com.health.service.api.user.model.command.request.UserCreateRequest;
import com.health.service.api.user.model.command.request.UserUpdateRequest;
import com.health.service.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return new APIResponse(APIResponseHeader.success(), new SingleResponse<>(userNum));
    }

    @PutMapping("/{userNum}")
    public APIResponse updateUser(@PathVariable(name = "userNum") Integer userNum,
                                  @RequestBody UserUpdateRequest userUpdateRequest) {
        userService.updateUser(userNum, userUpdateRequest);
        return new APIResponse(APIResponseHeader.success(), new SingleResponse<>(null));
    }

    @DeleteMapping("/{userNum}")
    public APIResponse deleteUser(@PathVariable(name = "userNum") Integer userNum) {
        userService.deleteUser(userNum);
        return new APIResponse(APIResponseHeader.success(), new SingleResponse<>(null));
    }
}
