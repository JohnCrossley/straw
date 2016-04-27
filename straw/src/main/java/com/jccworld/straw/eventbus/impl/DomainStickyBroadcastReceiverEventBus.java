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
public class DomainStickyBroadcastReceiverEventBus<T> extends BroadcastReceiver implements EventBus<T>, DomainEventBus<T> {
    private final Application application;
    private final IntentFilter intentFilter;

    private StickyEventSnapshot<T> eventSnapshot = null;

    private EventBusListener eventBusListener = null;

    public DomainStickyBroadcastReceiverEventBus(final Application application,
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
        return eventSnapshot == null ? null : eventSnapshot.fetch();//sticky
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        pumpEvent();
    }

    @Override
    public void pushEvent(T t) {
        eventSnapshot = new StickyEventSnapshot(t);
    }

    private void pumpEvent() {
        if (eventBusListener != null && eventSnapshot != null && !eventSnapshot.hasBeenFetched()) {
            eventBusListener.onEvent(eventSnapshot.fetch());
        }
    }
}
