package com.jccworld.straw.eventbus.producer;

/**
 * Service-side: provides data for the event bus
 *
 * Created by johncrossley on 08/01/16.
 */
public interface EventBusProducer<T> {

    void send(T t);

}
