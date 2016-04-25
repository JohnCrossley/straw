package com.jccworld.straw.ui.persisters;

import android.text.Editable;
import android.widget.Button;

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
public class ButtonPersisterTest {
    private static final String A_VALID_TEXT_ENTRY = "A VALID TEXT ENTRY";
    private static final boolean A_VALID_ENABLED_STATE = true;
    private ButtonPersister sut;

    @Mock
    private Button mockButton;

    @Mock
    private Editable mockEditable;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new ButtonPersister();
    }

    @Test
    public void dehydrates() {
        // init
        when(mockButton.getText()).thenReturn(mockEditable);
        when(mockEditable.toString()).thenReturn(A_VALID_TEXT_ENTRY);
        when(mockButton.isEnabled()).thenReturn(A_VALID_ENABLED_STATE);

        // run
        ButtonBean result = sut.dehydrate(mockButton);

        // verify
        assertEquals(A_VALID_TEXT_ENTRY, result.text);
        assertEquals(A_VALID_ENABLED_STATE, result.enabled);
    }

    @Test
    public void hydrates() {
        // init
        final PersistedDataBean persistedDataBean = new ButtonBean(A_VALID_TEXT_ENTRY, A_VALID_ENABLED_STATE);

        // run
        sut.hydrate(mockButton, persistedDataBean);

        // verify
        verify(mockButton).setText(A_VALID_TEXT_ENTRY);
        verify(mockButton).setEnabled(A_VALID_ENABLED_STATE);
    }
}