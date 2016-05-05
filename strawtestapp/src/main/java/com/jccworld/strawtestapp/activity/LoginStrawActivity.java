package com.jccworld.strawtestapp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jccworld.straw.KeyValueCache;
import com.jccworld.straw.UIPersister;
import com.jccworld.straw.activity.BaseActivity;
import com.jccworld.straw.eventbus.EventBus;
import com.jccworld.straw.eventbus.EventBusListener;
import com.jccworld.straw.ui.persisters.Persisted;
import com.jccworld.strawtestapp.R;
import com.jccworld.strawtestapp.di.injection.ProductionInjector;
import com.jccworld.strawtestapp.service.LoginStrawService;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by johncrossley on 26/04/16.
 */
public class LoginStrawActivity extends BaseActivity implements View.OnClickListener, EventBusListener<LoginState> {
    @Persisted
    private TextView messageTextView;

    @Persisted
    private Button loginBtn;

    @Persisted
    private Button forgotBtn;

    @Persisted
    private EditText usernameEditText;

    @Persisted
    private EditText passwordEditText;

    @Persisted
    private ProgressBar progressBar;

    @Inject
    @Named("loginEventBus")
    EventBus<LoginState> loginStateEventBus;

    @Override
    public void created() {
        new ProductionInjector().inject(this);

        setContentView(R.layout.activity_login);

        messageTextView = (TextView) findViewById(R.id.message);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        loginBtn = (Button) findViewById(R.id.login);
        loginBtn.setOnClickListener(this);
        forgotBtn = (Button) findViewById(R.id.forgot);
        forgotBtn.setOnClickListener(this);

        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
    }

    @Override
    public void onFocus() {
        loginStateEventBus.attachListener(this);
    }

    @Override
    public void onFocusLost() {
        loginStateEventBus.detachListener();
    }

    @Override
    public void onSave(KeyValueCache keyValueCache, UIPersister uiPersister) {
        uiPersister.save(this);
    }

    @Override
    public void onLoad(boolean firstRun, KeyValueCache keyValueCache, UIPersister uiPersister) {
        if (!firstRun) {
            uiPersister.load(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == loginBtn.getId()) {
            messageTextView.setVisibility(View.VISIBLE);
            messageTextView.setText(R.string.loading);
            progressBar.setVisibility(View.VISIBLE);

            usernameEditText.setEnabled(false);
            passwordEditText.setEnabled(false);
            loginBtn.setEnabled(false);
            forgotBtn.setEnabled(false);

            //hit service, wait for response
            loginStateEventBus.start();

            startService(new Intent(this, LoginStrawService.class));
        }
    }

    @Override
    public void onEvent(LoginState loginState) {
        loginStateEventBus.stop();

        if (loginState == LoginState.LOGGED_IN) {
            finish();
            startActivity(new Intent(this, DashboardActivity.class));
        }
    }
}
