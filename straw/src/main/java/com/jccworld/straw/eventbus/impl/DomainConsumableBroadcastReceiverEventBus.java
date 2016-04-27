package com.jccworld.straw.eventbus.impl;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.jccworld.straw.eventbus.EventBus;
import com.jccworld.straw.eventbus.EventBusListener;
import com.jccworld.straw.eventbus.DomainEventBus;

/**
 * Created by johncrossley on 27/11/15.
 */
public class DomainConsumableBroadcastReceiverEventBus<T> extends BroadcastReceiver implements EventBus<T>, DomainEventBus<T> {
    private final Application application;
    private final IntentFilter intentFilter;

    private T event = null;
    private EventBusListener eventBusListener = null;

    public DomainConsumableBroadcastReceiverEventBus(final Application application,
                                                     final IntentFilter intentFilter) {
        this.application = application;
        this.intentFilter = intentFilter;
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

        pumpEvent();
    }

    @Override
    public void detachListener() {
        this.eventBusListener = null;
    }

    @Override
    public T get() {
        T event = this.event;
        this.event = null;//consumed

        return event;
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        pumpEvent();
    }

    @Override
    public void pushEvent(T t) {
        event = t;
    }

    private void pumpEvent() {
        if (event == null || eventBusListener == null) {
            return;
        }

        T event = this.event;
        this.event = null;//consumed

        eventBusListener.onEvent(event);
    }
}
