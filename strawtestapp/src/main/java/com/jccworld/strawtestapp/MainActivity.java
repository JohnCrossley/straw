package com.jccworld.strawtestapp;

import android.view.View;
import android.widget.Button;

import com.jccworld.straw.KeyValueCache;
import com.jccworld.straw.UIPersister;
import com.jccworld.straw.activity.ActivityController;
import com.jccworld.straw.activity.BaseActivity;

/**
 * Created by jcc on 19/11/15.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button uiTestsButton;
    private Button eventBusButton;

    @Override
    public void created() {
        setContentView(R.layout.activity_main);
        uiTestsButton = (Button) findViewById(R.id.uiTests);
        uiTestsButton.setOnClickListener(this);
        eventBusButton = (Button) findViewById(R.id.eventBus);
        eventBusButton.setOnClickListener(this);
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
    public void onClick(View v) {
        if (v.getId() == uiTestsButton.getId()) {
            ActivityController.startActivity(this, UITestActivity.class);
        } else if (v.getId() == eventBusButton.getId()) {
            ActivityController.startActivity(this, EventBusActivity.class);
        }
    }
}
