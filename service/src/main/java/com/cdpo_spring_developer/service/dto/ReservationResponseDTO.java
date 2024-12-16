package com.cdpo_spring_developer.service.dto;

import com.cdpo_spring_developer.service.constants.ServiceType;
import com.cdpo_spring_developer.service.constants.StatusType;

import java.time.LocalDateTime;
import java.util.List;

public record
ReservationResponseDTO (
        List<ServiceType> order,
        StatusType status,
        long client,
        LocalDateTime reservationDate,
        double discount,
        double totalPrice
){
}
