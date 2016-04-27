package com.jccworld.strawtestapp.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jccworld.strawtestapp.R;
import com.jccworld.strawtestapp.service.LoginNoStrawService;

/**
 * Created by johncrossley on 26/04/16.
 */
public class LoginNoStrawActivity extends Activity implements View.OnClickListener {
    private TextView messageTextView;
    private Button loginBtn;
    private Button forgotBtn;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private ProgressBar progressBar;

    private BroadcastReceiver loginBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        messageTextView = (TextView) findViewById(R.id.message);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        loginBtn = (Button) findViewById(R.id.login);
        loginBtn.setOnClickListener(this);
        forgotBtn = (Button) findViewById(R.id.forgot);
        forgotBtn.setOnClickListener(this);

        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);

        loginBroadcastReceiver = new LoginBroadcastReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(loginBroadcastReceiver, new IntentFilter(LoginNoStrawService.LOGIN_NO_STRAW_SERVICE_RESPONSE_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(loginBroadcastReceiver);
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

            startService(new Intent(this, LoginNoStrawService.class));
        }
    }

    private class LoginBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            LoginNoStrawActivity.this.finish();
            startActivity(new Intent(LoginNoStrawActivity.this, DashboardActivity.class));
        }
    }

}
