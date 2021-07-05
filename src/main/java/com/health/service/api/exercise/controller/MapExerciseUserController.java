package com.health.service.api.exercise.controller;

import com.health.service.api.common.model.APIResponse;
import com.health.service.api.common.model.APIResponseHeader;
import com.health.service.api.common.model.SingleResponse;
import com.health.service.api.exercise.model.command.request.ExerciseUserCreateRequest;
import com.health.service.api.exercise.service.MapExerciseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/{userNum}/exercises")
public class MapExerciseUserController {

    private final MapExerciseUserService mapExerciseUserService;

    @Autowired
    public MapExerciseUserController(MapExerciseUserService mapExerciseUserService) {
        this.mapExerciseUserService = mapExerciseUserService;
    }

    @PostMapping("/{exerciseId}")
    public APIResponse createMapUserExercise(@PathVariable("userNum") Integer userNum,
                                             @PathVariable("exerciseId") Integer exerciseId,
                                             ExerciseUserCreateRequest request) {
        mapExerciseUserService.createMapUserExercise(userNum, exerciseId, request);
        return new APIResponse(APIResponseHeader.success(), new SingleResponse<>(null));
    }
}
