package com.jccworld.strawtestapp.eventbus.converter;

import android.content.Intent;

import com.jccworld.straw.eventbus.DomainToIntentConverter;
import com.jccworld.straw.eventbus.IntentToPojoConverter;

/**
 * Created by johncrossley on 27/11/15.
 */
public class ToShort implements IntentToPojoConverter<Short>, DomainToIntentConverter<Short> {
    @Override
    public Short convert(Intent intent) {
        return intent.getShortExtra("s", (short)-1);
    }

    @Override
    public Intent convert(Short aShort) {
        return new Intent("number").putExtra("s", aShort);
    }
}
