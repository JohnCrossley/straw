package com.jccworld.straw.ui.persisters;

import android.widget.Button;

import com.jccworld.straw.ui.vo.Persister;

/**
 * Created by jcc on 04/11/15.
 */
public class ButtonPersister implements Persister<Button> {

    @Override
    public ButtonBean dehydrate(final Button button) {
        ButtonBean bean = new ButtonBean(button.getText().toString(), button.isEnabled(), button.getVisibility());
        return bean;
    }

    @Override
    public void hydrate(final Button button, final PersistedDataBean payload) {
        ButtonBean bean = (ButtonBean) payload;
        button.setText(bean.text);
        button.setEnabled(bean.enabled);
        button.setVisibility(bean.visibility);
    }
}
