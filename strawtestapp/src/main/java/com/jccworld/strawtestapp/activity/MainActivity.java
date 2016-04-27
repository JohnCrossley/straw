package com.jccworld.strawtestapp.activity;

import android.view.View;
import android.widget.Button;

import com.jccworld.straw.KeyValueCache;
import com.jccworld.straw.UIPersister;
import com.jccworld.straw.activity.ActivityController;
import com.jccworld.straw.activity.BaseActivity;
import com.jccworld.strawtestapp.R;

/**
 * Created by jcc on 19/11/15.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button uiTestsButton;
    private Button eventBusPojoButton;
    private Button eventBusDomainButton;
    private Button loginStrawButton;
    private Button loginNoStrawButton;

    @Override
    public void created() {
        setContentView(R.layout.activity_main);
        uiTestsButton = (Button) findViewById(R.id.uiTests);
        uiTestsButton.setOnClickListener(this);
        eventBusPojoButton = (Button) findViewById(R.id.eventBusPojo);
        eventBusPojoButton.setOnClickListener(this);
        eventBusDomainButton = (Button) findViewById(R.id.eventBusDomain);
        eventBusDomainButton.setOnClickListener(this);
        loginStrawButton = (Button) findViewById(R.id.loginStraw);
        loginStrawButton.setOnClickListener(this);
        loginNoStrawButton = (Button) findViewById(R.id.loginNoStraw);
        loginNoStrawButton.setOnClickListener(this);
    }

    @Override
    public void onFocus() {

    }

    @Override
    public void onFocusLost() {

    }

    @Override
    public void onSave(final KeyValueCache keyValueCache, final UIPersister uiPersister) {

    }

    @Override
    public void onLoad(boolean firstRun, KeyValueCache keyValueCache, UIPersister uiPersister) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == uiTestsButton.getId()) {
            ActivityController.startActivity(this, UITestActivity.class);
        } else if (v.getId() == eventBusPojoButton.getId()) {
            ActivityController.startActivity(this, PojoEventBusActivity.class);
        } else if (v.getId() == eventBusDomainButton.getId()) {
            ActivityController.startActivity(this, DomainEventBusActivity.class);
        } else if (v.getId() == loginStrawButton.getId()) {
            ActivityController.startActivity(this, LoginStrawActivity.class);
        } else if (v.getId() == loginNoStrawButton.getId()) {
            ActivityController.startActivity(this, LoginNoStrawActivity.class);
        }
    }
}
