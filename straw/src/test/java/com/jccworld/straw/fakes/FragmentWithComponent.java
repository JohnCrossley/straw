package com.jccworld.straw.fakes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jccworld.straw.KeyValueCache;
import com.jccworld.straw.UIPersister;
import com.jccworld.straw.activity.BaseFragment;
import com.jccworld.straw.ui.persisters.Persisted;

/**
 * Created by johncrossley on 22/04/16.
 */
public class FragmentWithComponent extends BaseFragment {
    @Persisted
    public TextView myTextView;

    public FragmentWithComponent() {

    }

    @Override
    public void created() {
        myTextView = new TextView(getActivity());
    }

    @Override
    public void onFocus() {

    }

    @Override
    public void onFocusLost() {

    }

    @Override
    public void onSave(KeyValueCache keyValueCache, UIPersister uiPersister) {

    }

    @Override
    public void onLoad(boolean firstRun, KeyValueCache keyValueCache, UIPersister uiPersister) {

    }

    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container) {
        return new LinearLayout(getActivity());
    }
}
