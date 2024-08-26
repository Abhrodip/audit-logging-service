package com.example.audit_logging_service.config;


import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class PubSubOptions {
    private String pubsubName;
    private Map<String, String> requestData;
    private Map<String, String> metadata;
    private String topic;
}

