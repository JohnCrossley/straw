package com.jccworld.straw.ui.persisters;

import android.widget.ImageView;

import com.jccworld.straw.R;
import com.jccworld.straw.ui.vo.Persister;

/**
 * Created by jcc on 04/11/15.
 */
public class ImageViewPersister implements Persister<ImageView> {
    @Override
    public Object dehydrate(final ImageView imageView) {
        int resourceId = (Integer) imageView.getTag(R.id.image_view_tag_resource_id);
        ImageViewBean bean = new ImageViewBean(resourceId, imageView.isEnabled());
        return bean;
    }

    @Override
    public void hydrate(final ImageView imageView, final Object payload) {
        ImageViewBean bean = (ImageViewBean) payload;
        imageView.setImageResource(bean.resourceId);
        imageView.setEnabled(bean.enabled);
        imageView.setTag(R.id.image_view_tag_resource_id, bean.resourceId);
    }
}
