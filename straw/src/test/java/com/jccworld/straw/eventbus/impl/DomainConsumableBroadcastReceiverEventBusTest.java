package com.jccworld.straw.eventbus.impl;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.jccworld.straw.eventbus.EventBusListener;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by johncrossley on 25/04/16.
 */
public class DomainConsumableBroadcastReceiverEventBusTest {
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

    private DomainConsumableBroadcastReceiverEventBus sut;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new DomainConsumableBroadcastReceiverEventBus(mockApplicationContext, mockIntentFilter);
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
    public void whenNoListenerAvailableItRetainsEventUntilListenerIsAttached() {
        // init
        sut.pushEvent(mockEvent);
        sut.onReceive(mockContext, mockIntent);

        // run
        sut.attachListener(mockEventBusListener);

        // verify
        verify(mockEventBusListener).onEvent(mockEvent);
    }

    @Test
    public void removesEventAfterListenerReceivesEvent() {
        // init
        sut.pushEvent(mockEvent);
        sut.onReceive(mockContext, mockIntent);

        // run
        sut.attachListener(mockEventBusListener);

        // verify
        assertNull(sut.get());
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
}