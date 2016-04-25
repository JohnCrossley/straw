package com.jccworld.straw.activity;

import com.jccworld.straw.Cache;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by johncrossley on 21/04/16.
 */
public class ActivityCacheTest {

    private static final String AN_ACTIVITY_ID = "AN ACTIVITY ID";
    private static final String A_VALID_KEY = "A VALID KEY";
    private static final boolean A_VALID_BOOLEAN = true;
    private static final String ANOTHER_VALID_KEY = "ANOTHER VALID KEY";
    private static final int A_VALID_INTEGER = 12345;

    private ActivityCache sut;

    @Before
    public void setUp() throws Exception {
        sut = new ActivityCache();
    }

    @Test
    public void providesCacheForUnknownActivity() {
        // init
        final Cache expected = new Cache();

        // run
        final Cache result = sut.get(AN_ACTIVITY_ID);

        // verify
        assertEquals(expected, result);
    }

    @Test
    public void providesEmptyCache() {
        // init
        final Cache expected = new Cache();

        // run
        sut.create(AN_ACTIVITY_ID);

        // verify
        assertEquals(expected, sut.get(AN_ACTIVITY_ID));

    }

    @Test
    public void providesCacheWithInitValues() {
        // init
        final Map<String,Object> initParamMap = new HashMap<>();
        initParamMap.put(A_VALID_KEY, A_VALID_BOOLEAN);
        initParamMap.put(ANOTHER_VALID_KEY, A_VALID_INTEGER);


        final Cache expected = new Cache();
        expected.getKeyValueCache().save(A_VALID_KEY, A_VALID_BOOLEAN);
        expected.getKeyValueCache().save(ANOTHER_VALID_KEY, A_VALID_INTEGER);

        // run
        sut.create(AN_ACTIVITY_ID, initParamMap);

        // verify
        assertEquals(expected, sut.get(AN_ACTIVITY_ID));
    }
}