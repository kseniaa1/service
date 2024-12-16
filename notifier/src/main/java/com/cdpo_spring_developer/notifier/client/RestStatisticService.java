package com.cdpo_spring_developer.notifier.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
public class RestStatisticService implements StatisticService {

    private final RestTemplate restTemplate;
    private final String dwhUrl;

    @Override
    public List<Long> sendStatistics(List<StatisticRequest> statistics) {
        return restTemplate.postForObject(dwhUrl + "/submit", statistics, List.class);
    }
}