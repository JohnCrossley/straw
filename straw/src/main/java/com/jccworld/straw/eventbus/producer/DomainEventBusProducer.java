package com.jccworld.straw.eventbus.producer;

import android.content.Context;
import android.content.Intent;

import com.jccworld.straw.eventbus.DomainEventBus;

/**
 * Created by johncrossley on 08/01/16.
 */
public class DomainEventBusProducer<T> implements EventBusProducer<T> {

    private final DomainEventBus<T> eventBus;
    private final Context application;
    private final String uuid;

    public DomainEventBusProducer(final DomainEventBus<T> eventBus, final Context application, final String uuid) {
        this.eventBus = eventBus;
        this.application = application;
        this.uuid = uuid;
    }

    @Override
    public void send(T t) {
        //pushEvent object to event bus
        eventBus.pushEvent(t);

        //pushEvent intent that just indicates they need to request the next object from the EventBus
        application.sendBroadcast(new Intent(uuid));
    }
}
