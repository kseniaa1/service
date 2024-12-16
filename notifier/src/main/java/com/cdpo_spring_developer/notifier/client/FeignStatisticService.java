package com.cdpo_spring_developer.notifier.client;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FeignStatisticService implements StatisticService {

    private final FeignStatisticClient feignClient;

    @Override
    public List<Long> sendStatistics(List<StatisticRequest> statistics) {
        return feignClient.sendStatistics(statistics);
    }
}

