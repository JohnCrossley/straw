package com.jccworld.straw.ui.persisters;

/**
 * Created by jcc on 19/11/15.
 */
public class ButtonBean implements PersistedDataBean {
    final String text;
    final boolean enabled;

    ButtonBean(final String text, final boolean enabled) {
        this.text = text;
        this.enabled = enabled;
    }
}
