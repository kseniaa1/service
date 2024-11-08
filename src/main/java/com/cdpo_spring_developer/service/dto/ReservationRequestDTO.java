package com.cdpo_spring_developer.service.dto;

import com.cdpo_spring_developer.service.constants.ServiceType;
import com.cdpo_spring_developer.service.constants.StatusType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record ReservationRequestDTO(
        @NotBlank
        @NotEmpty
        List<ServiceType> order,
        @NotEmpty
        @NotNull
        StatusType status,
        @NotEmpty
        @NotNull
        String client,
        @Future
        @NotNull
        LocalDateTime reservationDate
) {
}
