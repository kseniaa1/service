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
import org.springframework.security.access.annotation.Secured;
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

    @Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
    @GetMapping
    public List<ReservationResponseDTO> getReservationsByDate(@RequestParam LocalDateTime reservationDate) {
        log.info("GET request with reservations with date = {}", reservationDate);
        try {
            return reservationService.getReservationsByDate(reservationDate);
        } catch (ServiceException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }
    @Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
    @GetMapping
    public Map<String, Integer> getIncomeByPeriod(@RequestParam LocalDateTime dateFrom, @RequestParam LocalDateTime dateTo) {
        return reservationService.getIncomeByPeriod(dateFrom, dateTo);
    }

    @Secured("ROLE_USER")
    @PostMapping
    public ResponseEntity<?> registerReservation(@Valid @RequestBody ReservationRequestDTO reservationRequest,
                                                 HttpServletRequest request) {
        log.debug("");
        URI uri = URI.create("/api/v1/reservations?id" +
                reservationService.registerReservation(reservationRequest));
        return ResponseEntity.created(uri).build();
    }

    @Secured("ROLE_OPERATOR")
    @PutMapping
    public ResponseEntity<?> cancelReservation(@Positive @RequestParam int id) {
        log.debug("");
        return new ResponseEntity<>(reservationService.cancelReservation(id), HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @GetMapping
    public List<ReservationResponseDTO> getReservationsList(@RequestParam @Positive int id) {
        log.info("");
        return reservationService.getReservationsList(id);
    }

    @Secured({"ROLE_OPERATOR", "ROLE_ADMIN"})
    @PutMapping
    public ReservationResponseDTO setDiscount(@Positive @RequestParam int id, @RequestParam double discount) {
        log.info("Set discount to reservation {}", id);
        return reservationService.setDiscount(id, discount);
    }

    @Secured({"ROLE_USER", "ROLE_OPERATOR", "ROLE_ADMIN"})
    @PutMapping
    public ReservationResponseDTO editReservation(@Positive @RequestParam int id, @Valid @RequestBody ReservationRequestDTO reservationRequest) {
        log.info("Update reservation {}", id);
        return reservationService.editReservation(id, reservationRequest);
    }
}

