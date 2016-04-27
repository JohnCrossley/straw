package com.jccworld.straw.ui.persisters;

import android.os.Build;
import android.view.View;
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

    private static final String A_VALID_TEXT_ON = "valid text on";
    private static final String A_VALID_TEXT_OFF = "valid text off";
    private static final boolean A_VALID_SHOW_TEXT = true;
    private static final boolean A_VALID_SHOW_TEXT_PRE_LOLLIPOP = false;
    private static final boolean A_VALID_IS_CHECKED = true;
    private static final boolean A_VALID_IS_ENABLED = true;
    private static final int A_VALID_VISIBILITY = View.VISIBLE;

    private SwitchPersister sut;
    
    @Mock
    private Switch mockSwitch;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new SwitchPersister();

        when(mockSwitch.getTextOn()).thenReturn(A_VALID_TEXT_ON);
        when(mockSwitch.getTextOff()).thenReturn(A_VALID_TEXT_OFF);
        when(mockSwitch.isChecked()).thenReturn(A_VALID_IS_CHECKED);
        when(mockSwitch.isEnabled()).thenReturn(A_VALID_IS_ENABLED);
        when(mockSwitch.getVisibility()).thenReturn(A_VALID_VISIBILITY);
    }

    @Test
    @Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.KITKAT)
    public void dehydratePreLollipop() {
        // run
        final SwitchBean result = sut.dehydrate(mockSwitch);

        // verify
        assertEquals(A_VALID_TEXT_ON, result.textOn);
        assertEquals(A_VALID_TEXT_OFF, result.textOff);
        assertEquals(A_VALID_SHOW_TEXT_PRE_LOLLIPOP, result.showText);
        assertEquals(A_VALID_IS_CHECKED, result.checked);
        assertEquals(A_VALID_IS_ENABLED, result.enabled);
        assertEquals(A_VALID_VISIBILITY, result.visibility);
    }

    @Test
    @Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
    public void dehydratePostLollipop() {
        // init
        when(mockSwitch.getShowText()).thenReturn(A_VALID_SHOW_TEXT);

        // run
        final SwitchBean result = sut.dehydrate(mockSwitch);

        // verify
        verify(mockSwitch).getShowText();
        assertEquals(A_VALID_TEXT_ON, result.textOn);
        assertEquals(A_VALID_TEXT_OFF, result.textOff);
        assertEquals(A_VALID_SHOW_TEXT, result.showText);
        assertEquals(A_VALID_IS_CHECKED, result.checked);
        assertEquals(A_VALID_IS_ENABLED, result.enabled);
        assertEquals(A_VALID_VISIBILITY, result.visibility);
    }

    @Test
    @Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.KITKAT)
    public void hydratePreLollipop() {
        // init
        final PersistedDataBean payload = new SwitchBean(A_VALID_TEXT_ON, A_VALID_TEXT_OFF,
                A_VALID_SHOW_TEXT, A_VALID_IS_CHECKED, A_VALID_IS_ENABLED, A_VALID_VISIBILITY);

        // run
        sut.hydrate(mockSwitch, payload);

        // verify
        verify(mockSwitch).setTextOn(A_VALID_TEXT_ON);
        verify(mockSwitch).setTextOff(A_VALID_TEXT_OFF);
        verify(mockSwitch).setTextOn(A_VALID_TEXT_ON);
        verify(mockSwitch).setChecked(A_VALID_IS_CHECKED);
        verify(mockSwitch).setEnabled(A_VALID_IS_ENABLED);
        verify(mockSwitch).setVisibility(A_VALID_VISIBILITY);
    }

    @Test
    @Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
    public void hydratePostLollipop() {
        // init
        when(mockSwitch.getShowText()).thenReturn(A_VALID_SHOW_TEXT);
        final PersistedDataBean payload = new SwitchBean(A_VALID_TEXT_ON, A_VALID_TEXT_OFF,
                A_VALID_SHOW_TEXT, A_VALID_IS_CHECKED, A_VALID_IS_ENABLED, A_VALID_VISIBILITY);

        // run
        sut.hydrate(mockSwitch, payload);

        // verify
        verify(mockSwitch).setTextOn(A_VALID_TEXT_ON);
        verify(mockSwitch).setTextOff(A_VALID_TEXT_OFF);
        verify(mockSwitch).setTextOn(A_VALID_TEXT_ON);
        verify(mockSwitch).setShowText(A_VALID_SHOW_TEXT);
        verify(mockSwitch).setChecked(A_VALID_IS_CHECKED);
        verify(mockSwitch).setEnabled(A_VALID_IS_ENABLED);
        verify(mockSwitch).setVisibility(A_VALID_VISIBILITY);
    }
}