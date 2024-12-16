package com.cdpo_spring_developer.notifier.service;

import com.cdpo_spring_developer.notifier.client.StatisticRequest;
import com.cdpo_spring_developer.notifier.client.StatisticService;
import com.cdpo_spring_developer.service.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@RequiredArgsConstructor
@Component
public class StatisticScheduler {

    private final StatisticService statisticService;
    private final ReservationService reservationService;


    @Scheduled(cron = "0 0 0 * * ?") // Каждая полночь
    public void sendStatisticsTask() {
        List<StatisticRequest> statistics = reservationService.getDoneReservations();
        List<Long> response;

        do {
            response = statisticService.sendStatistics(statistics);
            List<Long> finalResponse = response;
            statistics.removeIf(stat -> finalResponse.contains(stat.getUserId()));
        } while (!statistics.isEmpty());
    }
}
