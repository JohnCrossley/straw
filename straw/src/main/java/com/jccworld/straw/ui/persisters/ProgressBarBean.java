package com.jccworld.straw.ui.persisters;

/**
 * Created by jcc on 19/11/15.
 */
public class ProgressBarBean implements PersistedDataBean {
    final boolean enabled;
    final int visibility;

    ProgressBarBean(final boolean enabled, int visibility) {
        this.enabled = enabled;
        this.visibility = visibility;
    }
}
