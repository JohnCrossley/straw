package com.jccworld.straw.ui.persisters;

import android.widget.CheckBox;

import com.jccworld.straw.ui.vo.Persister;

/**
 * Handles a single checkbox
 *
 * Created by jcc on 04/11/15.
 */
public class CheckBoxPersister implements Persister<CheckBox> {
    @Override
    public CheckBoxBean dehydrate(final CheckBox checkBox) {
        CheckBoxBean bean = new CheckBoxBean(checkBox.getText().toString(), checkBox.isEnabled());
        return bean;
    }

    @Override
    public void hydrate(final CheckBox checkBox, final PersistedDataBean payload) {
        CheckBoxBean bean = (CheckBoxBean) payload;
        checkBox.setText(bean.text);
        checkBox.setEnabled(bean.enabled);
    }
}
