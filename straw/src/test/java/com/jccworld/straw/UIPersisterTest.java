package com.jccworld.straw;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.jccworld.straw.fakes.ActivityWithComponent;
import com.jccworld.straw.fakes.FakeActivity;
import com.jccworld.straw.fakes.FragmentWithComponent;
import com.jccworld.straw.fakes.ValidApplication;
import com.jccworld.straw.ui.persisters.Persisted;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * Created by johncrossley on 22/04/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class UIPersisterTest {

    private UIPersister sut;

    @Before
    public void setUp() throws Exception {
        sut = new UIPersister();
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsNonUIComponent() {
        sut.save(new ActivityWithInvalidComponent());
    }

    @Test
    @Config(application = ValidApplication.class)
    public void savesAndLoadActivity() {
        final ActivityWithComponent one = Robolectric.buildActivity(ActivityWithComponent.class).setup().get();
        final ActivityWithComponent two = Robolectric.buildActivity(ActivityWithComponent.class).setup().get();

        one.myTextView.setText("Hello");

        //serialise data from one into sut
        sut.save(one);
        //deserialise data from sut into two
        sut.load(two);

        assertEquals(one.myTextView.getText(), two.myTextView.getText());
    }

    @Test
    @Config(application = ValidApplication.class)
    public void savesAndLoadFragment() {
        final FragmentWithComponent one = new FragmentWithComponent();
        final FragmentWithComponent two = new FragmentWithComponent();
        startFragment(one);
        startFragment(two);
        one.myTextView.setText("Hello");

        //serialise data from one into sut
        sut.save(one);
        //deserialise data from sut into two
        sut.load(two);

        assertEquals(one.myTextView.getText(), two.myTextView.getText());
    }


    private class ActivityWithInvalidComponent extends Activity {
        @Persisted
        Object o;
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

}