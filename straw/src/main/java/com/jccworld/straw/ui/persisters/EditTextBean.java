package com.jccworld.straw.ui.persisters;

/**
 * Created by jcc on 19/11/15.
 */
public class EditTextBean implements PersistedDataBean {
    final String text;
    final boolean enabled;
    final int visibility;

    EditTextBean(final String text, final boolean enabled, final int visibility) {
        this.text = text;
        this.enabled = enabled;
        this.visibility = visibility;
    }
}
