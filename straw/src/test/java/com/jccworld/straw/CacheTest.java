package com.jccworld.straw;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by johncrossley on 22/04/16.
 */
public class CacheTest {

    public static final String KEY = "a valid key";
    private static final String VALUE = "a valid value";

    private Map<String, Object> initParams;
    private Cache sut;

    @Before
    public void setUp() throws Exception {
        initParams = new HashMap<>();
        initParams.put(KEY, VALUE);
    }

    @Test
    public void initWithParams() {
        // init
        sut = new Cache(initParams);

        // verify
        assertEquals(VALUE, sut.getKeyValueCache().loadString(KEY));
    }

    @Test
    public void startsWithZeroInstances() {
        // init
        sut = new Cache(initParams);

        // verify
        assertEquals(0, sut.getInstanceCount());
        assertFalse(sut.isFirstRun());
    }

    @Test
    public void countsInstances() {
        // init
        sut = new Cache(initParams);

        // run
        sut.newInstanceCreated();

        // verify
        assertEquals(1, sut.getInstanceCount());
        assertTrue(sut.isFirstRun());
    }

    @Test
    public void removesFirstRunFlagOnSecondInstance() {
        // init
        sut = new Cache(initParams);
        sut.newInstanceCreated();//1

        // run
        sut.newInstanceCreated();//2

        // verify
        assertEquals(2, sut.getInstanceCount());
        assertFalse(sut.isFirstRun());
    }

    @Test
    public void verifyEqualsSameObject() {
        // init
        sut = new Cache(initParams);

        // verify
        assertEquals(sut, sut);
    }

    @Test
    public void verifyEqualsWithSameInitParams() {
        // init
        Cache sut2;
        sut = new Cache(initParams);
        sut2 = new Cache(initParams);

        // verify
        assertEquals(sut, sut2);
    }

    @Test
    public void verifyNotEqualsWhenDifferentInstanceCount() {
        // init
        Cache sut2;
        sut = new Cache(initParams);
        sut2 = new Cache(initParams);

        // run
        sut.newInstanceCreated();

        // verify
        assertNotEquals(sut, sut2);
    }

    @Test
    public void verifyNotEqualsWhenNull() {
        // init
        sut = new Cache(initParams);

        // verify
        assertNotEquals(sut, null);
    }

    @Test
    public void verifyHashCodeIsConstant() {
        // initCache sut2;
        sut = new Cache(initParams);

        // verify
        assertEquals(sut.hashCode(), sut.hashCode());
    }

    @Test
    public void verifyHashCodeIsDifferentForDifferentInstances() {
        // init
        Cache sut2;
        sut = new Cache(initParams);
        sut2 = new Cache(initParams);

        // verify
        assertNotEquals(sut.hashCode(), sut2.hashCode());
    }
}