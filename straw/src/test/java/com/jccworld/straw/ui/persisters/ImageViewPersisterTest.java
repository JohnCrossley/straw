package com.jccworld.straw.ui.persisters;

import android.widget.ImageView;

import com.jccworld.straw.R;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by johncrossley on 22/04/16.
 */
public class ImageViewPersisterTest {
    private static final int A_VALID_RESOURCE_ID = android.R.drawable.btn_minus;
    private static final boolean A_VALID_ENABLED_STATE = true;
    private ImageViewPersister sut;

    @Mock
    private ImageView mockImageView;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new ImageViewPersister();
        when(mockImageView.getTag(R.id.image_view_tag_resource_id)).thenReturn(A_VALID_RESOURCE_ID);
    }

    @Test
    public void dehydrates() {
        // init
        when(mockImageView.isEnabled()).thenReturn(A_VALID_ENABLED_STATE);

        // run
        ImageViewBean result = sut.dehydrate(mockImageView);

        // verify
        assertEquals(A_VALID_RESOURCE_ID, result.resourceId);
        assertEquals(A_VALID_ENABLED_STATE, result.enabled);
    }

    @Test
    public void hydrates() {
        // init
        final PersistedDataBean persistedDataBean = new ImageViewBean(A_VALID_RESOURCE_ID, A_VALID_ENABLED_STATE);

        // run
        sut.hydrate(mockImageView, persistedDataBean);

        // verify
        verify(mockImageView).setImageResource(A_VALID_RESOURCE_ID);
        verify(mockImageView).setEnabled(A_VALID_ENABLED_STATE);
    }
}