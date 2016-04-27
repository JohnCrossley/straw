package com.jccworld.straw.eventbus;

/**
 * Created by johncrossley on 27/11/15.
 */
public interface EventBusListener<T> {

    /**
     *
     * @param event
     */
    void onEvent(T event);
}
