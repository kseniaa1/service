package com.cdpo_spring_developer.service.dto;

import com.cdpo_spring_developer.service.constants.ServiceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

@Validated
public record ServiceRequestDTO(
    @NotEmpty
    @NotBlank
    ServiceType name,
    @NotEmpty
    @NotBlank
    int price
) {
}
