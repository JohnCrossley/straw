package com.jccworld.straw.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.jccworld.straw.Cache;
import com.jccworld.straw.application.ApplicationCallbacks;

/**
 * Created by jcc on 26/10/15.
 */
public abstract class BaseActivity extends Activity implements ActivityCallbacks {

    public BaseActivity() {
        super();
    }

    private void logV(final String message) {
        Log.v(getClass().getSimpleName(), "[straw] " + message);
    }

    private Cache retrieveCache() {
        ActivityController.checkApplicationContext(getApplicationContext());
        final Context applicationContext = getApplicationContext();
        final ActivityCache activityCache = ((ApplicationCallbacks) applicationContext).getActivityCache();
        return activityCache.get(getActivityId());
    }

    protected String getActivityId() {
        return getIntent().getStringExtra(ActivityController.ACTIVITY_ID);
    }

    //----------------------------------------------------------------------------------------------

    private void handleOnCreated() {
        retrieveCache().newInstanceCreated();

        created();
    }

    private void handleOnResume() {
        final Cache cache = retrieveCache();
        onLoad(retrieveCache().isFirstRun(), cache.getKeyValueCache(), cache.getUiPersister());
        onFocus();
    }

    private void handleOnPause() {
        final Cache cache = retrieveCache();
        onFocusLost();
        onSave(cache.getKeyValueCache(), cache.getUiPersister());
    }

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        logV("onCreate(savedInstanceState: " + savedInstanceState + ")");
        super.onCreate(savedInstanceState);

        handleOnCreated();
    }

    @Override
    protected void onResume() {
        logV("onResume()");
        super.onResume();

        handleOnResume();
    }

    @Override
    protected void onPause() {
        logV("onPause()");
        super.onPause();

        handleOnPause();
    }
}
