package com.health.service.api.routine.model.command.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoutineRequest {

    private String routineName;

    private Integer dayOfWeek;
}
