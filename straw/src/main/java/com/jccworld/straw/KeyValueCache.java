package com.jccworld.straw;

import java.util.HashMap;
import java.util.Map;

/**
 * Store objects for the lifetime of a view.  These objects will get passed in after
 * every rotation, etc.
 *
 * Created by jcc on 26/10/15.
 */
public class KeyValueCache {
    private final Map<String, Object> map;

    public KeyValueCache() {
        map = new HashMap<>();
    }

    public KeyValueCache(final Map<String, Object> initParams) {
        map = new HashMap<>();
        map.putAll(initParams);
    }

    public void save(final String key, final Object value) {
        map.put(key, value);
    }

    public boolean has(final String key) {
        return map.containsKey(key);
    }

    public Integer loadInteger(final String key) {
        return (Integer)map.get(key);
    }

    public Long loadLong(final String key) {
        return (Long)map.get(key);
    }

    public Float loadFloat(final String key) {
        return (Float)map.get(key);
    }

    public Double loadDouble(final String key) {
        return (Double)map.get(key);
    }

    public Boolean loadBoolean(final String key) {
        return (Boolean)map.get(key);
    }

    public String loadString(final String key) {
        return (String)map.get(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        KeyValueCache that = (KeyValueCache) o;

        return map.equals(that.map);
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }
}
