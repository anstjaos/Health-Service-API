package com.health.service.api.exercise.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.health.service.api.DbUnitTestContext;
import com.health.service.api.exercise.exception.ExerciseNotFoundException;
import com.health.service.api.exercise.exception.ExerciseUserValidationException;
import com.health.service.api.exercise.exception.MapExerciseUserNotFoundException;
import com.health.service.api.exercise.model.command.model.MapExerciseUserDto;
import com.health.service.api.exercise.model.command.request.ExerciseUserCreateAndUpdateRequest;
import com.health.service.api.user.exception.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DatabaseSetup(value = {
        "/database/exercise.xml",
        "/database/service_user.xml",
        "/database/map_exercise_user.xml"
})
public class MapExerciseUserServiceTest extends DbUnitTestContext {

    @Autowired
    private MapExerciseUserService mapExerciseUserService;

    private Integer userNum;
    private Integer exerciseId;
    private Integer mapId;

    @Before
    public void setUp() {
        this.userNum = 1;
        this.exerciseId = 1;
        this.mapId = 1;
    }

    @Test
    public void success_create_user_exercise() {
        // given
        LocalDateTime now = LocalDateTime.now();
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
        request.setDate(now);
        request.setExerciseCount(15);
        request.setSetCount(5);
        // when
        mapExerciseUserService.createMapExerciseUser(userNum, exerciseId, request);
        // then
        MapExerciseUserDto dto = mapExerciseUserService.getMapExerciseUser(userNum, exerciseId, 3);
        assertEquals(now, dto.getDate());
        assertEquals(15, dto.getExerciseCount());
        assertEquals(5, dto.getSetCount());
    }

    @Test(expected = UserNotFoundException.class)
    public void fail_create_user_exercise_userNotFound() {
        // given
        Integer userNum = 1123;
        LocalDateTime now = LocalDateTime.now();
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
        request.setDate(now);
        request.setExerciseCount(15);
        request.setSetCount(5);
        // when
        mapExerciseUserService.createMapExerciseUser(userNum, exerciseId, request);
        // then
    }

    @Test(expected = ExerciseNotFoundException.class)
    public void fail_create_user_exercise_exerciseNotFound() {
        // given
        Integer exerciseId = 154545;
        LocalDateTime now = LocalDateTime.now();
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
        request.setDate(now);
        request.setExerciseCount(15);
        request.setSetCount(5);
        // when
        mapExerciseUserService.createMapExerciseUser(userNum, exerciseId, request);
        // then
    }

    @Test(expected = ExerciseUserValidationException.class)
    public void fail_create_user_exercise_validation() {
        // given
        LocalDateTime now = LocalDateTime.now();
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
        request.setDate(now);
        request.setExerciseCount(-1);
        request.setSetCount(100);
        // when
        mapExerciseUserService.createMapExerciseUser(userNum, exerciseId, request);
        // then
    }

    @Test(expected = ExerciseUserValidationException.class)
    public void fail_create_user_exercise_validation2() {
        // given
        LocalDateTime now = LocalDateTime.now();
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
        request.setDate(now);
        request.setExerciseCount(100);
        request.setSetCount(-1);
        // when
        mapExerciseUserService.createMapExerciseUser(userNum, exerciseId, request);
        // then
    }

    @Test
    public void success_update_exercise_user() {
        // given
        LocalDateTime now = LocalDateTime.now();
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
        request.setDate(now);
        request.setExerciseCount(100);
        request.setSetCount(3);
        // when
        mapExerciseUserService.updateMapExerciseUser(userNum, exerciseId, mapId, request);
        // then
        MapExerciseUserDto mapExerciseUserDto = mapExerciseUserService.getMapExerciseUser(userNum, exerciseId, mapId);

        assertEquals(now, mapExerciseUserDto.getDate());
        assertEquals(100, mapExerciseUserDto.getExerciseCount());
        assertEquals(3, mapExerciseUserDto.getSetCount());
    }

