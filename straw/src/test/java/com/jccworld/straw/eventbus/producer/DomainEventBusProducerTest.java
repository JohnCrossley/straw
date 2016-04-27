package com.jccworld.straw.eventbus.producer;

import android.content.Context;
import android.content.Intent;

import com.jccworld.straw.BuildConfig;
import com.jccworld.straw.eventbus.DomainEventBus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by johncrossley on 25/04/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class DomainEventBusProducerTest {

    private static final String A_VALID_UUID = "a valid uuid";

    @Mock
    private Object mockRequest;

    @Mock
    private DomainEventBus mockEventBus;

    @Mock
    private Context mockApplicationContext;

    private DomainEventBusProducer sut;

    @Captor
    private ArgumentCaptor<Intent> capture;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new DomainEventBusProducer(mockEventBus, mockApplicationContext, A_VALID_UUID);
    }

    @Test
    public void givesEventToConsumerBusThenSendsMessageToUIThread() {
        // init
        final InOrder inOrder = Mockito.inOrder(mockEventBus, mockApplicationContext);

        // run
        sut.send(mockRequest);

        // verify
        inOrder.verify(mockEventBus).pushEvent(mockRequest);
        inOrder.verify(mockApplicationContext).sendBroadcast(any(Intent.class));
    }

    @Test
    public void sendsAnIntentWithUuidAsAction() {
        // run
        sut.send(mockRequest);

        // verify
        verify(mockApplicationContext).sendBroadcast(capture.capture());
        assertEquals(A_VALID_UUID, capture.getValue().getAction());
    }
}