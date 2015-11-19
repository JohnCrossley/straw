package com.jccworld.straw.ui.persisters;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jccworld.straw.ui.vo.Persister;

/**
 * Created by jcc on 04/11/15.
 */
public class RadioGroupPersister implements Persister<RadioGroup> {
    @Override
    public Object dehydrate(final RadioGroup radioGroup) {
        RadioButtonBean radioButtonBeans[] = new RadioButtonBean[radioGroup.getChildCount()];
        int selectedIdx = -1;

        for(int i = 0; i < radioGroup.getChildCount(); i++) {
            View childView = radioGroup.getChildAt(i);

            if (childView instanceof RadioButton) {
                radioButtonBeans[i] = new RadioButtonBean(((RadioButton)childView).getText().toString(), childView.isEnabled());
            } else {
                radioButtonBeans[i] = new RadioButtonBean(childView.isEnabled());
            }

            if (childView.getId() >= 0 && childView.getId() == radioGroup.getCheckedRadioButtonId()) {
                selectedIdx = i;
            }
        }

        return new RadioGroupBean(radioButtonBeans, selectedIdx);
    }

    @Override
    public void hydrate(final RadioGroup radioGroup, final Object payload) {
        final RadioGroupBean bean = (RadioGroupBean) payload;

        for(int i = 0; i < radioGroup.getChildCount(); i++) {
            View child = radioGroup.getChildAt(i);

            if (child instanceof RadioButton) {
                ((RadioButton) child).setText(bean.radioButtonBeans[i].text);
            }
            radioGroup.getChildAt(i).setEnabled(bean.radioButtonBeans[i].enabled);

            if (i >= 0 && i == bean.selectedIdx) {
                if (child instanceof RadioButton) {
                    child.setSelected(true);
                } else {
                    throw new RuntimeException("The order of the RadioGroup has changed.  Please ensure the list order is always the same.  " +
                            "Expected: RadioButton, but got: " + child.getClass() + " at position: " + i);
                }
            }
        }
    }
}
