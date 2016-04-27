package com.jccworld.straw.eventbus;

import android.app.Application;
import android.content.IntentFilter;

import com.jccworld.straw.eventbus.container.DomainEventBusContainer;
import com.jccworld.straw.eventbus.container.PojoEventBusContainer;
import com.jccworld.straw.eventbus.impl.DomainConsumableBroadcastReceiverEventBus;
import com.jccworld.straw.eventbus.impl.DomainQueuedBroadcastReceiverEventBus;
import com.jccworld.straw.eventbus.impl.DomainStickyBroadcastReceiverEventBus;
import com.jccworld.straw.eventbus.impl.PojoConsumableBroadcastReceiverEventBus;
import com.jccworld.straw.eventbus.impl.PojoQueuedBroadcastReceiverEventBus;
import com.jccworld.straw.eventbus.impl.PojoStickyBroadcastReceiverEventBus;
import com.jccworld.straw.eventbus.producer.DomainEventBusProducer;
import com.jccworld.straw.eventbus.producer.PojoEventBusProducer;

import java.util.UUID;

/**
 * Created by johncrossley on 07/01/16.
 */
public class EventBusFactory {
    private final Application application;

    public EventBusFactory(final Application application) {
        this.application = application;
    }

    private String createId() {
        return UUID.randomUUID().toString();
    }

    public <T> PojoEventBusContainer createPojoConsumableEventBus(final DomainToIntentConverter<T> domainToIntentConverter,
                                                                  final IntentToPojoConverter<T> intentToPojoConverter) {
        final String id = createId();

        final PojoConsumableBroadcastReceiverEventBus<T> consumer = new PojoConsumableBroadcastReceiverEventBus<>(application, new IntentFilter(id), intentToPojoConverter);
        final PojoEventBusProducer<T> producer = new PojoEventBusProducer<>(application, id, domainToIntentConverter);

        return new PojoEventBusContainer(consumer, producer);
    }

    public <T> PojoEventBusContainer<T> createPojoStickyEventBus(final DomainToIntentConverter<T> domainToIntentConverter,
                                                                 final IntentToPojoConverter<T> intentToPojoConverter) {
        final String id = createId();

        final PojoStickyBroadcastReceiverEventBus<T> consumer = new PojoStickyBroadcastReceiverEventBus<>(application, new IntentFilter(id), intentToPojoConverter);
        final PojoEventBusProducer<T> producer = new PojoEventBusProducer<>(application, id, domainToIntentConverter);

        return new PojoEventBusContainer<>(consumer, producer);
    }

    public <T> PojoEventBusContainer<T> createPojoQueuedEventBus(final DomainToIntentConverter<T> domainToIntentConverter,
                                                                 final IntentToPojoConverter<T> intentToPojoConverter) {
        final String id = createId();

        final PojoQueuedBroadcastReceiverEventBus<T> consumer = new PojoQueuedBroadcastReceiverEventBus<>(application, new IntentFilter(id), intentToPojoConverter);
        final PojoEventBusProducer<T> producer = new PojoEventBusProducer<>(application, id, domainToIntentConverter);

        return new PojoEventBusContainer<>(consumer, producer);
    }

    //----------------------------------------------------------------------------------------------

    public <T> DomainEventBusContainer<T> createDomainConsumableEventBus() {
        final String id = createId();

        final DomainConsumableBroadcastReceiverEventBus<T> consumer = new DomainConsumableBroadcastReceiverEventBus<>(application, new IntentFilter(id));
        final DomainEventBusProducer<T> producer = new DomainEventBusProducer<>((DomainEventBus) consumer, application, id);

        return new DomainEventBusContainer<>(consumer, producer);
    }

    public <T> DomainEventBusContainer<T> createDomainStickyEventBus() {
        final String id = createId();

        final DomainStickyBroadcastReceiverEventBus<T> consumer = new DomainStickyBroadcastReceiverEventBus<>(application, new IntentFilter(id));
        final DomainEventBusProducer<T> producer = new DomainEventBusProducer<>((DomainEventBus) consumer, application, id);

        return new DomainEventBusContainer<>(consumer, producer);
    }

    public <T> DomainEventBusContainer<T> createDomainQueuedEventBus() {
        final String id = createId();

        final DomainQueuedBroadcastReceiverEventBus<T> consumer = new DomainQueuedBroadcastReceiverEventBus<>(application, new IntentFilter(id));
        final DomainEventBusProducer<T> producer = new DomainEventBusProducer<>((DomainEventBus) consumer, application, id);

        return new DomainEventBusContainer<>(consumer, producer);
    }

}
