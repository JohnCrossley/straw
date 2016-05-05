package com.jccworld.strawtestapp.di.module.application;

import com.jccworld.straw.eventbus.EventBus;
import com.jccworld.straw.eventbus.EventBusFactory;
import com.jccworld.straw.eventbus.container.DomainEventBusContainer;
import com.jccworld.straw.eventbus.producer.EventBusProducer;
import com.jccworld.strawtestapp.application.Application;
import com.jccworld.strawtestapp.activity.LoginState;
import com.jccworld.strawtestapp.di.module.DIModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by johncrossley on 27/11/15.
 */

@Module(
        injects = { Application.class },
        library = true,
        complete = false
)
public class LoginEventBusModule implements DIModule {

    private Application application;

    public LoginEventBusModule(final Application application) {
        this.application = application;
    }

    @Provides @Singleton @Named("loginEventBusContainer")
    public DomainEventBusContainer<LoginState> provideLoginEventBusContainer(final EventBusFactory eventBusFactory) {
        return eventBusFactory.createDomainStickyEventBus();
    }

    @Provides @Named("loginEventBus")
    public EventBus<LoginState> provideLoginEventBusEventBus(@Named("loginEventBusContainer") DomainEventBusContainer<LoginState> container) {
        return container.getConsumer();
    }

    @Provides @Named("loginEventBusProducer")
    public EventBusProducer<LoginState> provideLoginEventBusEventBusProducer(@Named("loginEventBusContainer") DomainEventBusContainer<LoginState> container) {
        return container.getProducer();
    }
}