package com.cdpo_spring_developer.service.dto;

import java.time.LocalDateTime;

public record ServiceErrorDTO(String message, LocalDateTime timestamp) {
}
