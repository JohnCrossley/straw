package com.jccworld.strawtestapp.di.module.application;

import com.jccworld.strawtestapp.application.Application;
import com.jccworld.strawtestapp.di.module.DIModule;

import dagger.Module;

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
