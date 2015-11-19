package com.jccworld.straw.ui.persisters;

import android.widget.AutoCompleteTextView;

import com.jccworld.straw.ui.vo.Persister;

/**
 * Created by jcc on 04/11/15.
 */
public class AutoCompleteTextViewPersister implements Persister<AutoCompleteTextView> {
    @Override
    public Object dehydrate(final AutoCompleteTextView autoCompleteTextView) {
        AutoCompleteTextViewBean bean = new AutoCompleteTextViewBean(autoCompleteTextView.getText().toString(), autoCompleteTextView.isEnabled());
        return bean;
    }

    @Override
    public void hydrate(final AutoCompleteTextView autoCompleteTextView, final Object payload) {
        AutoCompleteTextViewBean bean = (AutoCompleteTextViewBean) payload;
        autoCompleteTextView.setText(bean.text);
        autoCompleteTextView.setEnabled(bean.enabled);
    }
}
