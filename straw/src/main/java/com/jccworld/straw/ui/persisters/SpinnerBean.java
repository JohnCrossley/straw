package com.jccworld.straw.ui.persisters;

/**
 * Created by jcc on 19/11/15.
 */
public class SpinnerBean implements PersistedDataBean {
    final int selectedIdx;
    final boolean enabled;
    final int visibility;

    SpinnerBean(final int selectedIdx, final boolean enabled, int visibility) {
        this.selectedIdx = selectedIdx;
        this.enabled = enabled;
        this.visibility = visibility;
    }
}
