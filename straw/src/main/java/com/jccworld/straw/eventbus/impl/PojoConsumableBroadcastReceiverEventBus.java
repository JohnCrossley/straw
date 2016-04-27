package com.jccworld.straw.eventbus.impl;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.jccworld.straw.eventbus.EventBus;
import com.jccworld.straw.eventbus.EventBusListener;
import com.jccworld.straw.eventbus.IntentToPojoConverter;

/**
 * Created by johncrossley on 27/11/15.
 */
public class PojoConsumableBroadcastReceiverEventBus<T> extends BroadcastReceiver implements EventBus<T> {

    private final Application application;
    private final IntentFilter intentFilter;
    private final IntentToPojoConverter<T> intentToPojoConverter;

    private T event = null;
    private EventBusListener eventBusListener = null;

    public PojoConsumableBroadcastReceiverEventBus(final Application application,
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
        T converted = intentToPojoConverter.convert(intent);

        event = converted;
        pumpEvent();
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
