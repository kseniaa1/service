package com.cdpo_spring_developer.service.service;

import com.cdpo_spring_developer.service.constants.ServiceType;
import com.cdpo_spring_developer.service.constants.StatusType;
import com.cdpo_spring_developer.service.dto.ReservationRequestDTO;
import com.cdpo_spring_developer.service.dto.ReservationResponseDTO;
import com.cdpo_spring_developer.service.entity.Reservation;
import com.cdpo_spring_developer.service.mapper.ReservationMapper;
import com.cdpo_spring_developer.service.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.cdpo_spring_developer.service.constants.StatusType.CANCELLED;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;


    public ReservationResponseDTO registerReservation(ReservationRequestDTO reservationRequestDTO) {
        Reservation reservation = reservationMapper.mapToEntity(reservationRequestDTO);
        return reservationMapper.mapToDTO(reservationRepository.save(reservation));
    }
    public Long cancelReservation(int id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById((long) id);
        if (optionalReservation.isEmpty())  throw new ServiceException("Запись не найдена");
        Reservation reservation = optionalReservation.get();
        reservationRepository.updateStatus(id, CANCELLED);
        return 1L;
    }

    public List<ReservationResponseDTO> getReservationsByDate(LocalDateTime reservationDate) {
        List<Reservation> reservations = reservationRepository.findAllByReservationDateAndStatusPlanned(reservationDate.toString());
        if (reservations.isEmpty()) throw new ServiceException("Записи не найдены");
        return reservations.stream().map(reservationMapper::mapToDTO).toList();
    }

    public List<ReservationResponseDTO> getReservationsList(int  id) {
        return reservationRepository.findAllByUserId(id).stream().map(reservationMapper::mapToDTO).toList();
    }

    public ReservationResponseDTO setDiscount(int id, double newDiscount) {
       Optional<Reservation> reservationOptional = reservationRepository.findById((long) id);
        if (reservationOptional.isEmpty()) throw new ServiceException("Запись не найдена");
        Reservation reservation = reservationOptional.get();
        double totalPrice = reservation.getTotalPrice();
        double discount = reservation.getDiscount();
        double newTotalPrice = totalPrice/(1-discount)*(1+newDiscount);
        Reservation newReservation = reservationRepository.updateDiscount(id, newDiscount, newTotalPrice);
        return reservationMapper.mapToDTO(newReservation);
    }

    public ReservationResponseDTO editReservation(long id, ReservationRequestDTO reservationRequest) {
        List<ServiceType> order = reservationRequest.order();
        StatusType status = reservationRequest.status();
        long client_id = reservationRequest.client_id();
        String date = reservationRequest.reservationDate().toString();
        Reservation reservation = reservationRepository.updateReservation(id, order, status, client_id, date);
        return reservationMapper.mapToDTO(reservation);
    }

    public Map<String, Integer> getIncomeByPeriod(LocalDateTime dateFrom, LocalDateTime dateTo){
        return reservationRepository.getIncomeByPeriod(dateFrom.toString(), dateTo.toString());
    }
}
