package com.jccworld.straw;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import com.jccworld.straw.activity.FragmentCallbacks;
import com.jccworld.straw.activity.ActivityCallbacks;
import com.jccworld.straw.application.ApplicationCallbacks;

import java.util.UUID;

/**
 * Created by johncrossley on 28/04/16.
 */
public class UIComponentProxy {
    public static final String COMPONENT_ID_KEY = "COMPONENT_ID_KEY";

    private ApplicationCallbacks applicationCallbacks;
    private ActivityCallbacks callback;

    private String id;

    /**
     * Warning - only instantiate in Activity.onCreate(Bundle) or
     * or a callback you prefer where the Activity has been fully initialised.
     * @param activity
     */
    public UIComponentProxy(final Activity activity) {
        checkApplication(activity.getApplication());
        checkActivity(activity);

        this.callback = (ActivityCallbacks) activity;
        this.applicationCallbacks = (ApplicationCallbacks) activity.getApplication();
    }

    /**
     * Warning - only instantiate in Fragment.onActivityCreated(Bundle)
     * or a callback you prefer where the Activity has been fully initialised.
     * @param fragment
     */
    public UIComponentProxy(final Fragment fragment) {
        checkApplication(fragment.getActivity().getApplication());
        checkFragment(fragment);

        this.callback = (ActivityCallbacks) fragment;
        this.applicationCallbacks = (ApplicationCallbacks) fragment.getActivity().getApplication();
    }

    private void checkApplication(final Context appContext) {
        if (appContext instanceof ApplicationCallbacks) {
            return;
        } else {
            throw new RuntimeException("Your application needs to implement ApplicationCallbacks interface.  Check docs.");
        }
    }

    private void checkActivity(final Activity activity) {
        if (activity instanceof ActivityCallbacks) {
            return;
        } else {
            throw new RuntimeException("Your activity must implement the ActivityCallbacks interface");
        }
    }

    private void checkFragment(final Fragment fragment) {
        if (fragment instanceof ActivityCallbacks && fragment instanceof FragmentCallbacks) {
            return;
        } else {
            throw new RuntimeException("Your fragment must implement both ActivityCallbacks and FragmentCallbacks interfaces.");
        }
    }

    private String createId() {
        final UUID uuid = UUID.randomUUID();
        final String componentId = callback.getClass().getName() + ":" + uuid.toString();

        return componentId;
    }

    public void handleOnCreated(final Bundle bundle) {
        if (bundle == null) {
            id = createId();
            applicationCallbacks.getUIComponentCache().create(id);
        } else {
            id = bundle.getString(COMPONENT_ID_KEY);
        }

        retrieveCache().newInstanceCreated();

        callback.created();
    }

    public void onSaveInstanceState(final Bundle bundle) {
        bundle.putString(COMPONENT_ID_KEY, id);
    }

    public void handleOnResume() {
        final Cache cache = retrieveCache();
        callback.onLoad(cache.isFirstRun(), cache.getKeyValueCache(), cache.getUiPersister());
        callback.onFocus();
    }

    public void handleOnPause() {
        final Cache cache = retrieveCache();
        callback.onFocusLost();
        callback.onSave(cache.getKeyValueCache(), cache.getUiPersister());
    }

    private Cache retrieveCache() {
        return applicationCallbacks.getUIComponentCache().get(id);
    }
}
