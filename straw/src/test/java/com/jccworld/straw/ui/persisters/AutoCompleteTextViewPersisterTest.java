package com.jccworld.straw.ui.persisters;

import android.text.Editable;
import android.widget.AutoCompleteTextView;

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
public class AutoCompleteTextViewPersisterTest {
    private static final String A_VALID_TEXT_ENTRY = "A VALID TEXT ENTRY";
    private static final boolean A_VALID_ENABLED_STATE = true;
    private AutoCompleteTextViewPersister sut;

    @Mock
    private AutoCompleteTextView mockAutoCompleteTextView;

    @Mock
    private Editable mockEditable;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new AutoCompleteTextViewPersister();
    }

    @Test
    public void dehydrates() {
        // init
        when(mockAutoCompleteTextView.getText()).thenReturn(mockEditable);
        when(mockEditable.toString()).thenReturn(A_VALID_TEXT_ENTRY);
        when(mockAutoCompleteTextView.isEnabled()).thenReturn(A_VALID_ENABLED_STATE);

        // run
        AutoCompleteTextViewBean result = sut.dehydrate(mockAutoCompleteTextView);

        // verify
        assertEquals(A_VALID_TEXT_ENTRY, result.text);
        assertEquals(A_VALID_ENABLED_STATE, result.enabled);
    }

    @Test
    public void hydrates() {
        // init
        final PersistedDataBean persistedDataBean = new AutoCompleteTextViewBean(A_VALID_TEXT_ENTRY, A_VALID_ENABLED_STATE);

        // run
        sut.hydrate(mockAutoCompleteTextView, persistedDataBean);

        // verify
        verify(mockAutoCompleteTextView).setText(A_VALID_TEXT_ENTRY);
        verify(mockAutoCompleteTextView).setEnabled(A_VALID_ENABLED_STATE);
    }
}