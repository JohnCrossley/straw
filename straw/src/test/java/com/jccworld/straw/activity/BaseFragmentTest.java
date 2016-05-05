package com.jccworld.straw.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jccworld.straw.BuildConfig;
import com.jccworld.straw.KeyValueCache;
import com.jccworld.straw.UIComponentProxy;
import com.jccworld.straw.UIPersister;
import com.jccworld.straw.fakes.FakeActivity;
import com.jccworld.straw.fakes.FakeApplication;
import com.jccworld.straw.fakes.FragmentExtendingBaseFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.verify;

/**
 * Created by johncrossley on 29/04/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, application = FakeApplication.class)
public class BaseFragmentTest {
    private FragmentExtendingBaseFragment sut;

    private ActivityCallbacks spyActivityDelegate;
    private FragmentCallbacks spyFragmentDelegate;

    @Mock
    private Activity mockActivity;

    @Before
    public void setUp() throws Exception {
        sut = new FragmentExtendingBaseFragment();
        spyActivityDelegate = sut.getSpyActivityCallbacks();
        spyFragmentDelegate = sut.getSpyFragmentCallbacks();
    }

    public static void startFragment(final Fragment fragment) {
        final FakeActivity activity = Robolectric.buildActivity(FakeActivity.class)
                .create()
                .start()
                .resume()
                .get();

        FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment, null);
        fragmentTransaction.commit();
    }

    @Test
    public void onCreateCallsImplementationCreated() {
        // run
        startFragment(sut);

        // verify
        verify(spyActivityDelegate).created();
    }

    @Test
    public void onCreatedOnResumeCallsImplementationCreatedAndOnFocus() {
        // init
        final KeyValueCache expectedKeyValueCache = new KeyValueCache();
        final InOrder inOrder = Mockito.inOrder(spyActivityDelegate);

        // run
        startFragment(sut);

        // verify
        inOrder.verify(spyActivityDelegate).onLoad(eq(true), eq(expectedKeyValueCache), any(UIPersister.class));
        inOrder.verify(spyActivityDelegate).onFocus();
    }

    @Test
    public void onCreatedOnResumeSecondCallSetsFirstCallFlagToFalse() {
        // init
        final KeyValueCache expectedKeyValueCache = new KeyValueCache();
        final InOrder inOrder = Mockito.inOrder(spyActivityDelegate);
        final Bundle savedInstanceStateBundle = new Bundle();
        startFragment(sut);
        sut.onSaveInstanceState(savedInstanceStateBundle);
        Mockito.reset(spyActivityDelegate);

        // run (2)
        sut.onActivityCreated(savedInstanceStateBundle);
        sut.onResume();

        // verify
        inOrder.verify(spyActivityDelegate).onLoad(eq(false), eq(expectedKeyValueCache), any(UIPersister.class));
        inOrder.verify(spyActivityDelegate).onFocus();
    }

    @Test
    public void onCreatedSetsActivityId() {
        // init
        final Bundle savedInstanceStateBundle = new Bundle();
        startFragment(sut);
        sut.onActivityCreated(null);
        sut.onResume();

        // run
        sut.onSaveInstanceState(savedInstanceStateBundle);//writes component id from instance to bundle

        // verify
        assertNotNull(savedInstanceStateBundle.getString(UIComponentProxy.COMPONENT_ID_KEY));
    }

    @Test
    public void onPauseCallsImplementationOnFocusLostOnSave() {
        // init
        final KeyValueCache expectedKeyValueCache = new KeyValueCache();
        final InOrder inOrder = Mockito.inOrder(spyActivityDelegate);
        startFragment(sut);
        sut.onActivityCreated(null);
        sut.onResume();

        // run
        sut.onPause();

        // verify
        inOrder.verify(spyActivityDelegate).onFocusLost();
        inOrder.verify(spyActivityDelegate).onSave(eq(expectedKeyValueCache), any(UIPersister.class));
    }

    @Test
    public void createViewIsDelegatedTo() {
        // run
        startFragment(sut);

        // verify
        verify(spyFragmentDelegate).inflateView(notNull(LayoutInflater.class), any(ViewGroup.class));
    }
}