package com.health.service.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Map_Exercise_Routine")
@Getter
@Setter
public class MapExerciseRoutineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "map_id")
    private Integer mapId;

    @Column(name = "exercise_id")
    private Integer exerciseId;

    @Column(name = "routine_id")
    private Integer routineId;
}
