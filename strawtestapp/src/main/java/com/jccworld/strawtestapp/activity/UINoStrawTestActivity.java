package com.jccworld.strawtestapp.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.jccworld.strawtestapp.R;

import java.util.Random;

public class UINoStrawTestActivity extends Activity implements View.OnClickListener {
    private static final String INITIAL_TEXT = "sample text";
    private static final String RUNTIME_CHANGE_TEXT = "new changed text";
    private static final String TEXT_ON = "one";
    private static final String TEXT_OFF = "zero";

    private static final int INITIAL_IMAGE_RES_ID = R.drawable.tick;
    private static final int RUNTIME_CHANGE_IMAGE_RES_ID = R.drawable.tick_after;

    private static final String KEY_VIEWS_ENABLED = "viewsEnabled";
    public static final String DISABLE_VIEWS = "Disable views";
    public static final String ENABLE_VIEWS = "Enable views";

    private boolean viewsEnabled;

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
    private ProgressBar progressBar;

    //experimentation buttons
    private Button modifyContentBtn;
    private Button enableDisableBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ui_test);

        setupTextView();
        setupImageView();
        setupEditText();
        setupButton();
        setupRadioGroup();
        setupSpinner();
        setupImageButton();
        setupAutoCompleteTextView();
        setupCheckBoxes();
        setupToggleButton();
        setupSwitch();
        setupProgressBar();

        setupControls();
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
        autoCompleteTextView.setText(RUNTIME_CHANGE_TEXT + " " + currentTimeMillis);
        checkBox1.setText(RUNTIME_CHANGE_TEXT + " " + currentTimeMillis + " ... 1");
        checkBox2.setText(RUNTIME_CHANGE_TEXT + " " + currentTimeMillis + " ... 2");
        checkBox3.setText(RUNTIME_CHANGE_TEXT + " " + currentTimeMillis + " ... 3");
        checkBox1.setChecked(new Random().nextBoolean());
        checkBox2.setChecked(new Random().nextBoolean());
        checkBox3.setChecked(new Random().nextBoolean());
        toggleButton.setChecked(new Random().nextBoolean());
        toggleButton.setText(RUNTIME_CHANGE_TEXT + " " + currentTimeMillis);
        switchButton.setChecked(new Random().nextBoolean());
        progressBar.setProgress((int)(Math.random() * 100));
    }

    private void changeEnabledViewStates() {
        viewsEnabled = !viewsEnabled;

        textView.setEnabled(viewsEnabled);
        imageView.setEnabled(viewsEnabled);
        editText.setEnabled(viewsEnabled);
        button.setEnabled(viewsEnabled);
        for(int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(viewsEnabled);
        }
        spinner.setEnabled(viewsEnabled);
        imageButton.setEnabled(viewsEnabled);
        autoCompleteTextView.setEnabled(viewsEnabled);
        checkBox1.setEnabled(viewsEnabled);
        checkBox2.setEnabled(viewsEnabled);
        checkBox3.setEnabled(viewsEnabled);
        toggleButton.setEnabled(viewsEnabled);
        switchButton.setEnabled(viewsEnabled);
        progressBar.setEnabled(viewsEnabled);

        updateEnableDisableLabel();
    }

    private void setupTextView() {
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(INITIAL_TEXT);
    }

    private void setupImageView() {
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(INITIAL_IMAGE_RES_ID);
        imageView.setTag(R.id.image_view_tag_resource_id, INITIAL_IMAGE_RES_ID);
    }

    private void setupEditText() {
        editText = (EditText) findViewById(R.id.editText);
        editText.setText(INITIAL_TEXT);
    }

    private void setupButton() {
        button = (Button) findViewById(R.id.button);
        button.setText(INITIAL_TEXT);
    }

    private void setupRadioGroup() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton1.setText(INITIAL_TEXT + " ... 1");
        radioButton2.setText(INITIAL_TEXT + " ... 2");
        radioButton3.setText(INITIAL_TEXT + " ... 3");
    }

    private void setupSpinner() {
        spinner = (Spinner) findViewById(R.id.spinner);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0, true);
    }

    private void setupImageButton() {
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setImageResource(INITIAL_IMAGE_RES_ID);
        imageButton.setTag(R.id.image_view_tag_resource_id, INITIAL_IMAGE_RES_ID);
    }

    private void setupAutoCompleteTextView() {
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setText(INITIAL_TEXT);
    }

    private void setupCheckBoxes() {
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox1.setText(INITIAL_TEXT + " ... 1");
        checkBox2.setText(INITIAL_TEXT + " ... 2");
        checkBox3.setText(INITIAL_TEXT + " ... 3");
        checkBox2.setChecked(true);
    }

    private void setupToggleButton() {
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButton.setChecked(true);
        toggleButton.setTextOn(TEXT_ON);
        toggleButton.setTextOff(TEXT_OFF);
    }

    private void setupSwitch() {
        switchButton = (Switch) findViewById(R.id.switchButton);
        switchButton.setTextOn(TEXT_ON);
        switchButton.setTextOff(TEXT_OFF);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            switchButton.setShowText(true);//wtf!! hacky android
        }
        switchButton.setChecked(true);
    }

    private void setupProgressBar() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(50);
    }

    private void setupControls() {
        modifyContentBtn = (Button) findViewById(R.id.modifyContent);
        modifyContentBtn.setOnClickListener(this);

        enableDisableBtn = (Button) findViewById(R.id.enable_disable);
        enableDisableBtn.setOnClickListener(this);
    }

    private void updateEnableDisableLabel() {
        if (viewsEnabled) {
            enableDisableBtn.setText(DISABLE_VIEWS);
        } else {
            enableDisableBtn.setText(ENABLE_VIEWS);
        }
    }
}
