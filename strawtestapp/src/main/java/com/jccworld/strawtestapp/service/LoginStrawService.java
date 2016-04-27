package com.jccworld.strawtestapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.jccworld.straw.eventbus.producer.EventBusProducer;
import com.jccworld.strawtestapp.activity.LoginState;
import com.jccworld.strawtestapp.di.injection.ProductionInjector;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by johncrossley on 26/04/16.
 */
public class LoginStrawService extends Service {

    @Inject
    @Named("loginEventBusProducer")
    public EventBusProducer<LoginState> eventBusProducer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        new ProductionInjector().inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CountDownLatch latch = new CountDownLatch(1);
                try {
                    latch.await(5, TimeUnit.SECONDS);

                    eventBusProducer.send(LoginState.LOGGED_IN);

                } catch (InterruptedException e) {


                }
            }
        }).start();

        return START_NOT_STICKY;
    }
}
