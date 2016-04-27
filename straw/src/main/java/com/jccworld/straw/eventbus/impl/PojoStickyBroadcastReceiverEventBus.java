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
public class PojoStickyBroadcastReceiverEventBus<T> extends BroadcastReceiver implements EventBus<T> {

    private final Application application;
    private final IntentFilter intentFilter;
    private final IntentToPojoConverter<T> intentToPojoConverter;

    private StickyEventSnapshot<T> eventSnapshot = null;

    private EventBusListener eventBusListener = null;

    public PojoStickyBroadcastReceiverEventBus(final Application application,
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
        return eventSnapshot == null ? null : eventSnapshot.fetch();//sticky
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        T converted = intentToPojoConverter.convert(intent);

        eventSnapshot = new StickyEventSnapshot<>(converted);
        pumpEvent();
    }

    private void pumpEvent() {
        if (eventBusListener != null && eventSnapshot != null && !eventSnapshot.hasBeenFetched()) {
            eventBusListener.onEvent(eventSnapshot.fetch());
        }
    }
}
