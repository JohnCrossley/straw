package com.jccworld.strawtestapp;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.jccworld.straw.KeyValueCache;
import com.jccworld.straw.UIPersister;
import com.jccworld.strawtestapp.debug.VerboseActivity;

import java.util.Random;

public class MainActivity extends VerboseActivity implements View.OnClickListener {
    private static final String INITIAL_TEXT = "sample text";
    private static final String RUNTIME_CHANGE_TEXT = "new changed text";

    private static final int INITIAL_IMAGE_RES_ID = R.drawable.tick;
    private static final int RUNTIME_CHANGE_IMAGE_RES_ID = R.drawable.tick_after;

    private static final String KEY_TEXT_VIEW = "textView";
    private static final String KEY_IMAGE_VIEW = "imageView";
    private static final String KEY_EDIT_TEXT = "editText";
    private static final String KEY_BUTTON = "button";
    private static final String KEY_RADIO_GROUP = "radioGroup";
    private static final String KEY_SPINNER = "spinner";
    private static final String KEY_IMAGE_BUTTON = "imageButton";
    private static final String KEY_AUTO_COMPLETE_TEXT_VIEW = "autoCompleteTextView";
    private static final String KEY_CHECK_BOX_1 = "checkBox1";
    private static final String KEY_CHECK_BOX_2 = "checkBox2";
    private static final String KEY_CHECK_BOX_3 = "checkBox3";
    private static final String KEY_TOGGLE_BUTTON = "toggleButton";
    private static final String KEY_SWITCH = "switch";

    private TextView textView;
    private ImageView imageView;
    private EditText editText;
    private Button button;

    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;

    private Spinner spinner;
    private ImageButton imageButton;
    private AutoCompleteTextView autoCompleteTextView;

    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;

    private ToggleButton toggleButton;
    private Switch switchButton;

    //experimentation buttons
    private Button modifyContentBtn;
    private Button enableDisableBtn;

    @Override
    public void created() {
        //do bindings and stuff...
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        imageView = (ImageView) findViewById(R.id.imageView);

        editText = (EditText) findViewById(R.id.editText);

        button = (Button) findViewById(R.id.button);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);

