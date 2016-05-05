package com.jccworld.straw.fakes;

import android.app.Application;

import com.jccworld.straw.activity.UIComponentCache;
import com.jccworld.straw.application.ApplicationCallbacks;

/**
 * Created by johncrossley on 21/04/16.
 */
public class ValidApplication extends Application implements ApplicationCallbacks {

    private UIComponentCache UIComponentCache = new UIComponentCache();

    @Override
    public UIComponentCache getUIComponentCache() {
        return UIComponentCache;
    }
}
