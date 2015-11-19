package com.jccworld.straw.ui.persisters;

import android.os.Build;
import android.widget.Switch;

import com.jccworld.straw.ui.vo.Persister;

/**
 * Created by jcc on 04/11/15.
 */
public class SwitchPersister implements Persister<Switch> {
    @Override
    public Object dehydrate(final Switch switchButton) {
        boolean showText = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            showText = switchButton.getShowText();
        }

        SwitchBean bean = new SwitchBean(switchButton.getTextOn().toString(), switchButton.getTextOff().toString(),
                showText, switchButton.isChecked(), switchButton.isEnabled());
        return bean;
    }

    @Override
    public void hydrate(final Switch switchButton, final Object payload) {
        SwitchBean bean = (SwitchBean) payload;
        switchButton.setTextOn(bean.textOn);
        switchButton.setTextOff(bean.textOff);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            switchButton.setShowText(bean.showText);
        }

        switchButton.setChecked(bean.checked);
        switchButton.setEnabled(bean.enabled);
    }
}
