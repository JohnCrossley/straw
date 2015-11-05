package com.jccworld.straw.activity;

import com.jccworld.straw.KeyValueCache;
import com.jccworld.straw.UIPersister;

/**
 * A much simpler interface for an Activity.  All other stuff should be implemented under
 * separate interfaces.  I.e. startActivity()
 *
 * Created by jcc on 26/10/15.
 */
public interface ActivityCallbacks {

    /**
     * Create view: bind ui components to variables, etc.
     */
    void created();

    /**
     * Bind services/listeners etc.
     */
    void onFocus();

    /**
     * Unbind services/listeners etc.
     */
    void onFocusLost();

    /**
     *
     * @param keyValueCache
     * @param uiPersister
     */
    void onSave(final KeyValueCache keyValueCache, final UIPersister uiPersister);

    /**
     *
     * @param keyValueCache
     * @param uiPersister
     */
    void onLoad(final boolean firstRun, final KeyValueCache keyValueCache, final UIPersister uiPersister);

}
