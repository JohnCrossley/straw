package com.jccworld.straw.activity;

import com.jccworld.straw.Cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A cache for all activities
 */
public class ActivityCache {
    private final Map<String,Cache> cache = new HashMap<>();

    public void create(String activityId) {
        cache.put(activityId, new Cache());
    }

    public void create(String activityId, Map<String, Object> initParams) {
        cache.put(activityId, new Cache(initParams));
    }

    public Cache get(String activityId) {
        //may not be started via ActivityController.startActivity() i.e. app launch
        if (!cache.containsKey(activityId)) {
            create(activityId);
        }

        return cache.get(activityId);
    }
}