    @Test(expected = UserNotFoundException.class)
    public void fail_update_exercise_user_userNotFound() {
        // given
        Integer userNum = 1123;
        LocalDateTime now = LocalDateTime.now();
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
        request.setDate(now);
        request.setExerciseCount(100);
        request.setSetCount(3);
        // when
        mapExerciseUserService.updateMapExerciseUser(userNum, exerciseId, mapId, request);
        // then
    }

    @Test(expected = ExerciseNotFoundException.class)
    public void fail_update_exercise_user_exerciseNotFound() {
        // given
        Integer exerciseId = 4441;
        LocalDateTime now = LocalDateTime.now();
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
        request.setDate(now);
        request.setExerciseCount(100);
        request.setSetCount(3);
        // when
        mapExerciseUserService.updateMapExerciseUser(userNum, exerciseId, mapId, request);
        // then
    }

    @Test(expected = MapExerciseUserNotFoundException.class)
    public void fail_update_exercise_user_mapExerciseUserNotFound() {
        // given
        Integer mapId = 44441;
        LocalDateTime now = LocalDateTime.now();
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
        request.setDate(now);
        request.setExerciseCount(100);
        request.setSetCount(3);
        // when
        mapExerciseUserService.updateMapExerciseUser(userNum, exerciseId, mapId, request);
        // then
    }

    @Test(expected = ExerciseUserValidationException.class)
    public void fail_update_exercise_user_validation() {
        // given
        LocalDateTime now = LocalDateTime.now();
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
        request.setDate(now);
        request.setExerciseCount(-1);
        request.setSetCount(3);
        // when
        mapExerciseUserService.updateMapExerciseUser(userNum, exerciseId, mapId, request);
        // then
    }

    @Test(expected = ExerciseUserValidationException.class)
    public void fail_update_exercise_user_validation2() {
        // given
        LocalDateTime now = LocalDateTime.now();
        ExerciseUserCreateAndUpdateRequest request = new ExerciseUserCreateAndUpdateRequest();
        request.setDate(now);
        request.setExerciseCount(100);
        request.setSetCount(-1);
        // when
        mapExerciseUserService.updateMapExerciseUser(userNum, exerciseId, mapId, request);
        // then
    }

    @Test
    public void success_get_exercise_user() {
        // given
        LocalDateTime date = LocalDateTime.of(2021, 5, 25, 12, 30, 0);
        // when
        MapExerciseUserDto mapExerciseUserDto = mapExerciseUserService.getMapExerciseUser(userNum, exerciseId, mapId);
        // then
        assertEquals(date, mapExerciseUserDto.getDate());
        assertEquals(10, mapExerciseUserDto.getExerciseCount());
        assertEquals(3, mapExerciseUserDto.getSetCount());
    }

    @Test(expected = UserNotFoundException.class)
    public void fail_get_exercise_user_userNotFound() {
        // given
        userNum = 1234;
        // when
        mapExerciseUserService.getMapExerciseUser(userNum, exerciseId, mapId);
        // then
    }

    @Test(expected = ExerciseNotFoundException.class)
    public void fail_get_exercise_user_exerciseNotFound() {
        // given
        exerciseId = 1234;
        // when
        mapExerciseUserService.getMapExerciseUser(userNum, exerciseId, mapId);
        // then
    }

    @Test(expected = MapExerciseUserNotFoundException.class)
    public void fail_get_exercise_user_mapExerciseUserNotFound() {
        // given
        mapId = 1234;
        // when
        mapExerciseUserService.getMapExerciseUser(userNum, exerciseId, mapId);
        // then
    }

    @Test
    public void success_get_exercise_user_list() {
        // given
        // when
        List<MapExerciseUserDto> mapExerciseUserDtoList = mapExerciseUserService.getMapExerciseUserList(userNum);
        // then
        assertEquals(2, mapExerciseUserDtoList.size());
    }

    @Test(expected = UserNotFoundException.class)
    public void fail_get_exercise_user_list_userNotFound() {
        // given
        userNum = 1234;
        // when
        mapExerciseUserService.getMapExerciseUserList(userNum);
        // then
    }
}
