package com.example.audit_logging_service.config;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class BindingOptions {
    private String bindingName;
    private String method;
    private Object requestData;
    private Class<?> responseType;
    private String id;
    private Map<String, String> metadata;
}

