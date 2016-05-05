package com.jccworld.straw.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jccworld.straw.UIComponentProxy;

/**
 * Created by johncrossley on 28/04/16.
 */
public abstract class BaseFragment extends Fragment implements FragmentCallbacks, ActivityCallbacks {
    private UIComponentProxy uiComponentProxy;

    public BaseFragment() {
        super();
        logV("Constructor()");
    }

    private void logV(final String message) {
        Log.v(getClass().getSimpleName(), "[straw] " + message);
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        logV("onCreateView(inflater: " + inflater + ", container: " + container + ", savedInstanceState: " + savedInstanceState + ")");
        return inflateView(inflater, container);
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        logV("onActivityCreated(savedInstanceState: " + savedInstanceState + ")");
        super.onActivityCreated(savedInstanceState);

        uiComponentProxy = new UIComponentProxy(this);
        uiComponentProxy.handleOnCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        logV("onSaveInstanceState(outState: " + outState + ")");
        uiComponentProxy.onSaveInstanceState(outState);

        super.onSaveInstanceState(outState);//must be done last
    }

    @Override
    public void onResume() {
        logV("onResume()");
        super.onResume();

        uiComponentProxy.handleOnResume();
    }

    @Override
    public void onPause() {
        logV("onPause()");
        super.onPause();

        uiComponentProxy.handleOnPause();
    }
}
