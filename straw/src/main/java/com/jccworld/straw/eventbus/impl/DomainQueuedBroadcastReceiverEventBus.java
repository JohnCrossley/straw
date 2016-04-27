package com.jccworld.straw.eventbus.impl;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.jccworld.straw.eventbus.EventBus;
import com.jccworld.straw.eventbus.EventBusListener;
import com.jccworld.straw.eventbus.DomainEventBus;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by johncrossley on 27/11/15.
 */
public class DomainQueuedBroadcastReceiverEventBus<T> extends BroadcastReceiver implements EventBus<T>, DomainEventBus<T> {
    private final Application application;
    private final IntentFilter intentFilter;

    private Queue<T> queuedEvents = new ConcurrentLinkedQueue<>(new ArrayList<T>());
    private EventBusListener eventBusListener = null;

    public DomainQueuedBroadcastReceiverEventBus(final Application application,
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
        pumpQueue();//pushEvent all items in the queue
    }

    @Override
    public void detachListener() {
        this.eventBusListener = null;
    }

    @Override
    public T get() {
        return queuedEvents.size() == 0 ? null : queuedEvents.remove();
    }

    private void pumpQueue() {
        while(!queuedEvents.isEmpty()) {
            T event = queuedEvents.poll();
            eventBusListener.onEvent(event);
        }
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (eventBusListener != null) {
            pumpQueue();
        }
    }

    @Override
    public void pushEvent(final T t) {
        queuedEvents.add(t);
    }
}
