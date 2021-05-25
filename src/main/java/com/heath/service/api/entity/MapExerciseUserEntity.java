package com.heath.service.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Map_Exercise_User")
@Getter
@Setter
public class MapExerciseUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "map_id")
    private Integer mapId;

    @Column(name = "exercise_id")
    private Integer exerciseId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "exercise_count")
    private Integer exerciseCount;

    @Column(name = "set_count")
    private Integer setCount;
}
