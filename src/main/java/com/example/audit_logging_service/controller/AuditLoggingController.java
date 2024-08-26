package com.example.audit_logging_service.controller;

//import com.example.audit_logging_service.service.AsyncILAuditLoggingService;
import com.example.audit_logging_service.config.PubSubOptions;
import com.example.audit_logging_service.service.KafkaPubSubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/audit")
public class AuditLoggingController {

//    @Autowired
//    private AsyncILAuditLoggingService asyncILAuditLoggingService;

//    private  DaprProducer daprProducer;

//    @PostMapping("/log")
//    public ResponseEntity<String> logAuditEvent(
//            @RequestBody Map<String, String> auditJsonMap,
//            @RequestParam String eventMsg,
//            @RequestParam String eventType,
//            @RequestParam String hostName,
//            @RequestParam String payload,
//            @RequestParam String txnId) {
//
//        try {
//            asyncILAuditLoggingService.logEvent(auditJsonMap, eventMsg, eventType, hostName, payload, txnId);
//            log.debug("Audit event logged successfully: {}", auditJsonMap);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Audit event logged successfully.");
//        } catch (Exception e) {
//            log.error("Failed to log audit event", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to log audit event.");
//        }
//    }

//    @Autowired
//    public AuditLoggingController(DaprProducer daprProducer) {
//        this.daprProducer = daprProducer;
//    }
//
//    @PostMapping("/log")
//    public Mono<Void> publishAuditLog(
//            @RequestParam String pubsubName,
//            @RequestParam String topicName,
//            @RequestBody Map<String, Object> auditLog,
//            @RequestHeader Map<String, String> headers) {
//
//        return daprProducer.publishAuditLog(pubsubName, topicName, auditLog, headers)
//                .then();
//    }
//


    @Autowired
    private KafkaPubSubService kafkaPubSubService;

    @PostMapping("/log")
    public ResponseEntity<String> logEvent(@RequestBody Map<String, String> auditJsonMap) {
        String topicName = "il-audit-log";
        PubSubOptions pubSubOptions = PubSubOptions.builder()
                .pubsubName("il-imps-audit-pubsub")
                .topic(topicName)
                .requestData(auditJsonMap)
                .build();
        kafkaPubSubService.publishMessage(pubSubOptions);
        return ResponseEntity.ok("Event published successfully");
    }
}

