package com.jccworld.straw.ui.persisters;

import android.text.Editable;
import android.widget.EditText;

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
public class EditTextPersisterTest {
    private static final String A_VALID_TEXT_ENTRY = "A VALID TEXT ENTRY";
    private static final boolean A_VALID_ENABLED_STATE = true;
    private EditTextPersister sut;

    @Mock
    private EditText mockEditText;

    @Mock
    private Editable mockEditable;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new EditTextPersister();
    }

    @Test
    public void dehydrates() {
        // init
        when(mockEditText.getText()).thenReturn(mockEditable);
        when(mockEditable.toString()).thenReturn(A_VALID_TEXT_ENTRY);
        when(mockEditText.isEnabled()).thenReturn(A_VALID_ENABLED_STATE);

        // run
        EditTextBean result = sut.dehydrate(mockEditText);

        // verify
        assertEquals(A_VALID_TEXT_ENTRY, result.text);
        assertEquals(A_VALID_ENABLED_STATE, result.enabled);
    }

    @Test
    public void hydrates() {
        // init
        final PersistedDataBean persistedDataBean = new EditTextBean(A_VALID_TEXT_ENTRY, A_VALID_ENABLED_STATE);

        // run
        sut.hydrate(mockEditText, persistedDataBean);

        // verify
        verify(mockEditText).setText(A_VALID_TEXT_ENTRY);
        verify(mockEditText).setEnabled(A_VALID_ENABLED_STATE);
    }
}