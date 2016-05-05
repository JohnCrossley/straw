package com.jccworld.straw.activity;

import com.jccworld.straw.Cache;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by johncrossley on 21/04/16.
 */
public class UIComponentCacheTest {

    private static final String A_COMPONENT_ID = "AN ACTIVITY ID";

    private UIComponentCache sut;

    @Before
    public void setUp() throws Exception {
        sut = new UIComponentCache();
    }

    @Test
    public void providesEmptyCache() {
        // init
        final Cache expected = new Cache();

        // run
        sut.create(A_COMPONENT_ID);

        // verify
        assertEquals(expected, sut.get(A_COMPONENT_ID));
    }
}