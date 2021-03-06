package com.jccworld.strawtestapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.jccworld.straw.eventbus.producer.EventBusProducer;
import com.jccworld.strawtestapp.di.injection.Injector;
import com.jccworld.strawtestapp.di.injection.ProductionInjector;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jcc on 19/11/15.
 */
public class NumberDomainService extends Service {

    private final Injector injector;

    @Inject @Named("numberDomainConsumableEventBusProducer")
    EventBusProducer<Short> eventBusConsumableProducer;

    @Inject @Named("numberDomainStickyEventBusProducer")
    EventBusProducer<Short> eventBusStickyProducer;

    @Inject @Named("numberDomainQueuedEventBusProducer")
    EventBusProducer<Short> eventBusQueuedProducer;


    public NumberDomainService() {
        this.injector = new ProductionInjector();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        injector.inject(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread() {
            @Override
            public void run() {
                for(short s = 0; s >= 0; s++) {
                    eventBusConsumableProducer.send(s);
                    eventBusStickyProducer.send(s);
                    eventBusQueuedProducer.send(s);
                }
            }
        }.start();

        return Service.START_NOT_STICKY;
    }
}
