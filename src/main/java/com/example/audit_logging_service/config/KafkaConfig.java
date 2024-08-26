package com.example.audit_logging_service.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pubsub")
public class KafkaConfig {

//    private String topicName;
//    private String topicPrefix;
//    private String eventPubSubName;
//
//    private String timezone; // Added property for timezone
//
//
//    // Getters and Setters
//    public String getTopicName() {
//        return topicName;
//    }
//
//    public void setTopicName(String topicName) {
//        this.topicName = topicName;
//    }
//
//    public String getTopicPrefix() {
//        return topicPrefix;
//    }
//
//    public void setTopicPrefix(String topicPrefix) {
//        this.topicPrefix = topicPrefix;
//    }
//
//    public String getEventPubSubName() {
//        return eventPubSubName;
//    }
//
//    public void setEventPubSubName(String eventPubSubName) {
//        this.eventPubSubName = eventPubSubName;
//    }
//
//    public String getTimezone() {
//        return timezone; // Getter for timezone
//    }
//
//    public void setTimezone(String timezone) {
//        this.timezone = timezone; // Setter for timezone
//    }

    @Value("${pubsub.il.log.eventPubSubName}")
    private String eventPubSubName;

    @Value("${pubsub.il.log.topicName}")
    private String topicName;

//    @Value("${pubsub.il.log.topicPrefix}")
//    private String topicPrefix;

    // Getters for the injected properties
    public String getEventPubSubName() {
        return eventPubSubName;
    }

    public String getTopicName() {
        return topicName;
    }

//    public String getTopicPrefix() {
//        return topicPrefix;
//    }
}

