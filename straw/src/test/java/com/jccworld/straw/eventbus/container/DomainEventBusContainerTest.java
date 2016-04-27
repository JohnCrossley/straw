package com.jccworld.straw.eventbus.container;

import com.jccworld.straw.eventbus.EventBus;
import com.jccworld.straw.eventbus.producer.EventBusProducer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * Created by johncrossley on 25/04/16.
 */
public class DomainEventBusContainerTest {

    @Mock
    private EventBus mockEventBus;

    @Mock
    private EventBusProducer mockProducer;

    private DomainEventBusContainer sut;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void confirmSetup() {
        // run
        sut = new DomainEventBusContainer(mockEventBus, mockProducer);

        // verify
        assertEquals(mockEventBus, sut.getConsumer());
        assertEquals(mockProducer, sut.getProducer());
    }
}