package com.health.service.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Exercise")
@Getter
@Setter
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private Integer exerciseId;

    @Column(name = "name")
    private String exerciseName;

    @Column(name = "exercise_type_id")
    private Integer exerciseTypeId;

    @Column(name = "body_part_id")
    private Integer bodyPartId;

    @ManyToOne(fetch = FetchType.LAZY)
    private ExerciseTypeEntity exerciseType;

    @ManyToOne(fetch = FetchType.LAZY)
    private BodyPartEntity bodyPart;
}
