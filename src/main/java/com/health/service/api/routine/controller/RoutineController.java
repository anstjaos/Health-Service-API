package com.health.service.api.routine.controller;

import com.health.service.api.common.model.APIResponse;
import com.health.service.api.common.model.APIResponseHeader;
import com.health.service.api.common.model.CollectionResponse;
import com.health.service.api.common.model.SingleResponse;
import com.health.service.api.routine.model.command.model.RoutineDto;
import com.health.service.api.routine.model.command.request.CreateRoutineRequest;
import com.health.service.api.routine.model.command.request.UpdateRoutineRequest;
import com.health.service.api.routine.service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userNum}")
public class RoutineController {

    private final RoutineService routineService;

    @Autowired
    public RoutineController(RoutineService routineService) {
        this.routineService = routineService;
    }

    @PostMapping("/routines")
    public APIResponse createRoutine(@PathVariable("userNum") Integer userNum,
                                     @RequestBody CreateRoutineRequest request) {
        Integer routineId = routineService.createRoutine(userNum, request);
        return new APIResponse(APIResponseHeader.success(), new SingleResponse<>(routineId));
    }

    @PutMapping("/routines/{routineId}")
    public APIResponse updateRoutine(@PathVariable("userNum") Integer userNum,
                                     @PathVariable("routineId") Integer routineId,
                                     @RequestBody UpdateRoutineRequest request) {
        routineService.updateRoutine(userNum, routineId, request);
        return new APIResponse(APIResponseHeader.success(), new SingleResponse<>(null));
    }

    @GetMapping("/routines/{routineId}")
    public APIResponse getRoutine(@PathVariable("userNum") Integer userNum,
                                  @PathVariable("routineId") Integer routineId) {
        RoutineDto routineDto = routineService.getRoutine(userNum, routineId);
        return new APIResponse(APIResponseHeader.success(), new SingleResponse<>(routineDto));
    }

    @DeleteMapping("/routines/{routineId}")
    public APIResponse deleteRoutine(@PathVariable("userNum") Integer userNum,
                                     @PathVariable("routineId") Integer routineId) {
        routineService.deleteRoutine(userNum, routineId);
        return new APIResponse(APIResponseHeader.success(), new SingleResponse<>(null));
    }

    @GetMapping("/routines")
    public APIResponse getRoutineList(@PathVariable("userNum") Integer userNum) {
        List<RoutineDto> routineList = routineService.getRoutineList(userNum);
        return new APIResponse(APIResponseHeader.success(), new CollectionResponse<>(routineList.size(), routineList));
    }
}
