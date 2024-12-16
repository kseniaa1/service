package com.cdpo_spring_developer.notifier.client;

import java.util.List;

public interface StatisticService {
    List<Long> sendStatistics(List<StatisticRequest> statistics);
}