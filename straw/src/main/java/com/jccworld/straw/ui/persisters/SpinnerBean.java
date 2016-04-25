package com.jccworld.straw.ui.persisters;

/**
 * Created by jcc on 19/11/15.
 */
public class SpinnerBean implements PersistedDataBean {
    final int selectedIdx;
    final boolean enabled;

    SpinnerBean(final int selectedIdx, final boolean enabled) {
        this.selectedIdx = selectedIdx;
        this.enabled = enabled;
    }
}
