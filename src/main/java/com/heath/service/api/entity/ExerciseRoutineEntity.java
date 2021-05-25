package com.heath.service.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Exercise_Routine")
@Getter
@Setter
public class ExerciseRoutineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Integer routineId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "day_of_week")
    private Integer dayOfWeek;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;
}
