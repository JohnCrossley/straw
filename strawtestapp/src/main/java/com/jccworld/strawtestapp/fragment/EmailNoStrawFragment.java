package com.jccworld.strawtestapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jccworld.strawtestapp.R;

/**
 * Created by johncrossley on 28/04/16.
 */
public class EmailNoStrawFragment extends Fragment implements View.OnClickListener {

    private Button sendButton;
    private EditText subjectEditText;
    private EditText bodyEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_email, container);
    }

    @Override
    public void onStart() {
        super.onStart();

        sendButton = (Button) getView().findViewById(R.id.send);
        sendButton.setOnClickListener(this);

        subjectEditText = (EditText) getView().findViewById(R.id.subject);
        bodyEditText = (EditText) getView().findViewById(R.id.body);

        subjectEditText.setText("Without Straw (Normal Android)");
        bodyEditText.setText(R.string.bodySample);
    }

    @Override
    public void onClick(View v) {
        sendButton.setText("Wait...");
        sendButton.setEnabled(false);
        subjectEditText.setEnabled(false);
        bodyEditText.setEnabled(false);
    }
}
