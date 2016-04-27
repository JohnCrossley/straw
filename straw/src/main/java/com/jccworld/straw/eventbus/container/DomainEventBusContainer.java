package com.jccworld.straw.eventbus.container;

import com.jccworld.straw.eventbus.EventBus;
import com.jccworld.straw.eventbus.producer.EventBusProducer;

/**
 * Created by johncrossley on 10/01/16.
 */
public class DomainEventBusContainer<T> {
    private final EventBus<T> consumer;
    private final EventBusProducer producer;

    public DomainEventBusContainer(final EventBus<T> consumer, final EventBusProducer producer) {
        this.consumer = consumer;
        this.producer = producer;
    }

    public EventBus<T> getConsumer() {
        return consumer;
    }

    public EventBusProducer<T> getProducer() {
        return producer;
    }
}
