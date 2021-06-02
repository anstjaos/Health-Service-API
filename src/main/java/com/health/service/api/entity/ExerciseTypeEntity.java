package com.health.service.api.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// this entity is read-only
@Entity
@Table(name = "Exercise_Type")
@Getter
public class ExerciseTypeEntity {

    @Id
    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "type_name")
    private String typeName;
}
