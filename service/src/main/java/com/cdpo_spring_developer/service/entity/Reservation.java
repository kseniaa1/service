package com.cdpo_spring_developer.service.entity;

import com.cdpo_spring_developer.service.constants.StatusType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@Table(name="reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToMany
    @JoinTable(name = "order")
    private List<Services> order;

    @Column(name = "status")
    private StatusType status;

    @Column(name = "reservation_date")
    private  LocalDateTime reservationDate;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "discount")
    private double discount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private ApplicationUser user_id;


}


