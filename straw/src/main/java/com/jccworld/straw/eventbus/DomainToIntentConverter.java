package com.jccworld.straw.eventbus;

import android.content.Intent;

public interface DomainToIntentConverter<T> {
    Intent convert(final T t);
}