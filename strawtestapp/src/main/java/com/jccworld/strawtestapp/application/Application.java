package com.jccworld.strawtestapp.application;

import com.jccworld.straw.activity.UIComponentCache;
import com.jccworld.straw.application.ApplicationCallbacks;
import com.jccworld.strawtestapp.debug.VerboseApplication;
import com.jccworld.strawtestapp.di.module.application.ApplicationModule;
import com.jccworld.strawtestapp.di.module.application.DomainEventBusModule;
import com.jccworld.strawtestapp.di.module.application.LoginEventBusModule;
import com.jccworld.strawtestapp.di.module.application.PojoEventBusModule;

import dagger.ObjectGraph;

/**
 * Created by jcc on 04/11/15.
 */
public class Application extends VerboseApplication implements ApplicationCallbacks {
    private ObjectGraph objectGraph;
    private UIComponentCache UIComponentCache;

    @Override
    public void onCreate() {
        super.onCreate();

        System.out.println("JCC: APP STARTED ----------------------------------------------------------------------------------------------------");

        objectGraph = ObjectGraph.create(new ApplicationModule(this));
        objectGraph = objectGraph.plus(new PojoEventBusModule(), new DomainEventBusModule(this), new LoginEventBusModule(this));

        objectGraph.inject(this);

        UIComponentCache = new UIComponentCache();
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }

    @Override
    public UIComponentCache getUIComponentCache() {
        return UIComponentCache;
    }
}
