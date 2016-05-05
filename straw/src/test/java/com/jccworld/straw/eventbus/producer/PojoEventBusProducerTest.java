package com.jccworld.straw.eventbus.producer;

import android.content.Context;
import android.content.Intent;

import com.jccworld.straw.BuildConfig;
import com.jccworld.straw.eventbus.DomainToIntentConverter;

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

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by johncrossley on 25/04/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class PojoEventBusProducerTest {
    private String A_VALID_UUID = "a valid uuid";

    @Mock
    private Context mockApplicationContext;

    @Mock
    private DomainToIntentConverter mockDomainToIntentConverter;

    @Mock
    private Object mockRequest;

    @Mock
    private Intent mockConvertedRequestAsIntent;

    @Captor
    private ArgumentCaptor<Intent> capture;

    private PojoEventBusProducer sut;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new PojoEventBusProducer(mockApplicationContext, A_VALID_UUID, mockDomainToIntentConverter);
    }

    @Test
    public void convertsRequestAndSendsIntentToUiThread() {
        // init
        when(mockDomainToIntentConverter.convert(mockRequest)).thenReturn(mockConvertedRequestAsIntent);
        InOrder inOrder = Mockito.inOrder(mockDomainToIntentConverter, mockApplicationContext);

        // run
        sut.send(mockRequest);

        // verify
        inOrder.verify(mockDomainToIntentConverter).convert(mockRequest);
        inOrder.verify(mockApplicationContext).sendBroadcast(any(Intent.class));
    }

    @Test
    public void setsUuidToActionOnIntent() {
        // init
        final Intent realIntent = new Intent();
        when(mockDomainToIntentConverter.convert(mockRequest)).thenReturn(realIntent);

        // run
        sut.send(mockRequest);

        // verify
        verify(mockApplicationContext).sendBroadcast(capture.capture());
        assertEquals(A_VALID_UUID, capture.getValue().getAction());
    }

    @Test
    public void sendsConvertedDataToUiThread() {
        // init
        when(mockDomainToIntentConverter.convert(mockRequest)).thenReturn(mockConvertedRequestAsIntent);

        // run
        sut.send(mockRequest);

        // verify
        verify(mockApplicationContext).sendBroadcast(mockConvertedRequestAsIntent);
    }
}