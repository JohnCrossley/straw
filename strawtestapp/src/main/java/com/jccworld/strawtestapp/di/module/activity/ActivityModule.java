package com.jccworld.strawtestapp.di.module.activity;

import android.app.Activity;

import com.jccworld.strawtestapp.activity.DomainEventBusActivity;
import com.jccworld.strawtestapp.activity.LoginNoStrawActivity;
import com.jccworld.strawtestapp.activity.LoginStrawActivity;
import com.jccworld.strawtestapp.activity.PojoEventBusActivity;
import com.jccworld.strawtestapp.di.module.DIModule;

import dagger.Module;

/**
 * Created by johncrossley on 27/11/15.
 */
@Module(
    injects = {
            LoginStrawActivity.class,
            DomainEventBusActivity.class,
            LoginNoStrawActivity.class,
            PojoEventBusActivity.class
        },
    complete = false
)
public class ActivityModule implements DIModule {
    private final Activity activity;

    public ActivityModule(final Activity activity) {
        this.activity = activity;
    }

}