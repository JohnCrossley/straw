package com.jccworld.straw.ui.persisters;

import android.os.Build;
import android.widget.ToggleButton;

import com.jccworld.straw.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by johncrossley on 22/04/16.
 */
public class ToggleButtonPersisterTest {
    private static final String EXPECTED_TEXT_ON = "valid text on";
    private static final String EXPECTED_TEXT_OFF = "valid text off";
    private static final boolean EXPECTED_IS_CHECKED = true;
    private static final boolean EXPECTED_IS_ENABLED = true;

    private ToggleButtonPersister sut;

    @Mock
    private ToggleButton mockToggleButton;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new ToggleButtonPersister();

        when(mockToggleButton.getTextOn()).thenReturn(EXPECTED_TEXT_ON);
        when(mockToggleButton.getTextOff()).thenReturn(EXPECTED_TEXT_OFF);
        when(mockToggleButton.isChecked()).thenReturn(EXPECTED_IS_CHECKED);
        when(mockToggleButton.isEnabled()).thenReturn(EXPECTED_IS_ENABLED);
    }

    @Test
    public void dehydrate() {
        // run
        final ToggleButtonBean result = sut.dehydrate(mockToggleButton);

        // verify
        assertEquals(EXPECTED_TEXT_ON, result.textOn);
        assertEquals(EXPECTED_TEXT_OFF, result.textOff);
        assertEquals(EXPECTED_IS_CHECKED, result.checked);
        assertEquals(EXPECTED_IS_ENABLED, result.enabled);
    }

    @Test
    public void hydratePreLollipop() {
        // init
        final PersistedDataBean payload = new ToggleButtonBean(EXPECTED_TEXT_ON, EXPECTED_TEXT_OFF,
                EXPECTED_IS_CHECKED, EXPECTED_IS_ENABLED);

        // run
        sut.hydrate(mockToggleButton, payload);

        // verify
        verify(mockToggleButton).setTextOn(EXPECTED_TEXT_ON);
        verify(mockToggleButton).setTextOff(EXPECTED_TEXT_OFF);
        verify(mockToggleButton).setTextOn(EXPECTED_TEXT_ON);
        verify(mockToggleButton).setChecked(EXPECTED_IS_CHECKED);
        verify(mockToggleButton).setEnabled(EXPECTED_IS_ENABLED);
    }
}