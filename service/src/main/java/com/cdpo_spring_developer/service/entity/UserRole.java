package com.cdpo_spring_developer.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public enum RoleType {

        ROLE_USER,
        ROLE_ADMIN,
        ROLE_OPERATOR
    }
}


