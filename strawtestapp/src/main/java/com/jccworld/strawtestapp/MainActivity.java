package com.jccworld.strawtestapp;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jccworld.straw.KeyValueCache;
import com.jccworld.straw.UIPersister;
import com.jccworld.strawtestapp.debug.VerboseActivity;

public class MainActivity extends VerboseActivity implements View.OnClickListener {

    private static final String KEY_EDIT_TEXT = "editText";
    private static final String KEY_TEXT_VIEW = "textView";
    private static final String KEY_BUTTON = "button";

    private EditText editText;
    private String editTextText = "edit text sample text";

    private TextView textView;
    private String textViewText = "text view sample text";

    private Button button;
    private String buttonText = "button sample text";

    //experimentation buttons
    private Button modifyContentBtn;
    private Button enableDisableBtn;

    @Override
    public void created() {
        //do bindings and shit...
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(this);

        editText = (EditText) findViewById(R.id.editText);
        editText.setOnClickListener(this);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        modifyContentBtn = (Button) findViewById(R.id.modifyContent);
        modifyContentBtn.setOnClickListener(this);

        enableDisableBtn = (Button) findViewById(R.id.enable_disable);
        enableDisableBtn.setOnClickListener(this);
    }

    @Override
    public void onFocus() {
        //do analytics and stuff...
    }

    @Override
    public void onFocusLost() {
        //do analytics and stuff...
    }

    @Override
    public void onSave(final KeyValueCache keyValueCache, final UIPersister uiPersister) {
        //save ui state, variables and stuff...
        uiPersister.save(KEY_TEXT_VIEW, textView);
        uiPersister.save(KEY_EDIT_TEXT, editText);
        uiPersister.save(KEY_BUTTON, button);

    }

    @Override
    public void onLoad(final boolean firstRun, final KeyValueCache keyValueCache, final UIPersister uiPersister) {
        //load ui state, variables and stuff...

        if (firstRun) {
            textView.setText(textViewText);
            editText.setText(editTextText);
            button.setText(buttonText);

            return;
        }

        uiPersister.load(KEY_TEXT_VIEW, textView);
        uiPersister.load(KEY_EDIT_TEXT, editText);
        uiPersister.load(KEY_BUTTON, button);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.modifyContent) {
            long currentTimeMillis = System.currentTimeMillis();

            textView.setText(textViewText + " " + currentTimeMillis);
            editText.setText(editTextText + " " + currentTimeMillis);
            button.setText(buttonText + " " + currentTimeMillis);

        } else if (v.getId() == R.id.enable_disable) {
            textView.setEnabled(!textView.isEnabled());
            editText.setEnabled(!editText.isEnabled());
            button.setEnabled(!button.isEnabled());
        }
    }
}
