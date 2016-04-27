package com.jccworld.straw.eventbus;

/**
 * Created by johncrossley on 08/01/16.
 */
public interface DomainEventBus<T> {

    /**
     * Called in non UI thread.  A broadcast would follow up received in the UI thread which will
     * pickup the events from the queue.
     *
     * @param t
     */
    void pushEvent(T t);

}
