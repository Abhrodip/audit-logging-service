package com.example.audit_logging_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class AuditLoggingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditLoggingServiceApplication.class, args);
	}

}
