package com.jccworld.straw.ui.persisters;

import android.widget.Spinner;

import com.jccworld.straw.ui.vo.Persister;

/**
 * Created by jcc on 04/11/15.
 */
public class SpinnerPersister implements Persister<Spinner> {
    @Override
    public Object dehydrate(final Spinner spinner) {
        int selectedIdx = -1;

        if (spinner.getSelectedItemId() != Spinner.INVALID_ROW_ID) { //inconsistent with other Views...
            for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
                if (spinner.getSelectedItemId() ==  spinner.getItemIdAtPosition(i)) {
                    selectedIdx = i;
                    break;
                }
            }
        }

        SpinnerBean bean = new SpinnerBean(selectedIdx, spinner.isEnabled());
        return bean;
    }

    @Override
    public void hydrate(Spinner spinner, Object payload) {
        SpinnerBean bean = (SpinnerBean) payload;

        if (bean.selectedIdx >= 0) {
            spinner.setSelection(bean.selectedIdx);
        }

        spinner.setEnabled(bean.enabled);
    }
}
