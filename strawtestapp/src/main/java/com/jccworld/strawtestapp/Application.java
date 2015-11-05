package com.jccworld.strawtestapp;

import com.jccworld.straw.activity.ActivityCache;
import com.jccworld.straw.application.ApplicationCallbacks;
import com.jccworld.strawtestapp.debug.VerboseApplication;

/**
 * Created by jcc on 04/11/15.
 */
public class Application extends VerboseApplication implements ApplicationCallbacks {
    private ActivityCache activityCache;

    @Override
    public void onCreate() {
        super.onCreate();
        activityCache = new ActivityCache();
    }

    @Override
    public ActivityCache getActivityCache() {
        return activityCache;
    }
}
