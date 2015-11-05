package com.jccworld.straw.ui.vo;

/**
 * Created by jcc on 04/11/15.
 */
public interface Persister<T> {

    Object dehydrate(T t);

    void hydrate(T t, Object payload);

}
