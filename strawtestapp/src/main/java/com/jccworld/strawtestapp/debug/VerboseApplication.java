package com.jccworld.strawtestapp.debug;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.Log;

/**
 * Created by jcc on 05/11/15.
 */
public class VerboseApplication extends android.app.Application {

    private void logV(final String message) {
        Log.v(getClass().getSimpleName(), "[straw] " + message);
    }

    public VerboseApplication() {
        super();
        logV(getClass().getName() + "()  ------------------------------ *** APP STARTUP ***  ------------------------------");
    }

    @Override
    public void onCreate() {
        logV("onCreate()");
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        logV("onTerminate()");
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        logV("onConfigurationChanged(newConfig: " + newConfig + ")");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        logV("onLowMemory()");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        logV("onTrimMemory(level: " + level + ")");
        super.onTrimMemory(level);
    }

    @Override
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        logV("registerComponentCallbacks(callback: " + callback + ")");
        super.registerComponentCallbacks(callback);
    }

    @Override
    public void unregisterComponentCallbacks(ComponentCallbacks callback) {
        logV("unregisterComponentCallbacks(callback: " + callback + ")");
        super.unregisterComponentCallbacks(callback);
    }

    @Override
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        logV("registerActivityLifecycleCallbacks(callback: " + callback + ")");
        super.registerActivityLifecycleCallbacks(callback);
    }

    @Override
    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        logV("unregisterActivityLifecycleCallbacks(callback: " + callback + ")");
        super.unregisterActivityLifecycleCallbacks(callback);
    }

    @Override
    public void registerOnProvideAssistDataListener(OnProvideAssistDataListener callback) {
        logV("registerOnProvideAssistDataListener(callback: " + callback + ")");
        super.registerOnProvideAssistDataListener(callback);
    }

    @Override
    public void unregisterOnProvideAssistDataListener(OnProvideAssistDataListener callback) {
        logV("unregisterOnProvideAssistDataListener(callback: " + callback + ")");
        super.unregisterOnProvideAssistDataListener(callback);
    }
}
