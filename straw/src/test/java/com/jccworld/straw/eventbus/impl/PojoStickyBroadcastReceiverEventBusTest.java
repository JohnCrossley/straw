package com.jccworld.straw.eventbus.impl;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.jccworld.straw.eventbus.EventBusListener;
import com.jccworld.straw.eventbus.IntentToPojoConverter;

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
import static org.mockito.Mockito.when;

/**
 * Created by johncrossley on 26/04/16.
 */
public class PojoStickyBroadcastReceiverEventBusTest {
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
    private Intent mockIntent2;

    @Mock
    private Object mockEvent;

    @Mock
    private Object mockEvent2;

    @Mock
    private IntentToPojoConverter mockIntentToPojoConverter;

    private PojoStickyBroadcastReceiverEventBus sut;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new PojoStickyBroadcastReceiverEventBus(mockApplicationContext, mockIntentFilter, mockIntentToPojoConverter);
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
        // init
        when(mockIntentToPojoConverter.convert(mockIntent)).thenReturn(mockEvent);

        // run
        sut.onReceive(mockApplicationContext, mockIntent);

        // verify
        assertEquals(mockEvent, sut.get());
    }

    @Test
    public void onceEventIsSetEventBusRetainsIt() {
        // init
        when(mockIntentToPojoConverter.convert(mockIntent)).thenReturn(mockEvent);
        sut.onReceive(mockApplicationContext, mockIntent);

        // run
        assertEquals(mockEvent, sut.get());

        // verify
        assertEquals(mockEvent, sut.get());
        assertEquals(mockEvent, sut.get());
    }

    @Test
    public void remembersOnlyLastEvent() {
        // init
        when(mockIntentToPojoConverter.convert(mockIntent)).thenReturn(mockEvent);
        when(mockIntentToPojoConverter.convert(mockIntent2)).thenReturn(mockEvent2);
        sut.onReceive(mockApplicationContext, mockIntent);

        // post-init verify
        assertEquals(mockEvent, sut.get());

        // run
        sut.onReceive(mockApplicationContext, mockIntent2);

        // verify
        assertEquals(mockEvent2, sut.get());
    }
}