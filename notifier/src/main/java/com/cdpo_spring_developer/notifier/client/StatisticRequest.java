package com.cdpo_spring_developer.notifier.client;


import com.cdpo_spring_developer.service.constants.ServiceType;
import com.cdpo_spring_developer.service.constants.StatusType;
import lombok.Getter;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class StatisticRequest {
    @Getter
    private final Long userId;
    @Getter
    List<ServiceType> order;
    @Getter
    double discountedPrice;
}
