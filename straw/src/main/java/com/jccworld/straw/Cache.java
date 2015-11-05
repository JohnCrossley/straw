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
}
