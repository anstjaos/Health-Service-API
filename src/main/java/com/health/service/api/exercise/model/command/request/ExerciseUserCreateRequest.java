package com.health.service.api.exercise.model.command.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExerciseUserCreateRequest {

    private LocalDateTime date;

    private Integer exerciseCount;

    private Integer setCount;
}
