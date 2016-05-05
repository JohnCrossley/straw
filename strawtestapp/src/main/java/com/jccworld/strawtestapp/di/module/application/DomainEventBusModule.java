package com.jccworld.strawtestapp.di.module.application;

import com.jccworld.straw.eventbus.container.DomainEventBusContainer;
import com.jccworld.straw.eventbus.EventBus;
import com.jccworld.straw.eventbus.EventBusFactory;
import com.jccworld.straw.eventbus.producer.EventBusProducer;
import com.jccworld.strawtestapp.application.Application;
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
        library = true
)
public class DomainEventBusModule implements DIModule {

    private Application application;

    public DomainEventBusModule(final Application application) {
        this.application = application;
    }

    @Provides
    public EventBusFactory provideEventBusFactory() {
        return new EventBusFactory(application);
    }

    //----------------------------------------------------------------------------------------------

    //new the actual event bus container - contains producer for service and consumer for activity
    @Provides @Singleton @Named("numberDomainConsumableEventBusContainer")
    public DomainEventBusContainer<Short> provideNumberDomainConsumableEventBusContainer(final EventBusFactory eventBusFactory) {
        return eventBusFactory.createDomainConsumableEventBus();
    }

    @Provides @Named("numberDomainConsumableEventBus")
    public EventBus<Short> provideNumberDomainConsumableEventBus(@Named("numberDomainConsumableEventBusContainer") DomainEventBusContainer<Short> container) {
        return container.getConsumer();
    }

    @Provides @Named("numberDomainConsumableEventBusProducer")
    public EventBusProducer<Short> provideNumberDomainConsumableEventBusProducer(@Named("numberDomainConsumableEventBusContainer") DomainEventBusContainer<Short> container) {
        return container.getProducer();
    }

    //----------------------------------------------------------------------------------------------

    @Provides @Singleton @Named("numberDomainStickyEventBusContainer")
    public DomainEventBusContainer<Short> provideNumberDomainStickyEventBusContainer(final EventBusFactory eventBusFactory) {
        return eventBusFactory.createDomainStickyEventBus();
    }

    @Provides @Named("numberDomainStickyEventBus")
    public EventBus<Short> provideNumberDomainStickyEventBus(@Named("numberDomainStickyEventBusContainer") DomainEventBusContainer<Short> container) {
        return container.getConsumer();
    }

    @Provides @Named("numberDomainStickyEventBusProducer")
    public EventBusProducer<Short> provideNumberDomainStickyEventBusProducer(@Named("numberDomainStickyEventBusContainer") DomainEventBusContainer<Short> container) {
        return container.getProducer();
    }


    //----------------------------------------------------------------------------------------------

    @Provides @Singleton @Named("numberDomainQueuedEventBusContainer")
    public DomainEventBusContainer<Short> provideNumberDomainQueuedEventBusContainer(final EventBusFactory eventBusFactory) {
        return eventBusFactory.createDomainQueuedEventBus();
    }

    @Provides @Named("numberDomainQueuedEventBus")
    public EventBus<Short> provideNumberDomainQueuedEventBus(@Named("numberDomainQueuedEventBusContainer") DomainEventBusContainer<Short> container) {
        return container.getConsumer();
    }

    @Provides @Named("numberDomainQueuedEventBusProducer")
    public EventBusProducer<Short> provideNumberDomainQueuedEventBusProducer(@Named("numberDomainQueuedEventBusContainer") DomainEventBusContainer<Short> container) {
        return container.getProducer();
    }

    //----------------------------------------------------------------------------------------------

    @Provides @Singleton @Named("wordDomainConsumableEventBusContainer")
    public DomainEventBusContainer<String> provideWordDomainConsumableEventBusContainer(final EventBusFactory eventBusFactory) {
        return eventBusFactory.createDomainConsumableEventBus();
    }

    @Provides @Named("wordDomainConsumableEventBus")
    public EventBus<String> provideWordDomainConsumableEventBus(@Named("wordDomainConsumableEventBusContainer") DomainEventBusContainer<String> container) {
        return container.getConsumer();
    }

    @Provides @Named("wordDomainConsumableEventBusProducer")
    public EventBusProducer<String> provideWordDomainConsumableEventBusProducer(@Named("wordDomainConsumableEventBusContainer") DomainEventBusContainer<String> container) {
        return container.getProducer();
    }

    //----------------------------------------------------------------------------------------------

    @Provides @Singleton @Named("wordDomainStickyEventBusContainer")
    public DomainEventBusContainer<String> provideWordDomainStickyEventBusContainer(final EventBusFactory eventBusFactory) {
        return eventBusFactory.createDomainStickyEventBus();
    }

    @Provides @Named("wordDomainStickyEventBus")
    public EventBus<String> provideWordDomainStickyEventBus(@Named("wordDomainStickyEventBusContainer") DomainEventBusContainer<String> container) {
        return container.getConsumer();
    }

    @Provides @Named("wordDomainStickyEventBusProducer")
    public EventBusProducer<String> provideWordDomainStickyEventBusProducer(@Named("wordDomainStickyEventBusContainer") DomainEventBusContainer<String> container) {
        return container.getProducer();
    }

    //----------------------------------------------------------------------------------------------

    @Provides @Singleton @Named("wordDomainQueuedEventBusContainer")
    public DomainEventBusContainer<String> provideWordDomainQueuedEventBusContainer(final EventBusFactory eventBusFactory) {
        return eventBusFactory.createDomainQueuedEventBus();
    }

    @Provides @Named("wordDomainQueuedEventBus")
    public EventBus<String> provideWordDomainQueuedEventBus(@Named("wordDomainQueuedEventBusContainer") DomainEventBusContainer<String> container) {
        return container.getConsumer();
    }

    @Provides @Named("wordDomainQueuedEventBusProducer")
    public EventBusProducer<String> provideWordDomainQueuedEventBusProducer(@Named("wordDomainQueuedEventBusContainer") DomainEventBusContainer<String> container) {
        return container.getProducer();
    }
}