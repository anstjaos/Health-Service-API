package com.health.service.api.routine.entity;

import com.health.service.api.user.entity.UserEntity;
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

    @Column(name = "user_num")
    private Integer userNum;

    @Column(name = "routine_name")
    private String routineName;

    @Column(name = "day_of_week")
    private Integer dayOfWeek;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_num", updatable = false, insertable = false)
    private UserEntity user;
}
