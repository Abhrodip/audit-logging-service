//package com.example.audit_logging_service.service;
//
//import io.dapr.client.DaprClient;
//import io.dapr.client.domain.PublishEventRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Mono;
//
//import java.util.Map;
//
//@Slf4j
//@Service
//public class DaprProducer {
//
//    private final DaprClient daprClient;
//
//    @Autowired
//    public DaprProducer(DaprClient daprClient) {
//        this.daprClient = daprClient;
//    }
//
//    public <T> Mono<T> publishAuditLog(String pubsubName, String topicName, Object eventData, Map<String, String> metadata) {
//        PublishEventRequest eventRequest = new PublishEventRequest(pubsubName, topicName, eventData);
//        eventRequest.setMetadata(metadata);
//
//        return (Mono<T>) daprClient.publishEvent(eventRequest)
//                .doOnSuccess(success -> log.info("Message Published successfully!!"))
//                .doOnError(throwable -> log.error("Error occurred while sending message to Kafka: {}", throwable));
//    }
//}
