package com.jccworld.straw.application;

import com.jccworld.straw.activity.UIComponentCache;

/**
 * Applications must implement this interface and specify it in the AndroidManifest.xml under
 * <application name="YourImplementationClass" ..>...</application>
 * file.
 *
 * Created by jcc on 27/10/15.
 */
public interface ApplicationCallbacks {

    UIComponentCache getUIComponentCache();

}
