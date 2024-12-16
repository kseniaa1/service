package com.cdpo_spring_developer.service.mapper;

import com.cdpo_spring_developer.notifier.client.StatisticRequest;
import com.cdpo_spring_developer.service.dto.*;
import com.cdpo_spring_developer.service.entity.Reservation;
import com.cdpo_spring_developer.service.entity.Services;
import com.cdpo_spring_developer.service.repository.AccountRepository;
import com.cdpo_spring_developer.service.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservationMapper {
    private final AccountRepository accountRepository;
    private final ServiceRepository serviceRepository;
    public Reservation mapToEntity(ReservationRequestDTO reservationRequest) {
        return Reservation.builder()
                .order(reservationRequest.order()
                        .stream().map(serviceRepository::findByServiceType).toList())
                .status(reservationRequest.status())
                .user_id(accountRepository.findById(reservationRequest.client_id())
                        .orElseThrow(() -> new ServiceException("Запись не найлена")))
                .reservationDate(reservationRequest.reservationDate())
                .totalPrice(reservationRequest.totalPrice())
                .build();

    }
    public ReservationResponseDTO mapToDTO(Reservation reservation) {
        return new ReservationResponseDTO(
                reservation.getOrder().stream().map(Services::getServiceType).toList(),
                reservation.getStatus(),
                reservation.getUser_id().getId(),
                reservation.getReservationDate(),
                reservation.getDiscount(),
                reservation.getTotalPrice()
        );
    }
    public StatisticRequest mapToRequest(ReservationResponseDTO res) {
        return new StatisticRequest(
                res.client(),
                res.order(),
                res.totalPrice()*res.discount()
        );
    }
}
