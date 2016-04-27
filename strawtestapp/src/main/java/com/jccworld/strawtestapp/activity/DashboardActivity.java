package com.jccworld.strawtestapp.activity;

import com.jccworld.straw.KeyValueCache;
import com.jccworld.straw.UIPersister;
import com.jccworld.straw.activity.BaseActivity;
import com.jccworld.strawtestapp.R;

/**
 * Created by johncrossley on 26/04/16.
 */
public class DashboardActivity extends BaseActivity {

    @Override
    public void created() {
        setContentView(R.layout.activity_dashboard);
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
