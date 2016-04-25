package com.jccworld.straw.ui.persisters;

import android.widget.ToggleButton;

import com.jccworld.straw.ui.vo.Persister;

/**
 * Created by jcc on 04/11/15.
 */
public class ToggleButtonPersister implements Persister<ToggleButton> {
    @Override
    public ToggleButtonBean dehydrate(final ToggleButton toggleButton) {
        ToggleButtonBean bean = new ToggleButtonBean(toggleButton.getTextOn().toString(), toggleButton.getTextOff().toString(),
                toggleButton.isChecked(), toggleButton.isEnabled());
        return bean;
    }

    @Override
    public void hydrate(final ToggleButton toggleButton, final PersistedDataBean payload) {
        ToggleButtonBean bean = (ToggleButtonBean) payload;
        toggleButton.setTextOn(bean.textOn);
        toggleButton.setTextOff(bean.textOff);
        toggleButton.setChecked(bean.checked);
        toggleButton.setEnabled(bean.enabled);
    }
}
