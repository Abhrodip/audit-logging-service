package com.example.audit_logging_service.config;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.audit_logging_service")
public class Daprconfig {

    @Bean
    public DaprClient daprClient() {
        return new DaprClientBuilder().build();
    }
}
