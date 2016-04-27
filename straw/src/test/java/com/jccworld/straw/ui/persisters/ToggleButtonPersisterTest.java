package com.jccworld.straw.ui.persisters;

import android.view.View;
import android.widget.ToggleButton;

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
public class ToggleButtonPersisterTest {
    private static final String A_VALID_TEXT_ON = "valid text on";
    private static final String A_VALID_TEXT_OFF = "valid text off";
    private static final boolean A_VALID_IS_CHECKED = true;
    private static final boolean A_VALID_IS_ENABLED = true;
    private static final int A_VALID_VISIBILITY = View.VISIBLE;

    private ToggleButtonPersister sut;

    @Mock
    private ToggleButton mockToggleButton;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new ToggleButtonPersister();

        when(mockToggleButton.getTextOn()).thenReturn(A_VALID_TEXT_ON);
        when(mockToggleButton.getTextOff()).thenReturn(A_VALID_TEXT_OFF);
        when(mockToggleButton.isChecked()).thenReturn(A_VALID_IS_CHECKED);
        when(mockToggleButton.isEnabled()).thenReturn(A_VALID_IS_ENABLED);
        when(mockToggleButton.getVisibility()).thenReturn(A_VALID_VISIBILITY);
    }

    @Test
    public void dehydrate() {
        // run
        final ToggleButtonBean result = sut.dehydrate(mockToggleButton);

        // verify
        assertEquals(A_VALID_TEXT_ON, result.textOn);
        assertEquals(A_VALID_TEXT_OFF, result.textOff);
        assertEquals(A_VALID_IS_CHECKED, result.checked);
        assertEquals(A_VALID_IS_ENABLED, result.enabled);
        assertEquals(A_VALID_VISIBILITY, result.visibility);
    }

    @Test
    public void hydratePreLollipop() {
        // init
        final PersistedDataBean payload = new ToggleButtonBean(A_VALID_TEXT_ON, A_VALID_TEXT_OFF,
                A_VALID_IS_CHECKED, A_VALID_IS_ENABLED, A_VALID_VISIBILITY);

        // run
        sut.hydrate(mockToggleButton, payload);

        // verify
        verify(mockToggleButton).setTextOn(A_VALID_TEXT_ON);
        verify(mockToggleButton).setTextOff(A_VALID_TEXT_OFF);
        verify(mockToggleButton).setTextOn(A_VALID_TEXT_ON);
        verify(mockToggleButton).setChecked(A_VALID_IS_CHECKED);
        verify(mockToggleButton).setEnabled(A_VALID_IS_ENABLED);
        verify(mockToggleButton).setVisibility(A_VALID_VISIBILITY);
    }
}