package com.jccworld.straw.eventbus.impl;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.jccworld.straw.eventbus.EventBusListener;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by johncrossley on 26/04/16.
 */
public class DomainStickyBroadcastReceiverEventBusTest {
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

    private DomainStickyBroadcastReceiverEventBus sut;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new DomainStickyBroadcastReceiverEventBus(mockApplicationContext, mockIntentFilter);
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
        sut.pushEvent(mockEvent);
        sut.attachListener(mockEventBusListener);

        // run
        sut.onReceive(mockContext, mockIntent);

        // verify
        verify(mockEventBusListener).onEvent(any(Object.class));
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
    public void onceEventIsSetEventBusRetainsIt() {
        // run
        sut.pushEvent(mockEvent);

        // verify
        assertEquals(mockEvent, sut.get());
        assertEquals(mockEvent, sut.get());
        assertEquals(mockEvent, sut.get());
    }

    @Test
    public void remembersOnlyLastEvent() {
        // init
        sut.pushEvent(mockEvent);

        // post-init verify
        assertEquals(mockEvent, sut.get());

        // run
        sut.pushEvent(mockEvent2);

        // verify
        assertEquals(mockEvent2, sut.get());
    }

    @Test
    public void sendsPendingEventOnListenerAttached() {
        // init
        sut.pushEvent(mockEvent);

        // run
        sut.attachListener(mockEventBusListener);

        // verify
        verify(mockEventBusListener).onEvent(mockEvent);
    }

    @Test
    public void doesNotResendEventWhenListenerReattached() {
        // init
        sut.pushEvent(mockEvent);
        sut.attachListener(mockEventBusListener);
        reset(mockEventBusListener);

        // run
        sut.attachListener(mockEventBusListener);

        // verify
        verifyNoMoreInteractions(mockEventBusListener);
    }
}