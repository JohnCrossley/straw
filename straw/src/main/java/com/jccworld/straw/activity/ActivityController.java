package com.jccworld.straw.activity;

import android.content.Context;
import android.content.Intent;

import com.jccworld.straw.application.ApplicationCallbacks;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jcc on 27/10/15.
 */
public class ActivityController {

    public static final String ACTIVITY_ID = "ACTIVITY_ID";
    public static final String FIRST_TIME = "FIRST_TIME";

    public static void checkApplicationContext(final Context appContext) {
        if (appContext instanceof ApplicationCallbacks) {
            return;
        } else {
            throw new RuntimeException("Your application needs to implement ApplicationCallbacks interface.  Check docs.");
        }
    }

    public static String startActivity(final Context context, final Class clazz, final Map<String, Object> initParams) {
        return doIt(context, clazz, initParams);
    }

    public static String startActivity(final Context context, final Class clazz) {
        return doIt(context, clazz, new HashMap<String, Object>());
    }

    private static String doIt(final Context context, final Class clazz, final Map<String, Object> initParams) {
        checkApplicationContext(context.getApplicationContext());

        UUID uuid = UUID.randomUUID();
        final String activityId = clazz.getName() + ":" + uuid.toString();
        Intent intent = new Intent(context, clazz);
        intent.putExtra(ACTIVITY_ID, activityId);

        final ActivityCache activityCache = ((ApplicationCallbacks) context.getApplicationContext()).getActivityCache();
        activityCache.create(activityId, initParams);

        context.startActivity(intent);

        return activityId;
    }
}
