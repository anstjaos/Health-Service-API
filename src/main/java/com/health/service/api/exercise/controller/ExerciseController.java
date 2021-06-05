package com.health.service.api.exercise.controller;

import com.health.service.api.common.model.APIResponse;
import com.health.service.api.common.model.APIResponseHeader;
import com.health.service.api.common.model.SingleResponse;
import com.health.service.api.exercise.model.command.request.ExerciseCreateRequest;
import com.health.service.api.exercise.model.command.request.ExerciseUpdateRequest;
import com.health.service.api.exercise.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping
    public APIResponse createExercise(@RequestBody ExerciseCreateRequest request) {
        Integer exerciseId = exerciseService.createExercise(request);
        return new APIResponse(APIResponseHeader.success(), new SingleResponse<>(exerciseId));
    }

    @PutMapping("/{exerciseId}")
    public APIResponse updateExercise(@PathVariable("exerciseId") Integer exerciseId,
                                      @RequestBody ExerciseUpdateRequest request) {
        exerciseService.updateExercise(exerciseId, request);
        return new APIResponse(APIResponseHeader.success(), new SingleResponse<>(null));
    }
}
