package com.jccworld.straw.ui.persisters;

import android.view.View;
import android.widget.ProgressBar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by johncrossley on 27/04/16.
 */
public class ProgressBarPersisterTest {

    private static final boolean A_VALID_ENABLED_STATE = true;
    private static final int A_VALID_VISIBILITY = View.VISIBLE;

    @Mock
    private ProgressBar mockProgressBar;

    private ProgressBarPersister sut;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new ProgressBarPersister();
    }

    @Test
    public void dehydrates() {
        // init
        when(mockProgressBar.isEnabled()).thenReturn(A_VALID_ENABLED_STATE);
        when(mockProgressBar.getVisibility()).thenReturn(A_VALID_VISIBILITY);

        // run
        ProgressBarBean result = sut.dehydrate(mockProgressBar);

        // verify
        assertEquals(A_VALID_ENABLED_STATE, result.enabled);
        assertEquals(A_VALID_VISIBILITY, result.visibility);
    }

    @Test
    public void hydrates() {
        // init
        final PersistedDataBean persistedDataBean = new ProgressBarBean(A_VALID_ENABLED_STATE, A_VALID_VISIBILITY);

        // run
        sut.hydrate(mockProgressBar, persistedDataBean);

        // verify
        verify(mockProgressBar).setEnabled(A_VALID_ENABLED_STATE);
        verify(mockProgressBar).setVisibility(A_VALID_VISIBILITY);
    }
}