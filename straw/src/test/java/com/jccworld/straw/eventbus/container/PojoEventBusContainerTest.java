package com.jccworld.straw.eventbus.container;

import com.jccworld.straw.eventbus.EventBus;
import com.jccworld.straw.eventbus.producer.PojoEventBusProducer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * Created by johncrossley on 25/04/16.
 */
public class PojoEventBusContainerTest {

    @Mock
    private EventBus mockEventBus;

    @Mock
    private PojoEventBusProducer mockProducer;

    private PojoEventBusContainer sut;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void confirmSetup() {
        // run
        sut = new PojoEventBusContainer(mockEventBus, mockProducer);

        // verify
        assertEquals(mockEventBus, sut.getConsumer());
        assertEquals(mockProducer, sut.getProducer());
    }
}