package com.jccworld.straw.ui.persisters;

/**
 * Created by jcc on 19/11/15.
 */
public class SwitchBean implements PersistedDataBean {
    final String textOn;
    final String textOff;
    final boolean showText;
    final boolean checked;
    final boolean enabled;
    final int visibility;

    SwitchBean(final String textOn, final String textOff, final boolean showText,
               final boolean checked, final boolean enabled, int visibility) {
        this.textOn = textOn;
        this.textOff = textOff;
        this.showText = showText;
        this.checked = checked;
        this.enabled = enabled;
        this.visibility = visibility;
    }
}
