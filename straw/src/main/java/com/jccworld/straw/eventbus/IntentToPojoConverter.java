package com.jccworld.straw.eventbus;

import android.content.Intent;

public interface IntentToPojoConverter<T> {
    T convert(final Intent intent);
}