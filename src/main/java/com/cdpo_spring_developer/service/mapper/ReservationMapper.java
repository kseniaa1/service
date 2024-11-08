package com.cdpo_spring_developer.service.mapper;

import com.cdpo_spring_developer.service.dto.*;
import com.cdpo_spring_developer.service.entity.Reservation;
import org.springframework.stereotype.Service;

@Service
public class ReservationMapper {
    public Reservation mapToEntity(ReservationRequestDTO reservationRequest) {
        return Reservation.builder()
                .order(reservationRequest.order())
                .status(reservationRequest.status())
                .client(reservationRequest.client())
                .reservationDate(reservationRequest.reservationDate())
                .build();

    }
    public ReservationResponseDTO mapToDTO(Reservation reservation) {
        return new ReservationResponseDTO(
                reservation.getOrder(),
                reservation.getStatus(),
                reservation.getClient(),
                reservation.getReservationDate()
        );
    }
}
