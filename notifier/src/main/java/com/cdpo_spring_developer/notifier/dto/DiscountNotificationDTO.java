package com.cdpo_spring_developer.notifier.dto;

public record DiscountNotificationDTO(
        Long userId,
        double discount
) {
}
