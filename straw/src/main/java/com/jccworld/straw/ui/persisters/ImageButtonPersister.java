package com.jccworld.straw.ui.persisters;

import android.widget.ImageButton;

import com.jccworld.straw.R;
import com.jccworld.straw.ui.vo.Persister;

/**
 * Created by jcc on 04/11/15.
 */
public class ImageButtonPersister implements Persister<ImageButton> {
    @Override
    public ImageButtonBean dehydrate(final ImageButton imageButton) {
        int resourceId = (Integer) imageButton.getTag(R.id.image_view_tag_resource_id);
        ImageButtonBean bean = new ImageButtonBean(resourceId, imageButton.isEnabled(), imageButton.getVisibility());
        return bean;
    }

    @Override
    public void hydrate(final ImageButton imageButton, final PersistedDataBean payload) {
        ImageButtonBean bean = (ImageButtonBean) payload;
        imageButton.setImageResource(bean.resourceId);
        imageButton.setEnabled(bean.enabled);
        imageButton.setVisibility(bean.visibility);
        imageButton.setTag(R.id.image_view_tag_resource_id, bean.resourceId);
    }
}
