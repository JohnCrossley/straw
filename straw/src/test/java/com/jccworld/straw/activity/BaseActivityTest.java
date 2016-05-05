package com.jccworld.straw.activity;

import android.os.Bundle;

import com.jccworld.straw.BuildConfig;
import com.jccworld.straw.KeyValueCache;
import com.jccworld.straw.UIComponentProxy;
import com.jccworld.straw.UIPersister;
import com.jccworld.straw.fakes.ActivityExtendingBaseActivity;
import com.jccworld.straw.fakes.ValidApplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by johncrossley on 21/04/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, application = ValidApplication.class)
public class BaseActivityTest {
    private ActivityController<ActivityExtendingBaseActivity> activityController;
    private ActivityExtendingBaseActivity sut;
    private ActivityCallbacks spySutDelegate;

    @Before
    public void setUp() throws Exception {
        activityController = Robolectric.buildActivity(ActivityExtendingBaseActivity.class);
        sut = activityController.get();
        spySutDelegate = sut.getSpyActivityCallbacks();
    }

    @Test
    public void onCreateCallsImplementationCreated() {
        // run
        activityController.create();

        // verify
        verify(spySutDelegate).created();
    }

    @Test
    public void onCreatedOnResumeCallsImplementationCreatedAndOnFocus() {
        // init
        final KeyValueCache expectedKeyValueCache = new KeyValueCache();
        final InOrder inOrder = Mockito.inOrder(spySutDelegate);

        // run
        activityController.create();
        activityController.resume();

        // verify
        inOrder.verify(spySutDelegate).onLoad(eq(true), eq(expectedKeyValueCache), any(UIPersister.class));
        inOrder.verify(spySutDelegate).onFocus();
    }

    @Test
    public void onCreatedOnResumeSecondCallSetsFirstCallFlagToFalse() {
        // init
        final KeyValueCache expectedKeyValueCache = new KeyValueCache();
        final InOrder inOrder = Mockito.inOrder(spySutDelegate);
        final Bundle savedInstanceStateBundle = new Bundle();
        activityController.create();
        activityController.resume();
        activityController.saveInstanceState(savedInstanceStateBundle);
        Mockito.reset(spySutDelegate);

        // run (2)
        activityController.create(savedInstanceStateBundle);//recreate
        activityController.resume();

        // verify
        inOrder.verify(spySutDelegate).onLoad(eq(false), eq(expectedKeyValueCache), any(UIPersister.class));
        inOrder.verify(spySutDelegate).onFocus();
    }

    @Test
    public void onCreatedSetsActivityId() {
        // init
        final Bundle savedInstanceStateBundle = new Bundle();
        activityController.create();
        activityController.resume();

        // run
        activityController.saveInstanceState(savedInstanceStateBundle);//writes component id from instance to bundle

        // verify
        assertNotNull(savedInstanceStateBundle.getString(UIComponentProxy.COMPONENT_ID_KEY));
    }

    @Test
    public void onPauseCallsImplementationOnFocusLostOnSave() {
        // init
        final KeyValueCache expectedKeyValueCache = new KeyValueCache();
        final InOrder inOrder = Mockito.inOrder(spySutDelegate);
        activityController.create();
        activityController.resume();

        // run
        activityController.pause();

        // verify
        inOrder.verify(spySutDelegate).onFocusLost();
        inOrder.verify(spySutDelegate).onSave(eq(expectedKeyValueCache), any(UIPersister.class));
    }
}