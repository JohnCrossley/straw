package com.jccworld.strawtestapp.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jccworld.straw.KeyValueCache;
import com.jccworld.straw.UIPersister;
import com.jccworld.straw.activity.BaseFragment;
import com.jccworld.straw.ui.persisters.Persisted;
import com.jccworld.strawtestapp.R;

/**
 * Created by johncrossley on 28/04/16.
 */
public class EmailWithStrawFragment extends BaseFragment implements View.OnClickListener {

    @Persisted
    private Button sendButton;

    @Persisted
    private EditText subjectEditText;

    @Persisted
    private EditText bodyEditText;

    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_email, container);
    }

    @Override
    public void created() {
        sendButton = (Button) getView().findViewById(R.id.send);
        sendButton.setOnClickListener(this);

        subjectEditText = (EditText) getView().findViewById(R.id.subject);
        bodyEditText = (EditText) getView().findViewById(R.id.body);

        subjectEditText.setText("With Straw");
        bodyEditText.setText(R.string.bodySample);
    }

    @Override
    public void onFocus() {

    }

    @Override
    public void onFocusLost() {

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
        sendButton.setText("Wait...");
        sendButton.setEnabled(false);
        subjectEditText.setEnabled(false);
        bodyEditText.setEnabled(false);
    }
}