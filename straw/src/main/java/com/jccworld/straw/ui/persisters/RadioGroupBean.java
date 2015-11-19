package com.jccworld.straw.ui.persisters;

/**
 * Created by jcc on 19/11/15.
 */
public class RadioGroupBean {
    final RadioButtonBean[] radioButtonBeans;
    final int selectedIdx;

    public RadioGroupBean(final RadioButtonBean[] radioButtonBeans, int selectedIdx) {
        this.radioButtonBeans = radioButtonBeans;
        this.selectedIdx = selectedIdx;
    }
}
