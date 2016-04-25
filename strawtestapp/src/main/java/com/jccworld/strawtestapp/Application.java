package com.jccworld.strawtestapp;

import com.jccworld.straw.activity.ActivityCache;
import com.jccworld.straw.application.ApplicationCallbacks;
import com.jccworld.strawtestapp.debug.VerboseApplication;
import com.jccworld.strawtestapp.di.module.application.ApplicationModule;

import dagger.ObjectGraph;

/**
 * Created by jcc on 04/11/15.
 */
public class Application extends VerboseApplication implements ApplicationCallbacks {
    private ObjectGraph objectGraph;
    private ActivityCache activityCache;

    @Override
    public void onCreate() {
        super.onCreate();

        System.out.println("JCC: APP STARTED ----------------------------------------------------------------------------------------------------");

        objectGraph = ObjectGraph.create(new ApplicationModule(this));
        objectGraph.inject(this);

        activityCache = new ActivityCache();
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }

    @Override
    public ActivityCache getActivityCache() {
        return activityCache;
    }
}
