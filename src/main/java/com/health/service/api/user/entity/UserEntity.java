package com.health.service.api.user.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Service_User")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "user_num")
    @Setter(AccessLevel.NONE)
    private Integer userNum;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickName;

    @Column(name = "created_on", updatable = false)
    private LocalDateTime createdOn;

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @Column(name = "email")
    private String email;

    @Column(name = "token")
    private String token;
}
