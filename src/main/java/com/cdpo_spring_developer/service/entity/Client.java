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
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole userRole;
}
