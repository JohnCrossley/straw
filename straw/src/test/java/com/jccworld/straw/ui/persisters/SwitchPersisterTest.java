package com.jccworld.straw.ui.persisters;

import android.os.Build;
import android.widget.Switch;

import com.jccworld.straw.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by johncrossley on 22/04/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
public class SwitchPersisterTest {

    private static final String EXPECTED_TEXT_ON = "valid text on";
    private static final String EXPECTED_TEXT_OFF = "valid text off";
    private static final boolean EXPECTED_SHOW_TEXT = true;
    private static final boolean EXPECTED_SHOW_TEXT_PRE_LOLLIPOP = false;
    private static final boolean EXPECTED_IS_CHECKED = true;
    private static final boolean EXPECTED_IS_ENABLED = true;

    private SwitchPersister sut;
    
    @Mock
    private Switch mockSwitch;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new SwitchPersister();

        when(mockSwitch.getTextOn()).thenReturn(EXPECTED_TEXT_ON);
        when(mockSwitch.getTextOff()).thenReturn(EXPECTED_TEXT_OFF);
        when(mockSwitch.isChecked()).thenReturn(EXPECTED_IS_CHECKED);
        when(mockSwitch.isEnabled()).thenReturn(EXPECTED_IS_ENABLED);
    }

    @Test
    @Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.KITKAT)
    public void dehydratePreLollipop() {
        // run
        final SwitchBean result = sut.dehydrate(mockSwitch);

        // verify
        assertEquals(EXPECTED_TEXT_ON, result.textOn);
        assertEquals(EXPECTED_TEXT_OFF, result.textOff);
        assertEquals(EXPECTED_SHOW_TEXT_PRE_LOLLIPOP, result.showText);
        assertEquals(EXPECTED_IS_CHECKED, result.checked);
        assertEquals(EXPECTED_IS_ENABLED, result.enabled);
    }

    @Test
    @Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
    public void dehydratePostLollipop() {
        // init
        when(mockSwitch.getShowText()).thenReturn(EXPECTED_SHOW_TEXT);

        // run
        final SwitchBean result = sut.dehydrate(mockSwitch);

        // verify
        verify(mockSwitch).getShowText();
        assertEquals(EXPECTED_TEXT_ON, result.textOn);
        assertEquals(EXPECTED_TEXT_OFF, result.textOff);
        assertEquals(EXPECTED_SHOW_TEXT, result.showText);
        assertEquals(EXPECTED_IS_CHECKED, result.checked);
        assertEquals(EXPECTED_IS_ENABLED, result.enabled);
    }

    @Test
    @Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.KITKAT)
    public void hydratePreLollipop() {
        // init
        final PersistedDataBean payload = new SwitchBean(EXPECTED_TEXT_ON, EXPECTED_TEXT_OFF,
                EXPECTED_SHOW_TEXT, EXPECTED_IS_CHECKED, EXPECTED_IS_ENABLED);

        // run
        sut.hydrate(mockSwitch, payload);

        // verify
        verify(mockSwitch).setTextOn(EXPECTED_TEXT_ON);
        verify(mockSwitch).setTextOff(EXPECTED_TEXT_OFF);
        verify(mockSwitch).setTextOn(EXPECTED_TEXT_ON);
        verify(mockSwitch).setChecked(EXPECTED_IS_CHECKED);
        verify(mockSwitch).setEnabled(EXPECTED_IS_ENABLED);
    }

    @Test
    @Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
    public void hydratePostLollipop() {
        // init
        when(mockSwitch.getShowText()).thenReturn(EXPECTED_SHOW_TEXT);
        final PersistedDataBean payload = new SwitchBean(EXPECTED_TEXT_ON, EXPECTED_TEXT_OFF,
                EXPECTED_SHOW_TEXT, EXPECTED_IS_CHECKED, EXPECTED_IS_ENABLED);

        // run
        sut.hydrate(mockSwitch, payload);

        // verify
        verify(mockSwitch).setTextOn(EXPECTED_TEXT_ON);
        verify(mockSwitch).setTextOff(EXPECTED_TEXT_OFF);
        verify(mockSwitch).setTextOn(EXPECTED_TEXT_ON);
        verify(mockSwitch).setShowText(EXPECTED_SHOW_TEXT);
        verify(mockSwitch).setChecked(EXPECTED_IS_CHECKED);
        verify(mockSwitch).setEnabled(EXPECTED_IS_ENABLED);
    }
}