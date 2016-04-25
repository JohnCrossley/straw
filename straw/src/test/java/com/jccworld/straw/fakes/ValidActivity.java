package com.jccworld.straw.fakes;

import android.app.Activity;

import com.jccworld.straw.KeyValueCache;
import com.jccworld.straw.UIPersister;
import com.jccworld.straw.activity.ActivityCallbacks;

/**
 * Created by johncrossley on 21/04/16.
 */
public class ValidActivity extends Activity implements ActivityCallbacks {
    @Override
    public void created() {
        //NO-OP
    }

    @Override
    public void onFocus() {
        //NO-OP
    }

    @Override
    public void onFocusLost() {
        //NO-OP
    }

    @Override
    public void onSave(KeyValueCache keyValueCache, UIPersister uiPersister) {
        //NO-OP
    }

    @Override
    public void onLoad(boolean firstRun, KeyValueCache keyValueCache, UIPersister uiPersister) {
        //NO-OP
    }
}
