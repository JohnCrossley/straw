package com.jccworld.straw.ui.persisters;

/**
 * Created by jcc on 19/11/15.
 */
public class RadioButtonBean implements PersistedDataBean {
    final String text;
    final boolean enabled;
    final int visibility;

    public RadioButtonBean(final String text, final boolean enabled, final int visibility) {
        this.text = text;
        this.enabled = enabled;
        this.visibility = visibility;
    }

    public RadioButtonBean(final boolean enabled, final int visibility) {
        this.text = null;
        this.enabled = enabled;
        this.visibility = visibility;
    }
}
