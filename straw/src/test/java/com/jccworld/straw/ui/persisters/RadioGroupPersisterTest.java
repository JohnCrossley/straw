package com.jccworld.straw.ui.persisters;

import android.text.Editable;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * FIXME these tests are functional but are hard to read.
 * Create Hamcrest matcher to match beans against Android UI
 *
 * Created by johncrossley on 22/04/16.
 */
public class RadioGroupPersisterTest {
    private static final boolean A_VALID_ENABLED_STATE = true;

    private static final String TEXT_0 = "text 0";
    private static final String TEXT_1 = "text 1";
    private static final String TEXT_2 = "text 2";

    private static final boolean ENABLED_0 = true;
    private static final boolean ENABLED_1 = false;
    private static final boolean ENABLED_2 = true;

    private static final int VISIBILITY_0 = View.VISIBLE;
    private static final int VISIBILITY_1 = View.INVISIBLE;
    private static final int VISIBILITY_2 = View.GONE;

    private static final int A_VALID_ID = 123;

    private RadioGroupPersister sut;

    @Mock
    private RadioGroup mockRadioGroup;

    @Mock
    private Editable mockEditable;

    @Mock
    private RadioButton mockRadioButton0;

    @Mock
    private RadioButton mockRadioButton1;

    @Mock
    private RadioButton mockRadioButton2;

    @Mock
    private View mockViewAlternative1;

    @Mock
    private CharSequence mockCharacterSequence0;

    @Mock
    private CharSequence mockCharacterSequence1;

    @Mock
    private CharSequence mockCharacterSequence2;

    private RadioButtonBean radioBean0;
    private RadioButtonBean radioBean1;
    private RadioButtonBean radioBean2;

    private RadioButtonBean radioAlternativeBean1;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sut = new RadioGroupPersister();

        when(mockRadioButton0.getText()).thenReturn(mockCharacterSequence0);
        when(mockRadioButton1.getText()).thenReturn(mockCharacterSequence1);
        when(mockRadioButton2.getText()).thenReturn(mockCharacterSequence2);

        when(mockCharacterSequence0.toString()).thenReturn(TEXT_0);
        when(mockCharacterSequence1.toString()).thenReturn(TEXT_1);
        when(mockCharacterSequence2.toString()).thenReturn(TEXT_2);

        when(mockRadioButton0.isEnabled()).thenReturn(ENABLED_0);
        when(mockRadioButton1.isEnabled()).thenReturn(ENABLED_1);
        when(mockRadioButton2.isEnabled()).thenReturn(ENABLED_2);

        when(mockRadioButton0.getVisibility()).thenReturn(VISIBILITY_0);
        when(mockRadioButton1.getVisibility()).thenReturn(VISIBILITY_1);
        when(mockRadioButton2.getVisibility()).thenReturn(VISIBILITY_2);

        radioBean0 = new RadioButtonBean(TEXT_0, ENABLED_0, VISIBILITY_0);
        radioBean1 = new RadioButtonBean(TEXT_1, ENABLED_1, VISIBILITY_1);
        radioBean2 = new RadioButtonBean(TEXT_2, ENABLED_2, VISIBILITY_2);

        radioAlternativeBean1 = new RadioButtonBean(ENABLED_1, VISIBILITY_1);

