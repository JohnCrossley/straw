package com.jccworld.straw.fakes;

import com.jccworld.straw.KeyValueCache;
import com.jccworld.straw.UIPersister;
import com.jccworld.straw.activity.ActivityCallbacks;
import com.jccworld.straw.activity.BaseActivity;

import static org.mockito.Mockito.spy;

/**
 * Created by johncrossley on 21/04/16.
 */
public class ActivityExtendingBaseActivity extends BaseActivity {
    // allows us to see each call being made by the framework as we can't easily spy
    // the Activty itself because Robolectric is a steaming mess
    private ActivityCallbacks spyActivityCallbacks;

    public ActivityExtendingBaseActivity() {
        spyActivityCallbacks = spy(ActivityCallbacks.class);
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
    public void onSave(KeyValueCache keyValueCache, UIPersister uiPersister) {
        spyActivityCallbacks.onSave(keyValueCache, uiPersister);
    }

    @Override
    public void onLoad(boolean firstRun, KeyValueCache keyValueCache, UIPersister uiPersister) {
        spyActivityCallbacks.onLoad(firstRun, keyValueCache, uiPersister);
    }

    public ActivityCallbacks getSpyActivityCallbacks() {
        return spyActivityCallbacks;
    }

    public String getActivityIdTestImpl() {
        return getActivityId();
    }
}