        spinner = (Spinner) findViewById(R.id.spinner);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        imageButton = (ImageButton) findViewById(R.id.imageButton);

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);

        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        switchButton = (Switch) findViewById(R.id.switchButton);

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
        uiPersister.save(KEY_IMAGE_VIEW, imageView);
        uiPersister.save(KEY_EDIT_TEXT, editText);
        uiPersister.save(KEY_BUTTON, button);
        uiPersister.save(KEY_RADIO_GROUP, radioGroup);
        uiPersister.save(KEY_SPINNER, spinner);
        uiPersister.save(KEY_IMAGE_BUTTON, imageButton);
        uiPersister.save(KEY_AUTO_COMPLETE_TEXT_VIEW, autoCompleteTextView);
        uiPersister.save(KEY_CHECK_BOX_1, checkBox1);
        uiPersister.save(KEY_CHECK_BOX_2, checkBox2);
        uiPersister.save(KEY_CHECK_BOX_3, checkBox3);
        uiPersister.save(KEY_TOGGLE_BUTTON, toggleButton);
        uiPersister.save(KEY_SWITCH, switchButton);
    }

    @Override
    public void onLoad(final boolean firstRun, final KeyValueCache keyValueCache, final UIPersister uiPersister) {
        //load ui state, variables and stuff...

        if (firstRun) {
            textView.setText(INITIAL_TEXT);
            imageView.setImageResource(INITIAL_IMAGE_RES_ID);
            imageView.setTag(R.id.image_view_tag_resource_id, INITIAL_IMAGE_RES_ID);
            editText.setText(INITIAL_TEXT);
            button.setText(INITIAL_TEXT);
            radioButton1.setText(INITIAL_TEXT + " ... 1");
            radioButton2.setText(INITIAL_TEXT + " ... 2");
            radioButton3.setText(INITIAL_TEXT + " ... 3");
            spinner.setSelection(0, true);
            imageButton.setImageResource(INITIAL_IMAGE_RES_ID);
            imageButton.setTag(R.id.image_view_tag_resource_id, INITIAL_IMAGE_RES_ID);
            autoCompleteTextView.setText(INITIAL_TEXT);
            checkBox1.setText(INITIAL_TEXT + " ... 1");
            checkBox2.setText(INITIAL_TEXT + " ... 2");
            checkBox3.setText(INITIAL_TEXT + " ... 3");
            checkBox2.setChecked(true);
            toggleButton.setChecked(true);

            switchButton.setTextOn("onnzzzz");
            switchButton.setTextOff("offfff");
            switchButton.setShowText(true);//wtf hacky android
            switchButton.setChecked(true);

            toggleButton.setTextOn("onnnnzzz");
            toggleButton.setTextOff("offfzzzz");


            return;
        }

        uiPersister.load(KEY_TEXT_VIEW, textView);
        uiPersister.load(KEY_IMAGE_VIEW, imageView);
        uiPersister.load(KEY_EDIT_TEXT, editText);
        uiPersister.load(KEY_BUTTON, button);
        uiPersister.load(KEY_RADIO_GROUP, radioGroup);
        uiPersister.load(KEY_SPINNER, spinner);
        uiPersister.load(KEY_IMAGE_BUTTON, imageButton);
        uiPersister.load(KEY_AUTO_COMPLETE_TEXT_VIEW, autoCompleteTextView);
        uiPersister.load(KEY_CHECK_BOX_1, checkBox1);
        uiPersister.load(KEY_CHECK_BOX_2, checkBox2);
        uiPersister.load(KEY_CHECK_BOX_3, checkBox3);
        uiPersister.load(KEY_TOGGLE_BUTTON, toggleButton);
        uiPersister.load(KEY_SWITCH, switchButton);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.modifyContent) {
            modifyContent(System.currentTimeMillis());
        } else if (v.getId() == R.id.enable_disable) {
            changeEnabledViewStates();
        }
    }

    private void modifyContent(final long currentTimeMillis) {
        textView.setText(RUNTIME_CHANGE_TEXT + " " + currentTimeMillis);
        imageView.setImageResource(RUNTIME_CHANGE_IMAGE_RES_ID);
        imageView.setTag(R.id.image_view_tag_resource_id, RUNTIME_CHANGE_IMAGE_RES_ID);
        editText.setText(RUNTIME_CHANGE_TEXT + " " + currentTimeMillis);
        button.setText(RUNTIME_CHANGE_TEXT + " " + currentTimeMillis);
        radioButton1.setText(RUNTIME_CHANGE_TEXT + " " + currentTimeMillis + " ... 1");
        radioButton2.setText(RUNTIME_CHANGE_TEXT + " " + currentTimeMillis + " ... 2");
        radioButton3.setText(RUNTIME_CHANGE_TEXT + " " + currentTimeMillis + " ... 3");
        spinner.setSelection(new Random().nextInt(spinner.getCount()), true);
        imageButton.setImageResource(RUNTIME_CHANGE_IMAGE_RES_ID);
        imageButton.setTag(R.id.image_view_tag_resource_id, RUNTIME_CHANGE_IMAGE_RES_ID);
        autoCompleteTextView.setText(RUNTIME_CHANGE_TEXT+ " " + currentTimeMillis);
        checkBox1.setText(RUNTIME_CHANGE_TEXT + " " + currentTimeMillis + " ... 1");
        checkBox2.setText(RUNTIME_CHANGE_TEXT + " " + currentTimeMillis + " ... 2");
        checkBox3.setText(RUNTIME_CHANGE_TEXT + " " + currentTimeMillis + " ... 3");
        checkBox1.setChecked(new Random().nextBoolean());
        checkBox2.setChecked(new Random().nextBoolean());
        checkBox3.setChecked(new Random().nextBoolean());
        toggleButton.setChecked(new Random().nextBoolean());
        toggleButton.setText(RUNTIME_CHANGE_TEXT + " " + currentTimeMillis);
        switchButton.setChecked(new Random().nextBoolean());

    }

    private void changeEnabledViewStates() {
        textView.setEnabled(!textView.isEnabled());
        imageView.setEnabled(!imageView.isEnabled());
        editText.setEnabled(!editText.isEnabled());
        button.setEnabled(!button.isEnabled());
        for(int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(!radioGroup.getChildAt(i).isEnabled());
        }
        spinner.setEnabled(!spinner.isEnabled());
        imageButton.setEnabled(!imageButton.isEnabled());
        autoCompleteTextView.setEnabled(!autoCompleteTextView.isEnabled());
        checkBox1.setEnabled(!checkBox1.isEnabled());
        checkBox2.setEnabled(!checkBox2.isEnabled());
        checkBox3.setEnabled(!checkBox3.isEnabled());
        toggleButton.setEnabled(!toggleButton.isEnabled());
        switchButton.setEnabled(!switchButton.isEnabled());
    }
}
