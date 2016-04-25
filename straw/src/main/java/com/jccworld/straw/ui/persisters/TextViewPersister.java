package com.jccworld.straw.ui.persisters;

import android.widget.TextView;

import com.jccworld.straw.ui.vo.Persister;

/**
 * Created by jcc on 04/11/15.
 */
public class TextViewPersister implements Persister<TextView> {
    @Override
    public TextViewBean dehydrate(final TextView textView) {
        TextViewBean bean = new TextViewBean(textView.getText().toString(), textView.isEnabled());
        return bean;
    }

    @Override
    public void hydrate(final TextView textView, final PersistedDataBean payload) {
        TextViewBean bean = (TextViewBean) payload;
        textView.setText(bean.text);
        textView.setEnabled(bean.enabled);
    }
}