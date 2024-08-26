package com.example.audit_logging_service.service;

import com.example.audit_logging_service.Dapr.producer.DaprProducer;
import com.example.audit_logging_service.config.PubSubOptions;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KafkaPubSubService {

    private static final Logger log=org.slf4j.LoggerFactory.getLogger(KafkaPubSubService.class);
//    private final KafkaTemplate<String, Map<String, String>> kafkaTemplate;


    @Autowired
    private DaprProducer daprProducer;


//    public KafkaPubSubService(KafkaTemplate<String, Map<String, String>> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }

    public void publishMessage(PubSubOptions pubsubOptions) {
//        kafkaTemplate.send(pubSubOptions.getTopic(), pubSubOptions.getMetadata().get("TXN_ID"), pubSubOptions.getRequestData());
        log.debug("publishMessage : ", pubsubOptions);
        daprProducer.invokeDaprPublishEvent(pubsubOptions)
                .subscribe(resp ->log.debug("Pubsub Mono Complete"));
    }
}

