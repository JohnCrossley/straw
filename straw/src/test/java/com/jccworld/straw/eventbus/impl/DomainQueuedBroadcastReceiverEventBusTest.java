package com.jccworld.straw.eventbus.impl;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.jccworld.straw.eventbus.EventBusListener;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by johncrossley on 26/04/16.
 */
public class DomainQueuedBroadcastReceiverEventBusTest {
    @Mock
    private Application mockApplicationContext;

    @Mock
    private IntentFilter mockIntentFilter;

    @Mock
    private EventBusListener mockEventBusListener;

    @Mock
    private Context mockContext;

    @Mock
    private Intent mockIntent;

    @Mock
    private Object mockEvent;

    @Mock
    private Object mockEvent2;

    @Mock
    private Object mockEvent3;

    private DomainQueuedBroadcastReceiverEventBus sut;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new DomainQueuedBroadcastReceiverEventBus(mockApplicationContext, mockIntentFilter);
    }

    @Test
    public void startRegistersBroadcastReceiver() throws Exception {
        // run
        sut.start();

        // verify
        verify(mockApplicationContext).registerReceiver(sut, mockIntentFilter);
    }

    @Test
    public void stopUnregistersBroadcastReceiver() throws Exception {
        // run
        sut.stop();

        // verify
        verify(mockApplicationContext).unregisterReceiver(sut);
    }

    @Test
    public void eventsGetDispatchedToListener() throws Exception {
        // init
        sut.attachListener(mockEventBusListener);
        sut.pushEvent(mockEvent);

        // run
        sut.onReceive(mockContext, mockIntent);

        // verify
        verify(mockEventBusListener).onEvent(mockEvent);
    }

    @Test
    public void eventsAreQueuedAndSetOnceListenerIsAttached() throws Exception {
        // init
        final InOrder inOrder = Mockito.inOrder(mockEventBusListener);
        sut.pushEvent(mockEvent);
        sut.pushEvent(mockEvent2);
        sut.pushEvent(mockEvent3);

        // post-init
        verify(mockEventBusListener, never()).onEvent(any(Object.class));

        // run
        sut.attachListener(mockEventBusListener);

        // verify
        inOrder.verify(mockEventBusListener).onEvent(mockEvent);
        inOrder.verify(mockEventBusListener).onEvent(mockEvent2);
        inOrder.verify(mockEventBusListener).onEvent(mockEvent3);
    }

    @Test
    public void removingListenerEventsAreNotSent() throws Exception {
        // init
        sut.attachListener(mockEventBusListener);
        sut.detachListener();

        // run
        sut.onReceive(mockContext, mockIntent);

        // verify
        verify(mockEventBusListener, never()).onEvent(any(Object.class));
    }

    @Test
    public void initialEventIsUnSet() throws Exception {
        assertNull(sut.get());
    }

    @Test
    public void getEventProvidesEvent() {
        // run
        sut.pushEvent(mockEvent);

        // verify
        assertEquals(mockEvent, sut.get());
    }

    @Test
    public void firstGetMarksEventAsConsumed() {
        // run
        sut.pushEvent(mockEvent);

        // verify
        assertEquals(mockEvent, sut.get());
        assertNull(sut.get());//now null
    }

    @Test
    public void eventsAreQueuedInOrderAndRemovedAsTheyArePickedUp() {
        // run
        sut.pushEvent(mockEvent);
        sut.pushEvent(mockEvent2);
        sut.pushEvent(mockEvent3);

        // verify
        assertEquals(mockEvent, sut.get());
        assertEquals(mockEvent2, sut.get());
        assertEquals(mockEvent3, sut.get());
    }
}