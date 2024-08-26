package com.example.audit_logging_service.Dapr.producer;

import com.example.audit_logging_service.config.BindingOptions;
import com.example.audit_logging_service.config.PubSubOptions;
import io.dapr.client.DaprClient;
import io.dapr.client.domain.PublishEventRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class DaprProducer implements DaprMessageClient {

    private final DaprClient daprClient;

    @Autowired
    public DaprProducer(DaprClient daprClient) {
        this.daprClient = daprClient;
    }
//    @Autowired
//    @Qualifier("daprClientMessageConnect")
//    private DaprClient daprClient;

    @Override
    public <T> Mono<T> invokeDaprBinding(BindingOptions bindingOptions) {
        Mono<T> response= (Mono<T>) daprClient.invokeBinding(bindingOptions.getBindingName(),
                bindingOptions.getMethod(),
                bindingOptions.getRequestData(),
                bindingOptions.getMetadata(),
                bindingOptions.getResponseType());
        response.doOnSuccess(success -> {
            log.info("Message Published successfully!!");
        }).onErrorResume(throwable -> {
            log.error("Error occurred while sending message to Kafka: {}", throwable);
            var err=Mono.error(throwable);
            return (Mono<T>) err;
        });
        return response;
    }

    @Override
    public <T> Mono<T> invokeDaprPublishEvent(PubSubOptions pubSubOptions) {
        PublishEventRequest eventRequest= new PublishEventRequest(pubSubOptions.getPubsubName(),pubSubOptions.getTopic(),pubSubOptions.getRequestData());
        eventRequest.setMetadata(pubSubOptions.getMetadata());

        Mono<T> response= (Mono<T>) daprClient.publishEvent(eventRequest)
                .doOnSuccess(success -> {
                    log.info("Message Published successfully!!");
                })
                .onErrorResume(throwable -> {
                    log.error("Error occurred while sending message to Kafka: {}", throwable);
                    var err=Mono.error(throwable);
                    return ((Mono<T>) err).then();
                });
        return response;
    }
}
