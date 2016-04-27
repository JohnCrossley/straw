package com.jccworld.straw.eventbus.producer;

import android.content.Context;
import android.content.Intent;

import com.jccworld.straw.eventbus.DomainToIntentConverter;

/**
 * Created by johncrossley on 08/01/16.
 */
public class PojoEventBusProducer<T> implements EventBusProducer<T> {

    private final Context application;
    private final String uuid;
    private final DomainToIntentConverter<T> converter;

    public PojoEventBusProducer(Context application, String uuid, DomainToIntentConverter<T> converter) {
        this.application = application;
        this.uuid = uuid;
        this.converter = converter;
    }


    @Override
    public void send(T t) {
        //transform object into intent
        final Intent intent = converter.convert(t);
        intent.setAction(uuid);

        //pushEvent intent containing all data
        application.sendBroadcast(intent);
    }
}