package com.jccworld.straw.ui.persisters;

/**
 * Created by jcc on 19/11/15.
 */
public class CheckBoxBean implements PersistedDataBean {
    final String text;
    final boolean enabled;
    final int visibility;

    CheckBoxBean(final String text, final boolean enabled, final int visibility) {
        this.text = text;
        this.enabled = enabled;
        this.visibility = visibility;
    }
}
