package com.jccworld.straw.activity;

import com.jccworld.straw.Cache;

import java.util.HashMap;
import java.util.Map;

/**
 * A cache for all activities
 */
public class UIComponentCache {
    private final Map<String,Cache> cache = new HashMap<>();

    public void create(final String componentId) {
        cache.put(componentId, new Cache());
    }

    public Cache get(final String componentId) {
        return cache.get(componentId);
    }
}
