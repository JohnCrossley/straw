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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by johncrossley on 26/04/16.
 */
public class PojoConsumableBroadcastReceiverEventBusTest {
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
    private IntentToPojoConverter mockIntentToPojoConverter;

    private PojoConsumableBroadcastReceiverEventBus sut;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new PojoConsumableBroadcastReceiverEventBus(mockApplicationContext, mockIntentFilter, mockIntentToPojoConverter);
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
        when(mockIntentToPojoConverter.convert(mockIntent)).thenReturn(mockEvent);
        sut.attachListener(mockEventBusListener);

        // run
        sut.onReceive(mockContext, mockIntent);

        // verify
        verify(mockEventBusListener).onEvent(any(Object.class));
    }

    @Test
    public void whenNoListenerAvailableItRetainsEventUntilListenerIsAttached() {
        // init
        when(mockIntentToPojoConverter.convert(mockIntent)).thenReturn(mockEvent);
        sut.onReceive(mockContext, mockIntent);

        // run
        sut.attachListener(mockEventBusListener);

        // verify
        verify(mockEventBusListener).onEvent(mockEvent);
    }

    @Test
    public void removesEventAfterListenerReceivesEvent() {
        // init
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
    public void getEventProvidesConvertedEventFromIntent() {
        // init
        when(mockIntentToPojoConverter.convert(mockIntent)).thenReturn(mockEvent);

        // run
        sut.onReceive(mockContext, mockIntent);

        // verify
        assertEquals(mockEvent, sut.get());
    }

    @Test
    public void firstGetMarksEventAsConsumed() {
        // init
        when(mockIntentToPojoConverter.convert(mockIntent)).thenReturn(mockEvent);

        // run
        sut.onReceive(mockContext, mockIntent);

        // verify
        assertEquals(mockEvent, sut.get());
        assertNull(sut.get());//now null
    }


}