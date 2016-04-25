package com.jccworld.straw;

import java.util.Map;

/**
 * A cache for one activity/fragment etc.
 *
 * Created by jcc on 01/11/15.
 */
public class Cache {
    private int instanceCount = 0;
    private final KeyValueCache keyValueCache;
    private final UIPersister uiPersister;

    public Cache() {
        keyValueCache = new KeyValueCache();
        uiPersister = new UIPersister();
    }

    public Cache(final Map<String, Object> initParams) {
        keyValueCache = new KeyValueCache(initParams);
        uiPersister = new UIPersister();
    }

    public KeyValueCache getKeyValueCache() {
        return keyValueCache;
    }

    public UIPersister getUiPersister() {
        return uiPersister;
    }

    public void newInstanceCreated() {
        instanceCount++;
    }

    public int getInstanceCount() {
        return instanceCount;
    }

    public boolean isFirstRun() {
        return instanceCount == 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Cache cache = (Cache) o;

        if (instanceCount != cache.instanceCount) {
            return false;
        }

        return keyValueCache.equals(cache.keyValueCache);
    }

    @Override
    public int hashCode() {
        int result = instanceCount;
        result = 31 * result + keyValueCache.hashCode();
        result = 31 * result + uiPersister.hashCode();
        return result;
    }
}
