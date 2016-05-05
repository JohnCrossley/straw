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
     * Save data.  Use the keyValueCache to store your data between instances, and the uiPersister
     * to persisted the UI components annotated with @Persisted.
     *
     * Save permanent data to database/file using your own code.
     *
     * Runs in UI thread so keep it quick.
     *
     * @param keyValueCache
     * @param uiPersister
     */
    void onSave(final KeyValueCache keyValueCache, final UIPersister uiPersister);

    /**
     * Load data.  Use the keyValueCache to retrieve your data between instances, and the uiPersister
     * to load UI components annotated with @Persisted.
     *
     * Load your other data from database/file using your own code.
     *
     * Runs in UI thread so keep it quick.
     *
     * @param keyValueCache
     * @param uiPersister
     */
    void onLoad(final boolean firstRun, final KeyValueCache keyValueCache, final UIPersister uiPersister);

}
