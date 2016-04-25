package com.jccworld.straw.ui.persisters;

/**
 * Created by jcc on 19/11/15.
 */
public class ImageViewBean implements PersistedDataBean {
    final int resourceId;
    final boolean enabled;

    ImageViewBean(final int resourceId, final boolean enabled) {
        this.resourceId = resourceId;
        this.enabled = enabled;
    }
}
