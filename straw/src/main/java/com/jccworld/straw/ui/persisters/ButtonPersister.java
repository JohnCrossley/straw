package com.jccworld.straw.ui.persisters;

import android.widget.Button;

import com.jccworld.straw.ui.vo.Persister;

/**
 * Created by jcc on 04/11/15.
 */
public class ButtonPersister implements Persister<Button> {

    private class SerialisedForm {
        final String text;
        final boolean enabled;

        SerialisedForm(final String textV, final boolean enabledV) {
            text = textV;
            enabled = enabledV;
        }
    }

    @Override
    public Object dehydrate(final Button button) {
        SerialisedForm serialisedForm = new SerialisedForm(button.getText().toString(), button.isEnabled());
        return serialisedForm;
    }

    @Override
    public void hydrate(final Button button, final Object payload) {
        SerialisedForm serialisedForm = (SerialisedForm) payload;
        button.setText(serialisedForm.text);
        button.setEnabled(serialisedForm.enabled);
    }
}
