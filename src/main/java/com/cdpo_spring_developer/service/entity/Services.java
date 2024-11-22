package com.cdpo_spring_developer.service.entity;

import com.cdpo_spring_developer.service.constants.ServiceType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@Table(name="service")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "category")
    private ServiceType serviceType;

    @Column(name = "price")
    private double price;

    @Column(name = "duration")
    private double duration;

}
