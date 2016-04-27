package com.jccworld.straw.eventbus.container;

import com.jccworld.straw.eventbus.EventBus;
import com.jccworld.straw.eventbus.producer.EventBusProducer;
import com.jccworld.straw.eventbus.producer.PojoEventBusProducer;

/**
 * Created by johncrossley on 10/01/16.
 */
public class PojoEventBusContainer<T> {
    private final EventBus<T> consumer;
    private final PojoEventBusProducer<T> producer;

    public PojoEventBusContainer(final EventBus consumer, PojoEventBusProducer producer) {
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
