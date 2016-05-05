package com.jccworld.straw.fakes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jccworld.straw.KeyValueCache;
import com.jccworld.straw.UIPersister;
import com.jccworld.straw.activity.ActivityCallbacks;
import com.jccworld.straw.activity.BaseFragment;
import com.jccworld.straw.activity.FragmentCallbacks;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

/**
 * Created by johncrossley on 21/04/16.
 */
public class FragmentExtendingBaseFragment extends BaseFragment {

    // allows us to see each call being made by the framework as we can't easily spy
    // the Activity itself because Robolectric is a steaming mess
    private final ActivityCallbacks spyActivityCallbacks;
    private final FragmentCallbacks spyFragmentCallbacks;

    public FragmentExtendingBaseFragment() {
        spyActivityCallbacks = mock(ActivityCallbacks.class);
        spyFragmentCallbacks = spy(FragmentCallbacks.class);
    }

    @Override
    public void created() {
        spyActivityCallbacks.created();
    }

    @Override
    public void onFocus() {
        spyActivityCallbacks.onFocus();
    }

    @Override
    public void onFocusLost() {
        spyActivityCallbacks.onFocusLost();
    }

    @Override
    public void onSave(final KeyValueCache keyValueCache, final UIPersister uiPersister) {
        spyActivityCallbacks.onSave(keyValueCache, uiPersister);
    }

    @Override
    public void onLoad(final boolean firstRun, final KeyValueCache keyValueCache, final UIPersister uiPersister) {
        spyActivityCallbacks.onLoad(firstRun, keyValueCache, uiPersister);
    }

    @Override
    public View inflateView(final LayoutInflater inflater, final ViewGroup container) {
        spyFragmentCallbacks.inflateView(inflater, container);
        return mock(View.class);
    }

    public ActivityCallbacks getSpyActivityCallbacks() {
        return spyActivityCallbacks;
    }

    public FragmentCallbacks getSpyFragmentCallbacks() {
        return spyFragmentCallbacks;
    }
}
