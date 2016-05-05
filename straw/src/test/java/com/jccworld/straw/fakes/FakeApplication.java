package com.jccworld.straw.fakes;

import android.app.Application;

import com.jccworld.straw.activity.UIComponentCache;
import com.jccworld.straw.application.ApplicationCallbacks;

/**
 * Created by johncrossley on 05/05/16.
 */
public class FakeApplication extends Application implements ApplicationCallbacks {
    private UIComponentCache uiComponentCache;

    public FakeApplication() {
        uiComponentCache = new UIComponentCache();
    }

    @Override
    public UIComponentCache getUIComponentCache() {
        return uiComponentCache;
    }
}
