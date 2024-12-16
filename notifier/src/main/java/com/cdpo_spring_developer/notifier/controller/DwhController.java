package com.cdpo_spring_developer.notifier.controller;


import com.cdpo_spring_developer.notifier.client.StatisticRequest;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/dwh")
class DwhController {

    @PostMapping("/submit")
    public List<Long> receiveStatistics(@RequestBody List<StatisticRequest> statistics) {
        return statistics.stream()
                .map(req -> req.getUserId())
                .toList();
    }
}