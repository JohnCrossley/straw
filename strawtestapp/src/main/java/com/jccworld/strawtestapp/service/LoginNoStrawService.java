package com.jccworld.strawtestapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by johncrossley on 27/04/16.
 */
public class LoginNoStrawService extends Service {
    public static final String LOGIN_NO_STRAW_SERVICE_RESPONSE_ACTION = "LoginNoStrawService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CountDownLatch latch = new CountDownLatch(1);
                try {
                    latch.await(5, TimeUnit.SECONDS);

                    sendBroadcast(new Intent(LOGIN_NO_STRAW_SERVICE_RESPONSE_ACTION));

                } catch (InterruptedException e) {


                }
            }
        }).start();

        return START_NOT_STICKY;
    }
}
