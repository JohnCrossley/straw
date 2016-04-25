package com.jccworld.straw;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by johncrossley on 22/04/16.
 */
public class KeyValueCacheTest {

    private static final String KEY = "a valid key";

    private static final int VALUE_INTEGER = 123;
    private static final long VALUE_LONG = 456L;
    private static final float VALUE_FLOAT = 7.8F;
    private static final double VALUE_DOUBLE = 9.123;
    private static final boolean VALUE_BOOLEAN = true;
    private static final String VALUE_STRING = "a valid value";

    private KeyValueCache sut;

    @Before
    public void setUp() throws Exception {
        sut = new KeyValueCache();
    }

    @Test
    public void storesInitParams() {
        // init
        final Map<String, Object> initParams = new HashMap<>();
        initParams.put(KEY, VALUE_STRING);

        // run
        sut = new KeyValueCache(initParams);

        // verify
        assertTrue(sut.has(KEY));
    }

    @Test
    public void saveAndRetrieveInteger() {
        // run
        sut.save(KEY, VALUE_INTEGER);

        // verify
        assertEquals((Integer)VALUE_INTEGER, sut.loadInteger(KEY));
    }

    @Test
    public void saveAndRetrieveLong() {
        // run
        sut.save(KEY, VALUE_LONG);

        // verify
        assertEquals((Long)VALUE_LONG, sut.loadLong(KEY));
    }

    @Test
    public void saveAndRetrieveFloat() {
        // run
        sut.save(KEY, VALUE_FLOAT);

        // verify
        assertEquals((Float)VALUE_FLOAT, sut.loadFloat(KEY));
    }

    @Test
    public void saveAndRetrieveDouble() {
        // run
        sut.save(KEY, VALUE_DOUBLE);

        // verify
        assertEquals((Double)VALUE_DOUBLE, sut.loadDouble(KEY));
    }

    @Test
    public void saveAndRetrieveBoolean() {
        // run
        sut.save(KEY, VALUE_BOOLEAN);

        // verify
        assertEquals(VALUE_BOOLEAN, sut.loadBoolean(KEY));
    }

    @Test
    public void saveAndRetrieveString() {
        // run
        sut.save(KEY, VALUE_STRING);

        // verify
        assertEquals(VALUE_STRING, sut.loadString(KEY));
    }

    @Test
    public void equalsSameObject() {
        assertEquals(sut, sut);
    }

    @Test
    public void nullObjectNotEquals() {
        assertNotEquals(sut, null);
    }

    @Test
    public void equalsDifferentObjectSameParams() {
        // init
        KeyValueCache sut2;
        sut = new KeyValueCache();
        sut2 = new KeyValueCache();
        final Map<String,Object> initParams = new HashMap<>();
        final Map<String,Object> initParams2 = new HashMap<>();

        // run
        initParams.put(KEY, VALUE_STRING);
        initParams2.put(KEY, VALUE_STRING);

        // verify
        assertEquals(sut, sut2);
    }

    @Test
    public void hashCodeMatchesSameObject() {
        // init
        KeyValueCache sut2;
        sut = new KeyValueCache();
        sut2 = new KeyValueCache();

        // verify
        assertEquals(sut.hashCode(), sut2.hashCode());
    }
}