package com.heath.service.api.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// this entity is read-only
@Entity
@Getter
@Table(name = "Body_Part")
public class BodyPartEntity {

    @Id
    @Column(name = "body_part_id")
    private Integer bodyPartId;

    @Column(name = "body_part_name")
    private String bodyPartName;
}
