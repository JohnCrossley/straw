package com.jccworld.strawtestapp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.jccworld.straw.KeyValueCache;
import com.jccworld.straw.UIPersister;
import com.jccworld.straw.activity.BaseActivity;
import com.jccworld.strawtestapp.R;

/**
 * Created by jcc on 19/11/15.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button uiActivityStrawTestsButton;
    private Button uiActivityNoStrawTestsButton;

    private Button uiFragmentTestButton;
    private Button eventBusPojoButton;
    private Button eventBusDomainButton;
    private Button loginStrawButton;
    private Button loginNoStrawButton;

    @Override
    public void created() {
        setContentView(R.layout.activity_main);
        uiActivityStrawTestsButton = (Button) findViewById(R.id.uiActivityStrawTests);
        uiActivityStrawTestsButton.setOnClickListener(this);
        uiActivityNoStrawTestsButton = (Button) findViewById(R.id.uiActivityNoStrawTests);
        uiActivityNoStrawTestsButton.setOnClickListener(this);


        uiFragmentTestButton = (Button) findViewById(R.id.uiFragmentTests);
        uiFragmentTestButton.setOnClickListener(this);
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
        if (v.getId() == uiActivityStrawTestsButton.getId()) {
            startActivity(new Intent(this, UIStrawTestActivity.class));
        } else if (v.getId() == uiActivityNoStrawTestsButton.getId()) {
            startActivity(new Intent(this, UINoStrawTestActivity.class));
        } else if (v.getId() == uiFragmentTestButton.getId()) {
            startActivity(new Intent(this, EmailActivity.class));
        } else if (v.getId() == eventBusPojoButton.getId()) {
            startActivity(new Intent(this, PojoEventBusActivity.class));
        } else if (v.getId() == eventBusDomainButton.getId()) {
            startActivity(new Intent(this, DomainEventBusActivity.class));
        } else if (v.getId() == loginStrawButton.getId()) {
            startActivity(new Intent(this, LoginStrawActivity.class));
        } else if (v.getId() == loginNoStrawButton.getId()) {
            startActivity(new Intent(this, LoginNoStrawActivity.class));
        }
    }
}
