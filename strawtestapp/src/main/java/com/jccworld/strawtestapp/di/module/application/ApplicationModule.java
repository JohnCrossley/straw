package com.jccworld.strawtestapp.di.module.application;

import com.jccworld.strawtestapp.Application;
import com.jccworld.strawtestapp.di.module.DIModule;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by johncrossley on 27/11/15.
 */

@Module(
        injects = { Application.class },
        library = true
)
public class ApplicationModule implements DIModule {

    private Application application;

    public ApplicationModule(final Application application) {
        this.application = application;
    }


}