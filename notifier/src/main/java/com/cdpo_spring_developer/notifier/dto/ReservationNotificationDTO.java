package com.cdpo_spring_developer.notifier.dto;

import com.cdpo_spring_developer.service.constants.StatusType;


public record ReservationNotificationDTO(
        StatusType statusType,
        Long userId
) {
}