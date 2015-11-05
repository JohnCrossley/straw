package com.jccworld.straw.ui.persisters;

import android.widget.TextView;

import com.jccworld.straw.ui.vo.Persister;

/**
 * Created by jcc on 04/11/15.
 */
public class TextViewPersister implements Persister<TextView> {

    private class SerialisedForm {
        final String text;
        final boolean enabled;

        SerialisedForm(final String textV, final boolean enabledV) {
            text = textV;
            enabled = enabledV;
        }
    }

    @Override
    public Object dehydrate(final TextView textView) {
        SerialisedForm serialisedForm = new SerialisedForm(textView.getText().toString(), textView.isEnabled());
        return serialisedForm;
    }

    @Override
    public void hydrate(final TextView textView, final Object payload) {
        SerialisedForm serialisedForm = (SerialisedForm) payload;
        textView.setText(serialisedForm.text);
        textView.setEnabled(serialisedForm.enabled);
    }
}