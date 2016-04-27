package com.jccworld.strawtestapp.di.module.application;

import com.jccworld.straw.eventbus.EventBus;
import com.jccworld.straw.eventbus.EventBusFactory;
import com.jccworld.straw.eventbus.container.PojoEventBusContainer;
import com.jccworld.straw.eventbus.producer.EventBusProducer;
import com.jccworld.strawtestapp.Application;
import com.jccworld.strawtestapp.di.module.DIModule;
import com.jccworld.strawtestapp.eventbus.converter.ToShort;
import com.jccworld.strawtestapp.eventbus.converter.ToWord;

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
public class PojoEventBusModule implements DIModule {
    @Provides @Singleton @Named("numberPojoConsumableEventBusContainer")
    public PojoEventBusContainer<Short> provideNumberPojoConsumableEventBusContainer(final EventBusFactory eventBusFactory) {
        return eventBusFactory.createPojoConsumableEventBus(new ToShort(), new ToShort());
    }

    @Provides @Named("numberPojoConsumableEventBus")
    public EventBus<Short> provideNumberPojoConsumableEventBus(@Named("numberPojoConsumableEventBusContainer") PojoEventBusContainer<Short> container) {
        return container.getConsumer();
    }

    @Provides @Named("numberPojoConsumableEventBusProducer")
    public EventBusProducer<Short> provideNumberPojoConsumableEventBusProducer(@Named("numberPojoConsumableEventBusContainer") PojoEventBusContainer<Short> container) {
        return container.getProducer();
    }

    //----------------------------------------------------------------------------------------------

    @Provides @Singleton @Named("numberPojoStickyEventBusContainer")
    public PojoEventBusContainer<Short> provideNumberPojoStickyEventBusContainer(final EventBusFactory eventBusFactory) {
        return eventBusFactory.createPojoStickyEventBus(new ToShort(), new ToShort());
    }

    @Provides @Named("numberPojoStickyEventBus")
    public EventBus<Short> provideNumberPojoStickyEventBus(@Named("numberPojoStickyEventBusContainer") PojoEventBusContainer<Short> container) {
        return container.getConsumer();
    }

    @Provides @Named("numberPojoStickyEventBusProducer")
    public EventBusProducer<Short> provideNumberPojoStickyEventBusProducer(@Named("numberPojoStickyEventBusContainer") PojoEventBusContainer<Short> container) {
        return container.getProducer();
    }

    //----------------------------------------------------------------------------------------------

    @Provides @Singleton @Named("numberPojoQueuedEventBusContainer")
    public PojoEventBusContainer<Short> provideNumberPojoQueuedEventBusContainer(final EventBusFactory eventBusFactory) {
        return eventBusFactory.createPojoQueuedEventBus(new ToShort(), new ToShort());
    }

    @Provides @Named("numberPojoQueuedEventBus")
    public EventBus<Short> provideNumberPojoQueuedEventBus(@Named("numberPojoQueuedEventBusContainer") PojoEventBusContainer<Short> container) {
        return container.getConsumer();
    }

    @Provides @Named("numberPojoQueuedEventBusProducer")
    public EventBusProducer<Short> provideNumberPojoQueuedEventBusProducer(@Named("numberPojoQueuedEventBusContainer") PojoEventBusContainer<Short> container) {
        return container.getProducer();
    }

    //----------------------------------------------------------------------------------------------

    @Provides @Singleton @Named("wordPojoConsumableEventBusContainer")
    public PojoEventBusContainer<String> provideWordPojoConsumableEventBusContainer(final EventBusFactory eventBusFactory) {
        return eventBusFactory.createPojoConsumableEventBus(new ToWord(), new ToWord());
    }

    @Provides @Named("wordPojoConsumableEventBus")
    public EventBus<String> provideWordPojoConsumableEventBus(@Named("wordPojoConsumableEventBusContainer") PojoEventBusContainer<String> container) {
        return container.getConsumer();
    }

    @Provides @Named("wordPojoConsumableEventBusProducer")
    public EventBusProducer<String> provideWordPojoConsumableEventBusProducer(@Named("wordPojoConsumableEventBusContainer") PojoEventBusContainer<String> container) {
        return container.getProducer();
    }

    //----------------------------------------------------------------------------------------------

    @Provides @Singleton @Named("wordPojoStickyEventBusContainer")
    public PojoEventBusContainer<String> provideWordPojoStickyEventBusContainer(final EventBusFactory eventBusFactory) {
        return eventBusFactory.createPojoStickyEventBus(new ToWord(), new ToWord());
    }

    @Provides @Named("wordPojoStickyEventBus")
    public EventBus<String> provideWordPojoStickyEventBus(@Named("wordPojoStickyEventBusContainer") PojoEventBusContainer<String> container) {
        return container.getConsumer();
    }

    @Provides @Named("wordPojoStickyEventBusProducer")
    public EventBusProducer<String> provideWordPojoStickyEventBusProducer(@Named("wordPojoStickyEventBusContainer") PojoEventBusContainer<String> container) {
        return container.getProducer();
    }

    //----------------------------------------------------------------------------------------------

    @Provides @Singleton @Named("wordPojoQueuedEventBusContainer")
    public PojoEventBusContainer<String> provideWordPojoQueuedEventBusContainer(final EventBusFactory eventBusFactory) {
        return eventBusFactory.createPojoQueuedEventBus(new ToWord(), new ToWord());
    }

    @Provides @Named("wordPojoQueuedEventBus")
    public EventBus<String> provideWordPojoQueuedEventBus(@Named("wordPojoQueuedEventBusContainer") PojoEventBusContainer<String> container) {
        return container.getConsumer();
    }

    @Provides @Named("wordPojoQueuedEventBusProducer")
    public EventBusProducer<String> provideWordPojoQueuedEventBusProducer(@Named("wordPojoQueuedEventBusContainer") PojoEventBusContainer<String> container) {
        return container.getProducer();
    }
}