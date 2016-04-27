package com.jccworld.strawtestapp.eventbus.converter;

import android.content.Intent;

import com.jccworld.straw.eventbus.DomainToIntentConverter;
import com.jccworld.straw.eventbus.IntentToPojoConverter;

/**
 * Created by johncrossley on 27/11/15.
 */
public class ToWord implements IntentToPojoConverter<String>, DomainToIntentConverter<String> {
    @Override
    public String convert(Intent intent) {
        return intent.getStringExtra("word");
    }

    @Override
    public Intent convert(String s) {
        return new Intent("word").putExtra("word", s);
    }
}
