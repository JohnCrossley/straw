package com.jccworld.straw.eventbus.impl;

/**
 * A snapshot for a given event.  Once the event has been sent to a listener, we need to remember
 * that so we don't send the message again.
 *
 * Created by johncrossley on 27/04/16.
 */
class StickyEventSnapshot<T> {
    private final T event;
    private boolean sent;

    StickyEventSnapshot(final T event) {
        this.event = event;
        sent = false;
    }

    T fetch() {
        sent = true;
        return event;
    }

    boolean hasBeenFetched() {
        return sent;
    }
}
