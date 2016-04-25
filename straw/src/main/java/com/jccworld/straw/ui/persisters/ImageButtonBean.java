package com.jccworld.straw.ui.persisters;

/**
 * Created by jcc on 19/11/15.
 */
public class ImageButtonBean implements PersistedDataBean {
    final int resourceId;
    final boolean enabled;

    ImageButtonBean(final int resourceId, final boolean enabled) {
        this.resourceId = resourceId;
        this.enabled = enabled;
    }
}
