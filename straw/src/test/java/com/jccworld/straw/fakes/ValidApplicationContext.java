package com.jccworld.straw.fakes;

import android.app.Application;

import com.jccworld.straw.activity.ActivityCache;
import com.jccworld.straw.application.ApplicationCallbacks;

/**
 * Created by johncrossley on 21/04/16.
 */
public class ValidApplicationContext extends Application implements ApplicationCallbacks {

    private ActivityCache activityCache = new ActivityCache();

    @Override
    public ActivityCache getActivityCache() {
        return activityCache;
    }
}
