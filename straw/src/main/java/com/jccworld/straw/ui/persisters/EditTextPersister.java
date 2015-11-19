package com.jccworld.straw.ui.persisters;

import android.widget.EditText;

import com.jccworld.straw.ui.vo.Persister;

/**
 * Created by jcc on 04/11/15.
 */
public class EditTextPersister implements Persister<EditText> {
    @Override
    public Object dehydrate(final EditText editText) {
        EditTextBean bean = new EditTextBean(editText.getText().toString(), editText.isEnabled());
        return bean;
    }

    @Override
    public void hydrate(final EditText editText, final Object payload) {
        EditTextBean bean = (EditTextBean) payload;
        editText.setText(bean.text);
        editText.setEnabled(bean.enabled);
    }
}