package com.jccworld.straw.application;

import com.jccworld.straw.activity.ActivityCache;

/**
 * Applications must implement this interface and specify it in the AndroidManifest.xml
 * file.
 *
 * Created by jcc on 27/10/15.
 */
public interface ApplicationCallbacks {

    ActivityCache getActivityCache();

}
