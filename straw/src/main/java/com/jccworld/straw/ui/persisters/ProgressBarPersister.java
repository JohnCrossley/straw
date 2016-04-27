package com.jccworld.straw.ui.persisters;

import android.widget.ProgressBar;

import com.jccworld.straw.ui.vo.Persister;

/**
 * Created by jcc on 04/11/15.
 */
public class ProgressBarPersister implements Persister<ProgressBar> {
    @Override
    public ProgressBarBean dehydrate(final ProgressBar progressBar) {
        ProgressBarBean bean = new ProgressBarBean(progressBar.isEnabled(), progressBar.getVisibility());
        return bean;
    }

    @Override
    public void hydrate(final ProgressBar progressBar, final PersistedDataBean payload) {
        ProgressBarBean bean = (ProgressBarBean) payload;
        progressBar.setEnabled(bean.enabled);
        progressBar.setVisibility(bean.visibility);
    }
}
