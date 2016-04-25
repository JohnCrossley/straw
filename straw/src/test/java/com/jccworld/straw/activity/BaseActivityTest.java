package com.jccworld.straw.activity;

import android.content.Intent;
import android.os.Bundle;

import com.jccworld.straw.BuildConfig;
import com.jccworld.straw.KeyValueCache;
import com.jccworld.straw.UIPersister;
import com.jccworld.straw.fakes.ActivityExtendingBaseActivity;
import com.jccworld.straw.fakes.ValidApplicationContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by johncrossley on 21/04/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, application = ValidApplicationContext.class)
public class BaseActivityTest {
    private static final String A_VALID_ACTIVITY_ID = "A VALID ACTIVITY ID";
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
        activityController.create();
        activityController.resume();
        Mockito.reset(spySutDelegate);

        // run (2)
        activityController.create();
        activityController.resume();

        // verify
        inOrder.verify(spySutDelegate).onLoad(eq(false), eq(expectedKeyValueCache), any(UIPersister.class));
        inOrder.verify(spySutDelegate).onFocus();
    }

    @Test
    public void onCreatedSetsActivityId() {
        // init
        activityController.withIntent(new Intent(sut, ActivityExtendingBaseActivity.class)
                .putExtra(com.jccworld.straw.activity.ActivityController.ACTIVITY_ID, A_VALID_ACTIVITY_ID));

        // run
        activityController.create();
        activityController.resume();

        // verify
        assertEquals(A_VALID_ACTIVITY_ID, sut.getActivityIdTestImpl());
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