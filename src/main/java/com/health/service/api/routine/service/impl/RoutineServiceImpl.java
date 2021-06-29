package com.health.service.api.routine.service.impl;

import com.health.service.api.routine.entity.ExerciseRoutineEntity;
import com.health.service.api.routine.exception.CreateRoutineRequestException;
import com.health.service.api.routine.model.command.request.CreateRoutineRequest;
import com.health.service.api.routine.repository.RoutineRepository;
import com.health.service.api.routine.service.RoutineService;
import com.health.service.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RoutineServiceImpl implements RoutineService {

    private final UserService userService;
    private final RoutineRepository routineRepository;

    @Autowired
    public RoutineServiceImpl(UserService userService,
                              RoutineRepository routineRepository) {
        this.userService = userService;
        this.routineRepository = routineRepository;
    }

    @Override
    @Transactional
    public Integer createRoutine(Integer userNum, CreateRoutineRequest request) {
        // check valid user
        userService.getUser(userNum);

        // check valid request
        checkCreateRoutineRequest(request);

        ExerciseRoutineEntity routineEntity = new ExerciseRoutineEntity();
        routineEntity.setRoutineName(request.getRoutineName());
        routineEntity.setUserNum(userNum);
        routineEntity.setDayOfWeek(request.getDayOfWeek());

        routineEntity = routineRepository.save(routineEntity);
        return routineEntity.getRoutineId();
    }

    private void checkCreateRoutineRequest(CreateRoutineRequest request) {
        Optional.ofNullable(request.getRoutineName())
                .orElseThrow(() -> new CreateRoutineRequestException("routine name must be not null!"));

        Optional.ofNullable(request.getDayOfWeek())
                .orElseThrow(() -> new CreateRoutineRequestException("routine day of week must be not null!"));

        if (request.getDayOfWeek() < 0 || request.getDayOfWeek() > 6) throw new CreateRoutineRequestException("routine day of week value must set between 0, 6");
    }
}
