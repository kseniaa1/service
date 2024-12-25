package com.example.tests.client;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@TestConfiguration
public class ResClientTestConfig {

    @Bean
    public RestClient restClient(RestClient.Builder builder){
        return builder.build();
    }
}
