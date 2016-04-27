package com.jccworld.straw.ui.persisters;

/**
 * Created by jcc on 19/11/15.
 */
public class ImageViewBean implements PersistedDataBean {
    final int resourceId;
    final boolean enabled;
    final int visibility;


    ImageViewBean(final int resourceId, final boolean enabled, final int visibility) {
        this.resourceId = resourceId;
        this.enabled = enabled;
        this.visibility = visibility;
    }
}
