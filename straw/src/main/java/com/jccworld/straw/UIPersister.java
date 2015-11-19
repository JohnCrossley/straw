package com.jccworld.straw;

import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.jccworld.straw.ui.persisters.AutoCompleteTextViewPersister;
import com.jccworld.straw.ui.persisters.ButtonPersister;
import com.jccworld.straw.ui.persisters.CheckBoxPersister;
import com.jccworld.straw.ui.persisters.EditTextPersister;
import com.jccworld.straw.ui.persisters.ImageButtonPersister;
import com.jccworld.straw.ui.persisters.ImageViewPersister;
import com.jccworld.straw.ui.persisters.RadioGroupPersister;
import com.jccworld.straw.ui.persisters.SpinnerPersister;
import com.jccworld.straw.ui.persisters.SwitchPersister;
import com.jccworld.straw.ui.persisters.TextViewPersister;
import com.jccworld.straw.ui.persisters.ToggleButtonPersister;

import java.util.HashMap;
import java.util.Map;

/**
 * Serialise the data from UI elements (i.e. value, enabled, etc) so a new instance can be restored
 * in the same state
 *
 * Created by jcc on 26/10/15.
 */
public class UIPersister {
    private Map<String, Object> map = new HashMap<>();

    private final AutoCompleteTextViewPersister autoCompleteTextViewPersister;
    private final ButtonPersister buttonPersister;
    private final CheckBoxPersister checkBoxPersister;
    private final EditTextPersister editTextPersister;
    private final ImageButtonPersister imageButtonPersister;
    private final ImageViewPersister imageViewPersister;
    private final RadioGroupPersister radioGroupPersister;
    private final SpinnerPersister spinnerPersister;
    private final SwitchPersister switchPersister;
    private final TextViewPersister textViewPersister;
    private final ToggleButtonPersister toggleButtonPersister;

    public UIPersister() {
        autoCompleteTextViewPersister = new AutoCompleteTextViewPersister();
        buttonPersister = new ButtonPersister();
        checkBoxPersister = new CheckBoxPersister();
        editTextPersister = new EditTextPersister();
        imageButtonPersister = new ImageButtonPersister();
        imageViewPersister = new ImageViewPersister();
        radioGroupPersister = new RadioGroupPersister();
        spinnerPersister = new SpinnerPersister();
        switchPersister = new SwitchPersister();
        textViewPersister = new TextViewPersister();
        toggleButtonPersister = new ToggleButtonPersister();
    }

    public void save(final String key, final AutoCompleteTextView autoCompleteTextView) {
        map.put(key, autoCompleteTextViewPersister.dehydrate(autoCompleteTextView));
    }

    public void load(final String key, final AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextViewPersister.hydrate(autoCompleteTextView, map.get(key));
    }


    public void save(final String key, final Button button) {
        map.put(key, buttonPersister.dehydrate(button));
    }

    public void load(final String key, final Button button) {
        buttonPersister.hydrate(button, map.get(key));
    }


    public void save(final String key, final CheckBox checkBox) {
        map.put(key, checkBoxPersister.dehydrate(checkBox));
    }

    public void load(final String key, final CheckBox checkBox) {
        checkBoxPersister.hydrate(checkBox, map.get(key));
    }


    public void save(final String key, final EditText editText) {
        map.put(key, editTextPersister.dehydrate(editText));
    }

    public void load(final String key, final EditText editText) {
        editTextPersister.hydrate(editText, map.get(key));
    }


    public void save(final String key, final ImageButton imageButton) {
        map.put(key, imageButtonPersister.dehydrate(imageButton));
    }

    public void load(final String key, final ImageButton imageButton) {
        imageButtonPersister.hydrate(imageButton, map.get(key));
    }


    public void save(final String key, final ImageView imageView) {
        map.put(key, imageViewPersister.dehydrate(imageView));
    }

    public void load(final String key, final ImageView imageView) {
        imageViewPersister.hydrate(imageView, map.get(key));
    }


    public void save(final String key, final RadioGroup radioGroup) {
        map.put(key, radioGroupPersister.dehydrate(radioGroup));
    }

    public void load(final String key, final RadioGroup radioGroup) {
        radioGroupPersister.hydrate(radioGroup, map.get(key));
    }


    public void save(final String key, final Spinner spinner) {
        map.put(key, spinnerPersister.dehydrate(spinner));
    }

    public void load(final String key, final Spinner spinner) {
        spinnerPersister.hydrate(spinner, map.get(key));
    }


    public void save(final String key, final Switch switchButton) {
        map.put(key, switchPersister.dehydrate(switchButton));
    }

    public void load(final String key, final Switch switchButton) {
        switchPersister.hydrate(switchButton, map.get(key));
    }


    public void save(final String key, final TextView textView) {
        map.put(key, textViewPersister.dehydrate(textView));
    }

    public void load(final String key, final TextView textView) {
        textViewPersister.hydrate(textView, map.get(key));
    }


    public void save(final String key, final ToggleButton toggleButton) {
        map.put(key, toggleButtonPersister.dehydrate(toggleButton));
    }

    public void load(final String key, final ToggleButton toggleButton) {
        toggleButtonPersister.hydrate(toggleButton, map.get(key));
    }
}