        when(mockViewAlternative1.isEnabled()).thenReturn(ENABLED_1);
        when(mockViewAlternative1.getVisibility()).thenReturn(VISIBILITY_1);
    }

    @Test
    public void dehydrates() {
        // init
        when(mockRadioGroup.isEnabled()).thenReturn(A_VALID_ENABLED_STATE);
        when(mockRadioGroup.getChildCount()).thenReturn(3);
        when(mockRadioGroup.getChildAt(0)).thenReturn(mockRadioButton0);
        when(mockRadioGroup.getChildAt(1)).thenReturn(mockRadioButton1);
        when(mockRadioGroup.getChildAt(2)).thenReturn(mockRadioButton2);

        // run
        RadioGroupBean result = sut.dehydrate(mockRadioGroup);

        // verify: should move into a matcher
        assertEquals(3, result.radioButtonBeans.length);
        assertEquals(TEXT_0, result.radioButtonBeans[0].text);
        assertEquals(ENABLED_0, result.radioButtonBeans[0].enabled);
        assertEquals(VISIBILITY_0, result.radioButtonBeans[0].visibility);
        assertEquals(TEXT_1, result.radioButtonBeans[1].text);
        assertEquals(ENABLED_1, result.radioButtonBeans[1].enabled);
        assertEquals(VISIBILITY_1, result.radioButtonBeans[1].visibility);
        assertEquals(TEXT_2, result.radioButtonBeans[2].text);
        assertEquals(ENABLED_2, result.radioButtonBeans[2].enabled);
        assertEquals(VISIBILITY_2, result.radioButtonBeans[2].visibility);
    }

    @Test //a mixed group of other non-RadioButton views
    public void dehydratesMixedGroup() {
        // init
        when(mockRadioGroup.isEnabled()).thenReturn(A_VALID_ENABLED_STATE);
        when(mockRadioGroup.getChildCount()).thenReturn(3);
        when(mockRadioGroup.getChildAt(0)).thenReturn(mockRadioButton0);
        when(mockRadioGroup.getChildAt(1)).thenReturn(mockViewAlternative1);
        when(mockRadioGroup.getChildAt(2)).thenReturn(mockRadioButton2);

        // run
        RadioGroupBean result = sut.dehydrate(mockRadioGroup);

        // verify: should move into a matcher
        assertEquals(3, result.radioButtonBeans.length);
        assertEquals(TEXT_0, result.radioButtonBeans[0].text);
        assertEquals(ENABLED_0, result.radioButtonBeans[0].enabled);
        assertEquals(VISIBILITY_0, result.radioButtonBeans[0].visibility);
        assertEquals(ENABLED_1, result.radioButtonBeans[1].enabled);
        assertEquals(VISIBILITY_1, result.radioButtonBeans[1].visibility);
        assertEquals(TEXT_2, result.radioButtonBeans[2].text);
        assertEquals(ENABLED_2, result.radioButtonBeans[2].enabled);
        assertEquals(VISIBILITY_2, result.radioButtonBeans[2].visibility);
    }

    @Test
    public void dehydrateSetsSelectedIndex() {
        // init
        when(mockRadioGroup.getChildCount()).thenReturn(3);
        when(mockRadioGroup.getChildAt(0)).thenReturn(mockRadioButton0);
        when(mockRadioGroup.getChildAt(1)).thenReturn(mockRadioButton1);
        when(mockRadioGroup.getChildAt(2)).thenReturn(mockRadioButton2);
        when(mockRadioButton1.getId()).thenReturn(A_VALID_ID);
        when(mockRadioGroup.getCheckedRadioButtonId()).thenReturn(A_VALID_ID);

        // run
        RadioGroupBean result = sut.dehydrate(mockRadioGroup);

        // verify: should move into a matcher
        assertEquals(1, result.selectedIdx);
    }

    @Test
    public void hydrates() {
        // init
        when(mockRadioGroup.getChildCount()).thenReturn(3);
        when(mockRadioGroup.getChildAt(0)).thenReturn(mockRadioButton0);
        when(mockRadioGroup.getChildAt(1)).thenReturn(mockRadioButton1);
        when(mockRadioGroup.getChildAt(2)).thenReturn(mockRadioButton2);
        RadioButtonBean[] radioButtonBeans = { radioBean0, radioBean1, radioBean2 };
        final PersistedDataBean persistedDataBean = new RadioGroupBean(radioButtonBeans, 1);//IDX 1

        // run
        sut.hydrate(mockRadioGroup, persistedDataBean);

        // verify
        verify(mockRadioButton0).setText(TEXT_0);
        verify(mockRadioButton0).setEnabled(ENABLED_0);
        verify(mockRadioButton0).setVisibility(VISIBILITY_0);
        verify(mockRadioButton1).setText(TEXT_1);
        verify(mockRadioButton1).setEnabled(ENABLED_1);
        verify(mockRadioButton1).setVisibility(VISIBILITY_1);
        verify(mockRadioButton2).setText(TEXT_2);
        verify(mockRadioButton2).setEnabled(ENABLED_2);
        verify(mockRadioButton2).setVisibility(VISIBILITY_2);
        verify(mockRadioButton1).setSelected(true);//IDX 1
    }

    @Test(expected = RuntimeException.class)
    public void blowsUpOnHydrateWhenBeanHasIllegalSelectedIndex() {
        when(mockRadioGroup.getChildCount()).thenReturn(3);
        when(mockRadioGroup.getChildAt(0)).thenReturn(mockRadioButton0);
        when(mockRadioGroup.getChildAt(1)).thenReturn(mockViewAlternative1);
        when(mockRadioGroup.getChildAt(2)).thenReturn(mockRadioButton2);
        RadioButtonBean[] radioButtonBeans = { radioBean0, radioAlternativeBean1, radioBean2 };
        final PersistedDataBean persistedDataBean = new RadioGroupBean(radioButtonBeans, 1);//IDX 1

        // run: will blow up because IDX 1 is not a RadioButton so cannot be "selected"
        sut.hydrate(mockRadioGroup, persistedDataBean);
    }
}