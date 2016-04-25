package com.jccworld.straw.fakes;

import android.widget.TextView;

import com.jccworld.straw.KeyValueCache;
import com.jccworld.straw.UIPersister;
import com.jccworld.straw.activity.BaseActivity;
import com.jccworld.straw.ui.persisters.Persisted;

/**
 * Created by johncrossley on 22/04/16.
 */
public class ActivityWithComponent extends BaseActivity {
    @Persisted
    public TextView myTextView;

    public ActivityWithComponent() {

    }

    @Override
    public void created() {
        myTextView = new TextView(this);
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
}
