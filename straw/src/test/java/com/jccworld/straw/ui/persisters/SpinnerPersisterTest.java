package com.jccworld.straw.ui.persisters;

import android.view.View;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

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
public class SpinnerPersisterTest {
    private static final boolean A_VALID_ENABLED_STATE = true;

    private static final int A_SELECTED_IDX = 3;
    private static final Long A_SELECTED_ID = 12345L;
    private static final int A_VALID_VISIBILITY = View.VISIBLE;

    private SpinnerPersister sut;

    @Mock
    private Spinner mockSpinner;

    @Mock
    private SpinnerAdapter mockSpinnerAdaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new SpinnerPersister();
        when(mockSpinner.getAdapter()).thenReturn(mockSpinnerAdaptor);
        when(mockSpinnerAdaptor.getCount()).thenReturn(5);
        when(mockSpinner.getItemIdAtPosition(0)).thenReturn(111L);
        when(mockSpinner.getItemIdAtPosition(1)).thenReturn(222L);
        when(mockSpinner.getItemIdAtPosition(2)).thenReturn(333L);
        when(mockSpinner.getItemIdAtPosition(A_SELECTED_IDX)).thenReturn(A_SELECTED_ID);//4
        when(mockSpinner.getItemIdAtPosition(4)).thenReturn(555L);
        when(mockSpinner.getSelectedItemId()).thenReturn(A_SELECTED_ID);
    }

    @Test
    public void dehydrates() {
        // init
        when(mockSpinner.isEnabled()).thenReturn(A_VALID_ENABLED_STATE);

        // run
        SpinnerBean result = sut.dehydrate(mockSpinner);

        // verify
        assertEquals(A_SELECTED_IDX, result.selectedIdx);
        assertEquals(A_VALID_ENABLED_STATE, result.enabled);
        assertEquals(A_VALID_VISIBILITY, result.visibility);
    }

    @Test
    public void hydrates() {
        // init
        final PersistedDataBean persistedDataBean = new SpinnerBean(A_SELECTED_IDX, A_VALID_ENABLED_STATE, A_VALID_VISIBILITY);

        // run
        sut.hydrate(mockSpinner, persistedDataBean);

        // verify
        verify(mockSpinner).setSelection(A_SELECTED_IDX);
        verify(mockSpinner).setEnabled(A_VALID_ENABLED_STATE);
        verify(mockSpinner).setVisibility(A_VALID_VISIBILITY);
    }
}