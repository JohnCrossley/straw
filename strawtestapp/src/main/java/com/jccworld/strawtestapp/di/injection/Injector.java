package com.jccworld.strawtestapp.di.injection;

import android.app.Activity;
import android.app.Service;

/**
 * Created by johncrossley on 26/11/15.
 */
public interface Injector {
    void inject(final Activity activity);
    void inject(final Service service);
    //overloads for Fragments, Views, etc
}
