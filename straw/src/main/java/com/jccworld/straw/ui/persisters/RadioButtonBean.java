package com.jccworld.straw.ui.persisters;

/**
 * Created by jcc on 19/11/15.
 */
public class RadioButtonBean {
    final String text;
    final boolean enabled;

    public RadioButtonBean(final String text, final boolean enabled) {
        this.text = text;
        this.enabled = enabled;
    }

    public RadioButtonBean(final boolean enabled) {
        this.text = null;
        this.enabled = enabled;
    }
}
