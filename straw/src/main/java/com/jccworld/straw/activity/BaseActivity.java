package com.jccworld.straw.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.jccworld.straw.UIComponentProxy;

/**
 * Created by johncrossley on 26/10/15.
 */
public abstract class BaseActivity extends Activity implements ActivityCallbacks {
    private UIComponentProxy uiComponentProxy;

    public BaseActivity() {
        super();
        logV("Constructor()");
    }

    private void logV(final String message) {
        Log.v(getClass().getSimpleName(), "[straw] " + message);
    }

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        logV("onCreate(savedInstanceState: " + savedInstanceState + ")");
        super.onCreate(savedInstanceState);

        uiComponentProxy = new UIComponentProxy(this);
        uiComponentProxy.handleOnCreated(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        logV("onSaveInstanceState(outState: " + outState + ")");
        uiComponentProxy.onSaveInstanceState(outState);

        super.onSaveInstanceState(outState);//must be done last
    }

    @Override
    protected void onResume() {
        logV("onResume()");
        super.onResume();

        uiComponentProxy.handleOnResume();
    }

    @Override
    protected void onPause() {
        logV("onPause()");
        super.onPause();

        uiComponentProxy.handleOnPause();
    }
}
