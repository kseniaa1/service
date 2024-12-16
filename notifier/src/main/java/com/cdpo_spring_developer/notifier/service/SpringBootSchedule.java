package com.cdpo_spring_developer.notifier.service;

import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cdpo_spring_developer.notifier.dto.AdvNotification;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@EnableScheduling // возможность запускать методы по расписанию
public class SpringBootSchedule {
    private final NotificationService notificationService;

    /**
     * Метод вызывается по расписанию (раз в 7 дней), согласно настройкам,
     * описанным в параметрах аннотации Scheduled
     * Метод управляется отдельным пулом благодаря аннотации @Async
     * */
    @Async("notifier-executor") // задачи будут выполняться отдельным пулом
    @Scheduled(fixedRate = 7, timeUnit = TimeUnit.DAYS)
    public void runBySchedule(){
        notificationService.sendNotification(new AdvNotification("Посетите наш приют..."));
    }
}