package com.jccworld.straw.eventbus.impl;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.jccworld.straw.eventbus.EventBusListener;
import com.jccworld.straw.eventbus.IntentToPojoConverter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by johncrossley on 26/04/16.
 */
public class PojoQueuedBroadcastReceiverEventBusTest {
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
    private Intent mockIntent3;

    @Mock
    private Object mockEvent;

    @Mock
    private Object mockEvent2;

    @Mock
    private Object mockEvent3;

    @Mock
    private IntentToPojoConverter mockItentToPojoConverter;

    private PojoQueuedBroadcastReceiverEventBus sut;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new PojoQueuedBroadcastReceiverEventBus(mockApplicationContext, mockIntentFilter, mockItentToPojoConverter);
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
        when(mockItentToPojoConverter.convert(mockIntent)).thenReturn(mockEvent);
        sut.attachListener(mockEventBusListener);

        // run: this would be called in the UI thread (in real world)
        sut.onReceive(mockContext, mockIntent);

        // verify
        verify(mockEventBusListener).onEvent(mockEvent);
    }

    @Test
    public void eventsAreQueuedAndSetOnceListenerIsAttached() throws Exception {
        // init
        final InOrder inOrder = Mockito.inOrder(mockEventBusListener);
        when(mockItentToPojoConverter.convert(mockIntent)).thenReturn(mockEvent);
        when(mockItentToPojoConverter.convert(mockIntent2)).thenReturn(mockEvent2);
        when(mockItentToPojoConverter.convert(mockIntent3)).thenReturn(mockEvent3);
        sut.onReceive(mockContext, mockIntent);
        sut.onReceive(mockContext, mockIntent2);
        sut.onReceive(mockContext, mockIntent3);

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
        // init
        when(mockItentToPojoConverter.convert(mockIntent)).thenReturn(mockEvent);

        // run
        sut.onReceive(mockContext, mockIntent);

        // verify
        assertEquals(mockEvent, sut.get());
    }

    @Test
    public void firstGetMarksEventAsConsumed() {
        // init
        when(mockItentToPojoConverter.convert(mockIntent)).thenReturn(mockEvent);

        // run
        sut.onReceive(mockContext, mockIntent);

        // verify
        assertEquals(mockEvent, sut.get());
        assertNull(sut.get());//now null
    }

    @Test
    public void eventsAreQueuedInOrderAndRemovedAsTheyArePickedUp() {
        // init
        when(mockItentToPojoConverter.convert(mockIntent)).thenReturn(mockEvent);
        when(mockItentToPojoConverter.convert(mockIntent2)).thenReturn(mockEvent2);
        when(mockItentToPojoConverter.convert(mockIntent3)).thenReturn(mockEvent3);

        // run
        sut.onReceive(mockContext, mockIntent);
        sut.onReceive(mockContext, mockIntent2);
        sut.onReceive(mockContext, mockIntent3);

        // verify
        assertEquals(mockEvent, sut.get());
        assertEquals(mockEvent2, sut.get());
        assertEquals(mockEvent3, sut.get());
    }
}