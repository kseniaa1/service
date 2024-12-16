package com.cdpo_spring_developer.notifier.client;

import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "dwhClient", url = "${dwh.url}")
public interface FeignStatisticClient {
    @PostMapping("/submit")
    List<Long> sendStatistics(@RequestBody List<StatisticRequest> statistics);
}
