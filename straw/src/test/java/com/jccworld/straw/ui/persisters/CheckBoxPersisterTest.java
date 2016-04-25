package com.jccworld.straw.ui.persisters;

import android.text.Editable;
import android.widget.CheckBox;

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
public class CheckBoxPersisterTest {
    private static final String A_VALID_TEXT_ENTRY = "A VALID TEXT ENTRY";
    private static final boolean A_VALID_ENABLED_STATE = true;
    private CheckBoxPersister sut;

    @Mock
    private CheckBox mockCheckBox;

    @Mock
    private Editable mockEditable;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new CheckBoxPersister();
    }

    @Test
    public void dehydrates() {
        // init
        when(mockCheckBox.getText()).thenReturn(mockEditable);
        when(mockEditable.toString()).thenReturn(A_VALID_TEXT_ENTRY);
        when(mockCheckBox.isEnabled()).thenReturn(A_VALID_ENABLED_STATE);

        // run
        CheckBoxBean result = sut.dehydrate(mockCheckBox);

        // verify
        assertEquals(A_VALID_TEXT_ENTRY, result.text);
        assertEquals(A_VALID_ENABLED_STATE, result.enabled);
    }

    @Test
    public void hydrates() {
        // init
        final PersistedDataBean persistedDataBean = new CheckBoxBean(A_VALID_TEXT_ENTRY, A_VALID_ENABLED_STATE);

        // run
        sut.hydrate(mockCheckBox, persistedDataBean);

        // verify
        verify(mockCheckBox).setText(A_VALID_TEXT_ENTRY);
        verify(mockCheckBox).setEnabled(A_VALID_ENABLED_STATE);
    }
}