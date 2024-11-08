package com.cdpo_spring_developer.service.service;

import com.cdpo_spring_developer.service.dto.*;
import com.cdpo_spring_developer.service.entity.Reservation;
import com.cdpo_spring_developer.service.mapper.ReservationMapper;
import com.cdpo_spring_developer.service.repository.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;
    private final ServiceRepository serviceRepository;


    public Long registerReservation(ReservationRequestDTO reservationRequestDTO) {
        // TODO:: add to db
        return 1L;
    }
    public Long cancelReservation(int id) {
        // TODO:: change statusType to CANCELLED
        return 1L;
    }
    public Long editReservationDate(ReservationRequestDTO editRequest) {
        // TODO:: change db
        return 1L;
    }

    public List<ReservationResponseDTO> getReservationsByDate(LocalDateTime reservationDate) {
        List<Reservation> reservations = reservationRepository.findAllByReservationDateAndStatusPlanned(reservationDate.toString());
        if (reservations.isEmpty()) throw new ServiceException("Записи не найдена");
        return reservations.stream().map(reservationMapper::mapToDTO).toList();
    }

    public List<String> getReservationsList() {
        return serviceRepository.findCategories();
    }
}
