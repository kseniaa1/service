package com.cdpo_spring_developer.service.controller;

import com.cdpo_spring_developer.service.dto.*;
import com.cdpo_spring_developer.service.exceptions.ServiceException;
import com.cdpo_spring_developer.service.service.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("api/v1/reservation")
@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public List<ReservationResponseDTO> getReservationsByDate(@RequestParam LocalDateTime reservationDate) {
        log.info("GET request with reservations with date = {}", reservationDate);
        try {
            return reservationService.getReservationsByDate(reservationDate);
        } catch (ServiceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    public Map<LocalDateTime, Integer> getIncomeByPeriod(@RequestParam LocalDateTime dateFrom, LocalDateTime dateTo) {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> registerReservation(@Valid @RequestBody ReservationRequestDTO reservationRequest,
                                                 HttpServletRequest request) {
        log.debug("");
        URI uri = URI.create("/api/v1/reservations?id" +
                reservationService.registerReservation(reservationRequest));
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<?> cancelReservation(@Positive @RequestParam int id) {
        log.debug("");
        return new ResponseEntity<>(reservationService.cancelReservation(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> editReservationDate(@Valid @RequestBody ReservationRequestDTO editRequest) {
        log.info("");
        return new ResponseEntity<>(reservationService.editReservationDate(editRequest), HttpStatus.OK);
    }

    @GetMapping
    public List<String> getReservationsList() {
        log.info("");
        return reservationService.getReservationsList();
    }
}

