package com.jccworld.straw.eventbus.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * Created by johncrossley on 27/04/16.
 */
public class StickyEventSnapshotTest {

    @Mock
    private Object mockEvent;

    private StickyEventSnapshot sut;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new StickyEventSnapshot(mockEvent);
    }

    @Test
    public void getProvidesEvent() throws Exception {
        assertEquals(mockEvent, sut.fetch());
    }

    @Test
    public void sentIsInitiallyFalse() throws Exception {
        assertFalse(sut.hasBeenFetched());
    }

    @Test
    public void sentIsSetAfterRequestingEvent() {
        // run
        sut.fetch();

        // verify
        assertTrue(sut.hasBeenFetched());
    }
}