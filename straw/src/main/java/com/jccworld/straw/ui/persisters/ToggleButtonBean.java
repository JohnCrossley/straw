package com.jccworld.straw.ui.persisters;

/**
 * Created by jcc on 19/11/15.
 */
public class ToggleButtonBean implements PersistedDataBean {
    final String textOn;
    final String textOff;
    final boolean checked;
    final boolean enabled;

    ToggleButtonBean(final String textOn, final String textOff,
                     final boolean checked, final boolean enabled) {
        this.textOn = textOn;
        this.textOff = textOff;
        this.checked = checked;
        this.enabled = enabled;
    }
}
