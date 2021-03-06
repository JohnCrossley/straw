package com.jccworld.strawtestapp.di.injection;

import android.app.Activity;
import android.app.Service;
import android.content.Context;

import com.jccworld.strawtestapp.application.Application;
import com.jccworld.strawtestapp.di.module.activity.ActivityModule;
import com.jccworld.strawtestapp.di.module.service.ServiceModule;

/**
 * Created by johncrossley on 25/11/15.
 */
public class ProductionInjector implements Injector {

    @Override
    public void inject(final Activity activity) {
        getApplication(activity.getApplicationContext())
                .getObjectGraph()
                .plus(new ActivityModule(activity))
                .inject(activity);
    }

    @Override
    public void inject(final Service service) {
        getApplication(service.getApplicationContext())
                .getObjectGraph()
                .plus(new ServiceModule())
                .inject(service);
    }

    private static Application getApplication(final Context appContext) {
        if (appContext instanceof Application) {
            return (Application) appContext;
        } else {
            throw new RuntimeException("Application must extend "  + Application.class.getName());
        }
    }
}
