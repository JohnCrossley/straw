package com.jccworld.straw.eventbus;

/**
 * Created by johncrossley on 27/11/15.
 */
public interface EventBus<T> {

    /**
     * Business logic event: start listening for events - unaffected by Activity/Fragment lifecycle
     */
    void start();

    /**
     * Business logic event: stop listening for events - unaffected by Activity/Fragment lifecycle
     */
    void stop();

    /**
     * Activity/Fragment lifecycle event: attach a listener and get notified everytime there is a new event.
     *
     * Usually called in onResume()
     *
     * @param eventListener
     */
    void attachListener(final EventBusListener eventListener);

    /**
     * Activity/Fragment lifecycle event: detach a listener and stop being notified when there is a new event.
     * Depending on the implementation, the event may be queued (XX), overriden (XX), ignored etc.
     *
     * Usually called in onPause()
     */
    void detachListener();

    /**
     * Get event from EventBus.  If no event is available, returns null.  On queues, it returns the
     * 0th item and removes it from the queue.
     *
     * Note: deletes item on consumable event buses.  This is a use of the event.
     *
     * @return typed event
     */
    T get();
}
