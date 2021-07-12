package com.health.service.api.exercise.controller;

import com.health.service.api.common.model.APIResponse;
import com.health.service.api.common.model.APIResponseHeader;
import com.health.service.api.common.model.CollectionResponse;
import com.health.service.api.common.model.SingleResponse;
import com.health.service.api.exercise.model.command.model.MapExerciseUserDto;
import com.health.service.api.exercise.model.command.request.ExerciseUserCreateAndUpdateRequest;
import com.health.service.api.exercise.service.MapExerciseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userNum}/exercises")
public class MapExerciseUserController {

    private final MapExerciseUserService mapExerciseUserService;

    @Autowired
    public MapExerciseUserController(MapExerciseUserService mapExerciseUserService) {
        this.mapExerciseUserService = mapExerciseUserService;
    }

    @PostMapping("/{exerciseId}")
    public APIResponse createMapExerciseUser(@PathVariable("userNum") Integer userNum,
                                             @PathVariable("exerciseId") Integer exerciseId,
                                             ExerciseUserCreateAndUpdateRequest request) {
        Integer mapId = mapExerciseUserService.createMapExerciseUser(userNum, exerciseId, request);
        return new APIResponse(APIResponseHeader.success(), new SingleResponse<>(mapId));
    }

    @PutMapping("/{exerciseId}/maps/{mapId}")
    public APIResponse updateMapExerciseUser(@PathVariable("userNum") Integer userNum,
                                             @PathVariable("exerciseId") Integer exerciseId,
                                             @PathVariable("mapId") Integer mapId,
                                             ExerciseUserCreateAndUpdateRequest request) {
        mapExerciseUserService.updateMapExerciseUser(userNum, exerciseId, mapId, request);
        return new APIResponse(APIResponseHeader.success(), new SingleResponse<>(null));
    }

    @GetMapping("/{exerciseId}/maps/{mapId}")
    public APIResponse getMapExerciseUser(@PathVariable("userNum") Integer userNum,
                                          @PathVariable("exerciseId") Integer exerciseId,
                                          @PathVariable("mapId") Integer mapId) {
        MapExerciseUserDto mapExerciseUserDto = mapExerciseUserService.getMapExerciseUser(userNum, exerciseId, mapId);
        return new APIResponse(APIResponseHeader.success(), new SingleResponse<>(mapExerciseUserDto));
    }

    @GetMapping
    public APIResponse getMapExerciseUserList(@PathVariable("userNum") Integer userNum) {
        List<MapExerciseUserDto> mapExerciseUserDtoList = mapExerciseUserService.getMapExerciseUserList(userNum);
        return new APIResponse(APIResponseHeader.success(), new CollectionResponse<>(mapExerciseUserDtoList.size(), mapExerciseUserDtoList));
    }

    @DeleteMapping("/{exerciseId}/maps/{mapId}")
    public APIResponse deleteMapExerciseUser(@PathVariable("userNum") Integer userNum,
                                             @PathVariable("exerciseId") Integer exerciseId,
                                             @PathVariable("mapId") Integer mapId) {
        mapExerciseUserService.deleteMapExerciseUser(userNum, exerciseId, mapId);
        return new APIResponse(APIResponseHeader.success(), new SingleResponse<>(null));
    }
}
