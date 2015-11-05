package com.jccworld.straw.ui.persisters;

import android.widget.EditText;

import com.jccworld.straw.ui.vo.Persister;

/**
 * Created by jcc on 04/11/15.
 */
public class EditTextPersister implements Persister<EditText> {

    private class SerialisedForm {
        final String text;
        final boolean enabled;

        SerialisedForm(final String textV, final boolean enabledV) {
            text = textV;
            enabled = enabledV;
        }
    }

    @Override
    public Object dehydrate(final EditText editText) {
        SerialisedForm serialisedForm = new SerialisedForm(editText.getText().toString(), editText.isEnabled());
        return serialisedForm;
    }

    @Override
    public void hydrate(final EditText editText, final Object payload) {
        SerialisedForm serialisedForm = (SerialisedForm) payload;
        editText.setText(serialisedForm.text);
        editText.setEnabled(serialisedForm.enabled);
    }
}