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
public class SentencePojoService extends Service {
    private final Injector injector;

    @Inject @Named("wordPojoConsumableEventBusProducer")
    EventBusProducer<String> eventBusConsumerProducer;

    @Inject @Named("wordPojoStickyEventBusProducer")
    EventBusProducer<String> eventBusStickyProducer;

    @Inject @Named("wordPojoQueuedEventBusProducer")
    EventBusProducer<String> eventBusQueuedProducer;

    private String[] words = { "The", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog" };

    public SentencePojoService() {
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
                for(int i = 0; i < words.length; i++) {
                    eventBusConsumerProducer.send(words[i]);
                    eventBusStickyProducer.send(words[i]);
                    eventBusQueuedProducer.send(words[i]);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        return Service.START_NOT_STICKY;
    }
}
