package com.jccworld.straw.activity;

import android.app.Activity;
import android.content.Context;

import com.jccworld.straw.BuildConfig;
import com.jccworld.straw.Cache;
import com.jccworld.straw.application.ApplicationCallbacks;
import com.jccworld.straw.fakes.FakeActivity;
import com.jccworld.straw.fakes.InvalidActivity;
import com.jccworld.straw.fakes.InvalidApplicationContext;
import com.jccworld.straw.fakes.ValidActivity;
import com.jccworld.straw.fakes.ValidApplicationContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by johncrossley on 21/04/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class ActivityControllerTest {
    private static final Class A_VALID_ACTIVITY = ValidActivity.class;
    private static final Class AN_INVALID_ACTIVITY = InvalidActivity.class;

    private ActivityController sut;

    @Before
    public void setUp() throws Exception {
        //sut = new ActivityController();
    }

    @Test(expected = RuntimeException.class)
    @Config(application = InvalidApplicationContext.class)
    public void rejectsActivityNotImplementingApplicationCallbacksInterface() {
        // init
        final Activity invalidContext = Robolectric.buildActivity(FakeActivity.class).create().get();

        // run
        ActivityController.startActivity(invalidContext, A_VALID_ACTIVITY);
    }

    @Test(expected = RuntimeException.class)
    public void rejectsApplicationContextNotImplementingApplicationCallbacksInterface() {
        // init
        final Activity context = Robolectric.buildActivity(FakeActivity.class).create().get();

        // run
        ActivityController.startActivity(context, AN_INVALID_ACTIVITY);
    }

    @Test
    @Config(application = ValidApplicationContext.class)
    public void startsActivityWithNoInitParams() {
        // init
        final Activity context = Robolectric.buildActivity(FakeActivity.class).create().get();

        // run
        ActivityController.startActivity(context, A_VALID_ACTIVITY);
    }

    @Test
    @Config(application = ValidApplicationContext.class)
    public void startsActivityWithInitParams() {
        // init
        final Activity context = Robolectric.buildActivity(FakeActivity.class).create().get();
        final ApplicationCallbacks application = (ApplicationCallbacks) context.getApplicationContext();
        final Map<String, Object> initParams = new HashMap<>();

        // run
        final String activityId = ActivityController.startActivity(context, A_VALID_ACTIVITY, initParams);

        // verify
        assertEquals(new Cache(initParams), application.getActivityCache().get(activityId));
    }
}