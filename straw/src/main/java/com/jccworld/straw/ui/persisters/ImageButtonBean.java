package com.jccworld.straw.ui.persisters;

/**
 * Created by jcc on 19/11/15.
 */
public class ImageButtonBean implements PersistedDataBean {
    final int resourceId;
    final boolean enabled;
    final int visibility;

    ImageButtonBean(final int resourceId, final boolean enabled, int visibility) {
        this.resourceId = resourceId;
        this.enabled = enabled;
        this.visibility = visibility;
    }
}
