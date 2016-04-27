package com.jccworld.straw.eventbus.impl;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.jccworld.straw.eventbus.EventBus;
import com.jccworld.straw.eventbus.EventBusListener;
import com.jccworld.straw.eventbus.IntentToPojoConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johncrossley on 27/11/15.
 */
public class PojoQueuedBroadcastReceiverEventBus<T> extends BroadcastReceiver implements EventBus<T> {

    private final Application application;
    private final IntentFilter intentFilter;
    private final IntentToPojoConverter<T> intentToPojoConverter;

    private List<T> queuedEvents = new ArrayList<>();
    private EventBusListener eventBusListener = null;

    public PojoQueuedBroadcastReceiverEventBus(final Application application,
                                               final IntentFilter intentFilter,
                                               final IntentToPojoConverter<T> intentToPojoConverter) {
        this.application = application;
        this.intentFilter = intentFilter;
        this.intentToPojoConverter = intentToPojoConverter;
    }

    @Override
    public void start() {
        application.registerReceiver(this, intentFilter);
    }

    @Override
    public void stop() {
        application.unregisterReceiver(this);
    }

    @Override
    public void attachListener(final EventBusListener eventBusListener) {
        this.eventBusListener = eventBusListener;
        pumpQueue();//pushEvent all items in the queue
    }

    @Override
    public void detachListener() {
        this.eventBusListener = null;
    }

    @Override
    public T get() {
        return queuedEvents.isEmpty() ? null : queuedEvents.remove(0);
    }

    private void pumpQueue() {
        while(!queuedEvents.isEmpty()) {
            T event = queuedEvents.get(0);
            queuedEvents.remove(0);

            eventBusListener.onEvent(event);
        }
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        T converted = intentToPojoConverter.convert(intent);

        if (eventBusListener == null) {
            queuedEvents.add(converted);
        } else {
            eventBusListener.onEvent(converted);
        }
    }
}
