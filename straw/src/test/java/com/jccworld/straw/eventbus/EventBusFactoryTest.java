package com.jccworld.straw.eventbus;

import android.app.Application;

import com.jccworld.straw.eventbus.container.DomainEventBusContainer;
import com.jccworld.straw.eventbus.container.PojoEventBusContainer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * TODO: this is a factory of factories -> it should be broken into container factory and event bus
 * factory.  That way we can test that the same UUID is being propagated in the producer and consumer.
 *
 * Created by johncrossley on 25/04/16.
 */
public class EventBusFactoryTest {

    @Mock
    private Application mockApplication;

    @Mock
    private DomainToIntentConverter<? extends Object> mockDomainToIntentConverter;

    @Mock
    private IntentToPojoConverter mockIntentToPojoConverter;

    private EventBusFactory sut;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new EventBusFactory(mockApplication);
    }

    @Test
    public void testCreatePojoConsumableEventBus() throws Exception {
        // run
        final PojoEventBusContainer pojoConsumableEventBus = sut.createPojoConsumableEventBus(mockDomainToIntentConverter, mockIntentToPojoConverter);

        // verify
        assertNotNull(pojoConsumableEventBus);
        assertNotNull(pojoConsumableEventBus.getConsumer());
        assertNotNull(pojoConsumableEventBus.getProducer());
    }

    @Test
    public void testCreatePojoStickyEventBus() throws Exception {
        // run
        final PojoEventBusContainer pojoStickyEventBus = sut.createPojoStickyEventBus(mockDomainToIntentConverter, mockIntentToPojoConverter);

        // verify
        assertNotNull(pojoStickyEventBus);
        assertNotNull(pojoStickyEventBus.getConsumer());
        assertNotNull(pojoStickyEventBus.getProducer());
    }

    @Test
    public void testCreatePojoQueuedEventBus() throws Exception {
        // run
        final PojoEventBusContainer pojoQueuedEventBus = sut.createPojoQueuedEventBus(mockDomainToIntentConverter, mockIntentToPojoConverter);

        // verify
        assertNotNull(pojoQueuedEventBus);
        assertNotNull(pojoQueuedEventBus.getConsumer());
        assertNotNull(pojoQueuedEventBus.getProducer());
    }

    @Test
    public void testCreateDomainConsumableEventBus() throws Exception {
        // run
        final DomainEventBusContainer domainConsumableEventBus = sut.createDomainConsumableEventBus();

        // verify
        assertNotNull(domainConsumableEventBus);
        assertNotNull(domainConsumableEventBus.getConsumer());
        assertNotNull(domainConsumableEventBus.getProducer());
    }

    @Test
    public void testCreateDomainStickyEventBus() throws Exception {
        // run
        final DomainEventBusContainer domainStickyEventBus = sut.createDomainStickyEventBus();

        // verify
        assertNotNull(domainStickyEventBus);
        assertNotNull(domainStickyEventBus.getConsumer());
        assertNotNull(domainStickyEventBus.getProducer());
    }

    @Test
    public void testCreateDomainQueuedEventBus() throws Exception {
        // run
        final DomainEventBusContainer domainQueuedEventBus = sut.createDomainQueuedEventBus();

        // verify
        assertNotNull(domainQueuedEventBus);
        assertNotNull(domainQueuedEventBus.getConsumer());
        assertNotNull(domainQueuedEventBus.getProducer());
    }
}