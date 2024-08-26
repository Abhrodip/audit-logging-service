package com.example.audit_logging_service.Dapr.producer;

import com.example.audit_logging_service.config.BindingOptions;
import com.example.audit_logging_service.config.PubSubOptions;
import reactor.core.publisher.Mono;

public interface DaprMessageClient {
    /**
     * @param bindingOptions
     * @param <T>
     * @return
     */
    public <T> Mono<T> invokeDaprBinding(BindingOptions bindingOptions);

    /**
     *
     * @param pubSubOptions
     * @param <T>
     * @return
     */
    public <T> Mono<T> invokeDaprPublishEvent(PubSubOptions pubSubOptions) ;

}

