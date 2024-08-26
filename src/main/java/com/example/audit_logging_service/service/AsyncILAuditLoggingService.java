package com.example.audit_logging_service.service;

import com.example.audit_logging_service.config.KafkaConfig;
import com.example.audit_logging_service.config.PubSubOptions;
import com.example.audit_logging_service.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class AsyncILAuditLoggingService {

    @Autowired
    private KafkaConfig kafkaConfig;

    @Autowired
    private KafkaPubSubService kafkaPubSubService;

    public void log(Map<String, String> auditJsonMap) {
        try {
            String topicName = getTopicName(kafkaConfig.getTopicName());
            log.debug("Publishing event in Kafka with pubsubTopic as {}", topicName);
            log.debug("AsyncILAuditLoggingService ... auditJsonMap...{}", auditJsonMap);
            Map<String, String> metadata = Map.of(
                    "TOPIC_PARTITIONING_KEY",
                    auditJsonMap.get("TXN_ID")
            );

            PubSubOptions pubSubOptions = PubSubOptions.builder()
                    .pubsubName(kafkaConfig.getEventPubSubName())
                    .requestData(auditJsonMap)
                    .metadata(metadata)
                    .topic(topicName)
                    .build();

            kafkaPubSubService.publishMessage(pubSubOptions);
            log.debug("Successfully Published event in Kafka with pubsubTopic as {}", topicName);

        } catch (Exception e) {
            log.error("Error storing audit log: ", e);
        }
    }

    private String getTopicName(String topicName) {
        return topicName;
    }

    public void logEvent(Map<String, String> auditJsonMap, String eventMsg, String eventType, String hostName, String payload, String txnId) {
        auditJsonMap.put("TXN_ID", txnId);
        auditJsonMap.put("AUDIT_TYPE", eventType);
        auditJsonMap.put("TYPE_EVENT", eventMsg + "_" + hostName);
        auditJsonMap.put("PAYLOAD", payload);
//        auditJsonMap.put("PHYSICAL_TIME_EVENT", DateTimeUtil.getCurrentDateTime(kafkaConfig.getTimezone()).toString());
        this.log(new HashMap<>(auditJsonMap));
    }
}

