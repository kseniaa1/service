package com.cdpo_spring_developer.notifier.controller;

import com.cdpo_spring_developer.notifier.dto.DiscountNotificationDTO;
import com.cdpo_spring_developer.notifier.dto.ReservationNotificationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cdpo_spring_developer.notifier.dto.AdvNotification;
import com.cdpo_spring_developer.notifier.dto.UserNotification;
import com.cdpo_spring_developer.notifier.exception.NotificationException;
import com.cdpo_spring_developer.notifier.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notifier")
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/reservation")
    public ResponseEntity<Void> notifyReservationStatus(@RequestBody ReservationNotificationDTO notificationDTO){
        if (notificationDTO.statusType() == null || notificationDTO.userId() == null)
            return ResponseEntity.badRequest().build();
        try {
            notificationService.sendReservationStatusNotification(notificationDTO);
            return ResponseEntity.ok().build();
        } catch (NotificationException e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/discount")
    public ResponseEntity<Void> notifyExcuseDiscount(@RequestBody DiscountNotificationDTO notificationDTO){
        if (notificationDTO.userId() == null) return ResponseEntity.badRequest().build();
        try {
            notificationService.sendExcuseDiscount(notificationDTO);
            return ResponseEntity.ok().build();
        } catch (NotificationException e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
