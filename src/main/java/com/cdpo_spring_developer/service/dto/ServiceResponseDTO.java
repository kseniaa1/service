package com.cdpo_spring_developer.service.dto;

import com.cdpo_spring_developer.service.constants.ServiceType;

public record ServiceResponseDTO (
    ServiceType name,
    double price,
    double duration
){
}
