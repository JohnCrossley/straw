package com.jccworld.straw;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jccworld.straw.ui.persisters.AutoCompleteTextViewPersister;
import com.jccworld.straw.ui.persisters.ButtonPersister;
import com.jccworld.straw.ui.persisters.CheckBoxPersister;
import com.jccworld.straw.ui.persisters.CompoundButtonPersister;
import com.jccworld.straw.ui.persisters.EditTextPersister;
import com.jccworld.straw.ui.persisters.ImageButtonPersister;
import com.jccworld.straw.ui.persisters.RadioButtonPersister;
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
    private final CompoundButtonPersister compoundButtonPersister;
    private final EditTextPersister editTextPersister;
    private final ImageButtonPersister imageButtonPersister;
    private final RadioButtonPersister radioButtonPersister;
    private final RadioGroupPersister radioGroupPersister;
    private final SpinnerPersister spinnerPersister;
    private final SwitchPersister switchPersister;
    private final TextViewPersister textViewPersister;
    private final ToggleButtonPersister toggleButtonPersister;

    public UIPersister() {
        autoCompleteTextViewPersister = new AutoCompleteTextViewPersister();
        buttonPersister = new ButtonPersister();
        checkBoxPersister = new CheckBoxPersister();
        compoundButtonPersister = new CompoundButtonPersister();
        editTextPersister = new EditTextPersister();
        imageButtonPersister = new ImageButtonPersister();
        radioButtonPersister = new RadioButtonPersister();
        radioGroupPersister = new RadioGroupPersister();
        spinnerPersister = new SpinnerPersister();
        switchPersister = new SwitchPersister();
        textViewPersister = new TextViewPersister();
        toggleButtonPersister = new ToggleButtonPersister();
    }

    public void save(final String key, final TextView textView) {
        map.put(key, textViewPersister.dehydrate(textView));
    }

    public void load(final String key, final TextView textView) {
        textViewPersister.hydrate(textView, map.get(key));
    }

    public void save(final String key, final EditText editText) {
        map.put(key, editTextPersister.dehydrate(editText));
    }

    public void load(final String key, final EditText editText) {
        editTextPersister.hydrate(editText, map.get(key));
    }

    public void save(final String key, final Button button) {
        map.put(key, buttonPersister.dehydrate(button));
    }

    public void load(final String key, final Button button) {
        buttonPersister.hydrate(button, map.get(key));
    }
}
