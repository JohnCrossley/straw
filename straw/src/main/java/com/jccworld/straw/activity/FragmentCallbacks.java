package com.jccworld.straw.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by johncrossley on 28/04/16.
 */
public interface FragmentCallbacks {

    /**
     * Inflate your fragment view and return it here (equivalent of Fragment.onCreateView())
     * @param inflater
     * @param container
     * @return
     */
    View inflateView(final LayoutInflater inflater, final ViewGroup container);

}
