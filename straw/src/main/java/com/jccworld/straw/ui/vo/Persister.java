package com.jccworld.straw.ui.vo;

import com.jccworld.straw.ui.persisters.PersistedDataBean;

/**
 * Created by jcc on 04/11/15.
 */
public interface Persister<T> {

    PersistedDataBean dehydrate(T t);

    void hydrate(T t, PersistedDataBean payload);

}
