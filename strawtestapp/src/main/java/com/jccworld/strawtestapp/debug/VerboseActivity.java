package com.jccworld.strawtestapp.debug;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

import com.jccworld.straw.Cache;
import com.jccworld.straw.activity.ActivityCache;
import com.jccworld.straw.activity.ActivityCallbacks;
import com.jccworld.straw.activity.ActivityController;
import com.jccworld.straw.activity.BaseActivity;
import com.jccworld.straw.application.ApplicationCallbacks;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Created by jcc on 26/10/15.
 */
public abstract class VerboseActivity extends BaseActivity {

    public VerboseActivity() {
        super();
    }

    //----------------------------------------------------------------------------------------------

//    private static final String[] ignore = new String[]{
//            "getApplicationInfo"
//    };

    private static final String[] onlyShow = new String[]{
           "onCreate",
           "onStart",
           "onResume",
           "onPause",
           "onStop",
           "onDestroy",
           "onRestart",
           "onSaveInstanceState",
           "onRestoreInstanceState"
    };
    
    private void logV(final String message) {

//        for(String s : ignore) {
//            if (message.contains(s)) {
//                return;
//            }
//        }

        for(String s : onlyShow) {
            if (message.startsWith(s)) {
                Log.v(getClass().getSimpleName(), "[straw] " + message);
            }
        }

//        Log.v(getClass().getSimpleName(), "[straw] " + message);
    }

    //----------------------------------------------------------------------------------------------


//    @Override
//    public ActionBar getSupportActionBar() {
//        logV("getSupportActionBar()");
//        return super.getSupportActionBar();
//    }

//    @Override
//    public void setSupportActionBar(Toolbar toolbar) {
//        logV("setSupportActionBar(toolbar: " + toolbar + ")");
//        super.setSupportActionBar(toolbar);
//    }

    @Override
    public MenuInflater getMenuInflater() {
        logV("getMenuInflater()");
        return super.getMenuInflater();
    }

    @Override
    public void setContentView(int layoutResID) {
        logV("setContentView(layoutResID: " + layoutResID + ")");
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        logV("setContentView(view: " + view + ")");
        super.setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        logV("setContentView(view: " + view + ", params: " + params + ")");
        super.setContentView(view, params);
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        logV("addContentView(view: " + view + ", params: " + params + ")");
        super.addContentView(view, params);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        logV("onConfigurationChanged(newConfig: " + newConfig + ")");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStop() {
        logV("onStop()");
        super.onStop();
    }

    @Override
    protected void onPostResume() {
        logV("onPostResume()");
        super.onPostResume();
    }

    @Override
    public View onCreatePanelView(int featureId) {
        logV("onCreatePanelView(featureId: " + featureId + ")");
        return super.onCreatePanelView(featureId);
    }

    @Override
    protected void onDestroy() {
        logV("onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        logV("onTitleChanged(title: " + title + ", color: " + color + ")");
        super.onTitleChanged(title, color);
    }

//    @Override
//    public boolean supportRequestWindowFeature(int featureId) {
//        logV("supportRequestWindowFeature(featureId: " + featureId + ")");
//        return super.supportRequestWindowFeature(featureId);
//    }

//    @Override
//    public void supportInvalidateOptionsMenu() {
//        logV("supportInvalidateOptionsMenu()");
//        super.supportInvalidateOptionsMenu();
//    }

    @Override
    public void invalidateOptionsMenu() {
        logV("invalidateOptionsMenu()");
        super.invalidateOptionsMenu();
    }

//    @Override
//    public void onSupportActionModeStarted(ActionMode mode) {
//        logV("onSupportActionModeStarted(mode: " + mode + ")");
//        super.onSupportActionModeStarted(mode);
//    }

//    @Override
//    public void onSupportActionModeFinished(ActionMode mode) {
//        logV("onSupportActionModeFinished(mode: " + mode + ")");
//        super.onSupportActionModeFinished(mode);
//    }

//    @Override
//    public ActionMode startSupportActionMode(ActionMode.Callback callback) {
//        logV("startSupportActionMode(callback: " + callback + ")");
//        return super.startSupportActionMode(callback);
//    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        logV("onCreatePanelMenu(featureId: " + featureId + ", menu: " + menu + ")");
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        logV("onPreparePanel(featureId: " + featureId + ", view: " + view + ", menu: " + menu + ")");
        return super.onPreparePanel(featureId, view, menu);
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        logV("onPanelClosed(featureId: " + featureId + ", menu: " + menu + ")");
        super.onPanelClosed(featureId, menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        logV("onMenuOpened(featureId: " + featureId + ", menu: " + menu + ")");
        return super.onMenuOpened(featureId, menu);
    }

//    @Override
//    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
//        logV("onPrepareOptionsPanel(view: " + view + ", menu: " + menu + ")");
//        return super.onPrepareOptionsPanel(view, menu);
//    }

    @Override
    public void onBackPressed() {
        logV("onBackPressed()");
        super.onBackPressed();
    }

//    @Override
//    public void setSupportProgressBarVisibility(boolean visible) {
//        logV("setSupportProgressBarVisibility(visible: " + visible + ")");
//        super.setSupportProgressBarVisibility(visible);
//    }

//    @Override
//    public void setSupportProgressBarIndeterminateVisibility(boolean visible) {
//        logV("setSupportProgressBarIndeterminateVisibility(visible: " + visible + ")");
//        super.setSupportProgressBarIndeterminateVisibility(visible);
//    }

//    @Override
//    public void setSupportProgressBarIndeterminate(boolean indeterminate) {
//        logV("setSupportProgressBarIndeterminate(indeterminate: " + indeterminate + ")");
//        super.setSupportProgressBarIndeterminate(indeterminate);
//    }

//    @Override
//    public void setSupportProgress(int progress) {
//        logV("setSupportProgress(progress: " + progress + ")");
//        super.setSupportProgress(progress);
//    }

//    @Override
//    public void onCreateSupportNavigateUpTaskStack(TaskStackBuilder builder) {
//        logV("onCreateSupportNavigateUpTaskStack(taskStackBuilder: " + builder + ")");
//        super.onCreateSupportNavigateUpTaskStack(builder);
//    }

//    @Override
//    public void onPrepareSupportNavigateUpTaskStack(TaskStackBuilder builder) {
//        logV("onPrepareSupportNavigateUpTaskStack(taskStackBuilder: " + builder + ")");
//        super.onPrepareSupportNavigateUpTaskStack(builder);
//    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        logV("onSupportNavigateUp()");
//        return super.onSupportNavigateUp();
//    }

//    @Override
//    public Intent getSupportParentActivityIntent() {
//        logV("getSupportParentActivityIntent()");
//        return super.getSupportParentActivityIntent();
//    }

//    @Override
//    public boolean supportShouldUpRecreateTask(Intent targetIntent) {
//        logV("supportShouldUpRecreateTask(targetIntent: " + targetIntent + ")");
//        return super.supportShouldUpRecreateTask(targetIntent);
//    }

//    @Override
//    public void supportNavigateUpTo(Intent upIntent) {
//        logV("supportNavigateUpTo(upIntent: " + upIntent + ")");
//        super.supportNavigateUpTo(upIntent);
//    }

//    @Nullable
//    @Override
//    public ActionBarDrawerToggle.Delegate getV7DrawerToggleDelegate() {
//        logV("getV7DrawerToggleDelegate()");
//        return super.getV7DrawerToggleDelegate();
//    }

    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        logV("onKeyShortcut(keyCode: " + keyCode + ", event: " + event + ")");
        return super.onKeyShortcut(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        logV("onKeyDown(keyCode: " + keyCode + ", event: " + event + ")");
        return super.onKeyDown(keyCode, event);
    }

//    @Override
//    public void onSupportContentChanged() {
//        logV("onSupportContentChanged()");
//        super.onSupportContentChanged();
//    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        logV("onCreateView(parent: " + parent + ", name: " + name + ", context: " + context + ", attrs: " + attrs);
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        logV("onCreateView(name: " + name + ", context: " + context + ", attrs: " + attrs + ")");
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        logV("onActivityResult(requestCode: " + requestCode + ", resultCode: " + resultCode + ", data: " + data + ")");
        super.onActivityResult(requestCode, resultCode, data);
    }

//    @Override
//    public void supportFinishAfterTransition() {
//        logV("supportFinishAfterTransition()");
//        super.supportFinishAfterTransition();
//    }

//    @Override
//    public void setEnterSharedElementCallback(SharedElementCallback callback) {
//        logV("setEnterSharedElementCallback(sharedElementCallback: " + callback + ")");
//        super.setEnterSharedElementCallback(callback);
//    }

//    @Override
//    public void setExitSharedElementCallback(SharedElementCallback listener) {
//        logV("setExitSharedElementCallback(sharedElementCallback: " + listener + ")");
//        super.setExitSharedElementCallback(listener);
//    }

//    @Override
//    public void supportPostponeEnterTransition() {
//        logV("supportPostponeEnterTransition()");
//        super.supportPostponeEnterTransition();
//    }

//    @Override
//    public void supportStartPostponedEnterTransition() {
//        logV("supportStartPostponedEnterTransition()");
//        super.supportStartPostponedEnterTransition();
//    }

    @Override
    public void onLowMemory() {
        logV("onLowMemory()");
        super.onLowMemory();
    }

    @Override
    protected void onPause() {
        logV("onPause()");
        super.onPause();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        logV("onNewIntent(intent: " + intent + ")");
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        logV("onResume()");
        super.onResume();
    }

//    @Override
//    protected void onResumeFragments() {
//        logV("onResumeFragments()");
//        super.onResumeFragments();
//    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        logV("onSaveInstanceState(outState: " + outState + ")");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        logV("onStart()");
        super.onStart();
    }

//    @Override
//    public Object onRetainCustomNonConfigurationInstance() {
//        logV("onRetainCustomNonConfigurationInstance()");
//        return super.onRetainCustomNonConfigurationInstance();
//    }

//    @Override
//    public Object getLastCustomNonConfigurationInstance() {
//        logV("getLastCustomNonConfigurationInstance()");
//        return super.getLastCustomNonConfigurationInstance();
//    }

    @Override
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        logV("dump(prefix: " + prefix + ", fileDescriptor: " + fd + ", printWriter: " + writer + ", args: " + args + ")");
        super.dump(prefix, fd, writer, args);
    }

//    @Override
//    public void onAttachFragment(Fragment fragment) {
//        logV("onAttachFragment(fragment: " + fragment + ")");
//        super.onAttachFragment(fragment);
//    }

//    @Override
//    public FragmentManager getSupportFragmentManager() {
//        logV("getSupportFragmentManager()");
//        return super.getSupportFragmentManager();
//    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        logV("startActivityForResult(intent: " + intent + ", requestCode: " + requestCode + ")");
        super.startActivityForResult(intent, requestCode);
    }

//    @Override
//    public void startActivityFromFragment(Fragment fragment, Intent intent, int requestCode) {
//        logV("startActivityFromFragment(fragment: " + fragment + ", intent: " + intent + ", requestCode: " + requestCode + ")");
//        super.startActivityFromFragment(fragment, intent, requestCode);
//    }

//    @Override
//    public LoaderManager getSupportLoaderManager() {
//        logV("getSupportLoaderManager()");
//        return super.getSupportLoaderManager();
//    }

    @Override
    public Intent getIntent() {
        logV("getIntent()");
        return super.getIntent();
    }

    @Override
    public void setIntent(Intent newIntent) {
        logV("setIntent(newIntent: " + newIntent + ")");
        super.setIntent(newIntent);
    }

    @Override
    public WindowManager getWindowManager() {
        logV("getWindowManager()");
        return super.getWindowManager();
    }

    @Override
    public Window getWindow() {
        logV("getWindow()");
        return super.getWindow();
    }

    @Override
    public android.app.LoaderManager getLoaderManager() {
        logV("getLoaderManager()");
        return super.getLoaderManager();
    }

    @Nullable
    @Override
    public View getCurrentFocus() {
        logV("getCurrentFocus()");
        return super.getCurrentFocus();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        logV("onCreate(savedInstanceState: " + savedInstanceState + ")");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        logV("onCreate(savedInstanceState: " + savedInstanceState + ", persistentState: " + persistentState + ")");

        if (savedInstanceState != null) {
            for(String s : savedInstanceState.keySet()) {
                logV("   +++ savedInstanceState --> " + s + ": " + savedInstanceState.get(s));
            }
        }

        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        logV("onRestoreInstanceState(savedInstanceState: " + savedInstanceState + ")");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        logV("onRestoreInstanceState(savedInstanceState: " + savedInstanceState + ", persistentState: " + persistentState + ")");
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        logV("onPostCreate(savedInstanceState: " + savedInstanceState + ")");
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        logV("onPostCreate(savedInstanceState: " + savedInstanceState + ", persistentState: " + persistentState + ")");
        super.onPostCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onRestart() {
        logV("onRestart()");
        super.onRestart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        logV("onSaveInstanceState(outState: " + outState + ", outPersistentState: " + outPersistentState + ")");
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onUserLeaveHint() {
        logV("onUserLeaveHint()");
        super.onUserLeaveHint();
    }

    @Override
    public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) {
        logV("onCreateThumbnail(outBitmap: " + outBitmap + ", canvas: " + canvas + ")");
        return super.onCreateThumbnail(outBitmap, canvas);
    }

    @Nullable
    @Override
    public CharSequence onCreateDescription() {
        logV("onCreateDescription()");
        return super.onCreateDescription();
    }

    @Override
    public void onProvideAssistData(Bundle data) {
        logV("onProvideAssistData(data: " + data + ")");
        super.onProvideAssistData(data);
    }

    @Override
    public void reportFullyDrawn() {
        logV("reportFullyDrawn()");
        super.reportFullyDrawn();
    }

    @Override
    public int getChangingConfigurations() {
        logV("getChangingConfigurations()");
        return super.getChangingConfigurations();
    }

    @Nullable
    @Override
    public Object getLastNonConfigurationInstance() {
        logV("getLastNonConfigurationInstance()");
        return super.getLastNonConfigurationInstance();
    }

    @Override
    public void onTrimMemory(int level) {
        logV("onTrimMemory(level: " + level + ")");
        super.onTrimMemory(level);
    }

    @Override
    public android.app.FragmentManager getFragmentManager() {
        logV("getFragmentManager()");
        return super.getFragmentManager();
    }

    @Override
    public void onAttachFragment(android.app.Fragment fragment) {
        logV("onAttachFragment(fragment: " + fragment + ")");
        super.onAttachFragment(fragment);
    }

    @Override
    public void startManagingCursor(Cursor c) {
        logV("startManagingCursor(cursor: " + c + ")");
        super.startManagingCursor(c);
    }

    @Override
    public void stopManagingCursor(Cursor c) {
        logV("stopManagingCursor(cursor: " + c + ")");
        super.stopManagingCursor(c);
    }

    @Override
    public View findViewById(int id) {
        logV("findViewById(id: " + id + ")");
        return super.findViewById(id);
    }

    @Nullable
    @Override
    public android.app.ActionBar getActionBar() {
        logV("getActionBar()");
        return super.getActionBar();
    }

    @Override
    public void setActionBar(android.widget.Toolbar toolbar) {
        logV("setActionBar(toolbar: " + toolbar + ")");
        super.setActionBar(toolbar);
    }

    @Override
    public TransitionManager getContentTransitionManager() {
        logV("getContentTransitionManager()");
        return super.getContentTransitionManager();
    }

    @Override
    public void setContentTransitionManager(TransitionManager tm) {
        logV("setContentTransitionManager(transitionManager: " + tm + ")");
        super.setContentTransitionManager(tm);
    }

    @Override
    public Scene getContentScene() {
        logV("getContentScene()");
        return super.getContentScene();
    }

    @Override
    public void setFinishOnTouchOutside(boolean finish) {
        logV("setFinishOnTouchOutside(finish: " + finish + ")");
        super.setFinishOnTouchOutside(finish);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        logV("onKeyLongPress(keyCode: " + keyCode + ", event: " + event + ")");
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        logV("onKeyUp(keyCode: " + keyCode + ", event: " + event + ")");
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        logV("onKeyMultiple(keyCode: " + keyCode + ", repeatCount: " + repeatCount + ", event: " + event + ")");
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        logV("onTouchEvent(event: " + event + ")");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        logV("onTrackballEvent(event: " + event + ")");
        return super.onTrackballEvent(event);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        logV("onGenericMotionEvent(event: " + event + ")");
        return super.onGenericMotionEvent(event);
    }

    @Override
    public void onUserInteraction() {
        logV("onUserInteraction()");
        super.onUserInteraction();
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        logV("onWindowAttributesChanged(params: " + params + ")");
        super.onWindowAttributesChanged(params);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        logV("onWindowFocusChanged(hasFocus: " + hasFocus + ")");
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onAttachedToWindow() {
        logV("onAttachedToWindow()");
        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        logV("onDetachedFromWindow()");
        super.onDetachedFromWindow();
    }

    @Override
    public boolean hasWindowFocus() {
        logV("hasWindowFocus()");
        return super.hasWindowFocus();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        logV("dispatchKeyEvent(event: " + event + ")");
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        logV("dispatchKeyShortcutEvent(event: " + event + ")");
        return super.dispatchKeyShortcutEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        logV("dispatchTouchEvent(event: " + ev + ")");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent ev) {
        logV("dispatchTrackballEvent(event: " + ev + ")");
        return super.dispatchTrackballEvent(ev);
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent ev) {
        logV("dispatchGenericMotionEvent(event: " + ev + ")");
        return super.dispatchGenericMotionEvent(ev);
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        logV("dispatchPopulateAccessibilityEvent(event: " + event + ")");
        return super.dispatchPopulateAccessibilityEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        logV("onCreateOptionsMenu(menu: " + menu + ")");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        logV("onPrepareOptionsMenu(menu: " + menu + ")");
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        logV("onOptionsItemSelected(menuItem: " + item + ")");
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigateUp() {
        logV("onNavigateUp()");
        return super.onNavigateUp();
    }

    @Override
    public boolean onNavigateUpFromChild(Activity child) {
        logV("onNavigateUpFromChild(childActivity: " + child + ")");
        return super.onNavigateUpFromChild(child);
    }

    @Override
    public void onCreateNavigateUpTaskStack(android.app.TaskStackBuilder builder) {
        logV("onCreateNavigateUpTaskStack(taskStackBuilder: " + builder + ")");
        super.onCreateNavigateUpTaskStack(builder);
    }

    @Override
    public void onPrepareNavigateUpTaskStack(android.app.TaskStackBuilder builder) {
        logV("onPrepareNavigateUpTaskStack(taskStackBuilder: " + builder + ")");
        super.onPrepareNavigateUpTaskStack(builder);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        logV("onOptionsMenuClosed(menu: " + menu + ")");
        super.onOptionsMenuClosed(menu);
    }

    @Override
    public void openOptionsMenu() {
        logV("openOptionsMenu()");
        super.openOptionsMenu();
    }

    @Override
    public void closeOptionsMenu() {
        logV("closeOptionsMenu()");
        super.closeOptionsMenu();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        logV("onCreateContextMenu(menu: " + menu + ", view: " + v + ", menuInfo: " + menuInfo + ")");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public void registerForContextMenu(View view) {
        logV("registerForContextMenu(view: " + view + ")");
        super.registerForContextMenu(view);
    }

    @Override
    public void unregisterForContextMenu(View view) {
        logV("unregisterForContextMenu(view: " + view);
        super.unregisterForContextMenu(view);
    }

    @Override
    public void openContextMenu(View view) {
        logV("openContextMenu(view: " + view + ")");
        super.openContextMenu(view);
    }

    @Override
    public void closeContextMenu() {
        logV("closeContextMenu()");
        super.closeContextMenu();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        logV("onContextItemSelected(item: " + item + ")");
        return super.onContextItemSelected(item);
    }

    @Override
    public void onContextMenuClosed(Menu menu) {
        logV("onContextMenuClosed(menu: " + menu + ")");
        super.onContextMenuClosed(menu);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        logV("onCreateDialog(id: " + id + ")");
        return super.onCreateDialog(id);
    }

    @Nullable
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        logV("onCreateDialog(id: " + id + ", bundleArgs: " + args + ")");
        return super.onCreateDialog(id, args);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        logV("onPrepareDialog(id: " + id + ", dialog: " + dialog + ")");
        super.onPrepareDialog(id, dialog);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
        logV("onPrepareDialog(id: " + id + ", dialog: " + dialog + ", bundleArgs: " + args + ")");
        super.onPrepareDialog(id, dialog, args);
    }

    @Override
    public boolean onSearchRequested() {
        logV("onSearchRequested()");
        return super.onSearchRequested();
    }

    @Override
    public void startSearch(String initialQuery, boolean selectInitialQuery, Bundle appSearchData, boolean globalSearch) {
        logV("startSearch(initialQuery: " + initialQuery + ", selectedInitialQuery: " + selectInitialQuery + ", appSearchDataBundle: " + appSearchData + ", globalSearch: " + globalSearch + ")");
        super.startSearch(initialQuery, selectInitialQuery, appSearchData, globalSearch);
    }

    @Override
    public void triggerSearch(String query, Bundle appSearchData) {
        logV("triggerSearch(query: " + query + ", appSearchDataBundle: " + appSearchData + ")");
        super.triggerSearch(query, appSearchData);
    }

    @Override
    public void takeKeyEvents(boolean get) {
        logV("takeKeyEvents(get: " + get + ")");
        super.takeKeyEvents(get);
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        logV("getLayoutInflater()");
        return super.getLayoutInflater();
    }

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        logV("onApplyThemeResource(theme: " + theme + ", resID: " + resid + ", first: " + first + ")");
        super.onApplyThemeResource(theme, resid, first);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        logV("startActivityForResult(" + intent + ", requestCode: " + requestCode + ", optionsBundle: " + options + ")");
        super.startActivityForResult(intent, requestCode, options);
    }

    @Override
    public void startIntentSenderForResult(IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws IntentSender.SendIntentException {
        logV("startIntentSenderForResult(intentSender: " + intent + ", requestCode: " + requestCode + ", fillInIntent: " + fillInIntent + ", flagsMask: " + flagsMask + ", flagsValues: " + flagsValues + ", extraFlags: " + extraFlags + ")");
        super.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags);
    }

    @Override
    public void startIntentSenderForResult(IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws IntentSender.SendIntentException {
        logV("startIntentSenderForResult(intentSender: " + intent + ", requestCode: " + requestCode + ", fillInIntent: " + fillInIntent + ", flagsMask: " + flagsMask + ", flagsValues: " + flagsValues + ", extraFlags: " + extraFlags + ", optionsBundle: " + options + ")");
        super.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
    }

    @Override
    public void startActivity(Intent intent) {
        logV("startActivity(intent: " + intent + ")");
        super.startActivity(intent);
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        logV("startActivity(intent: " + intent + ", optionsBundle: " + options + ")");
        super.startActivity(intent, options);
    }

    @Override
    public void startActivities(Intent[] intents) {
        logV("startActivities(intents: " + intents + ")");
        super.startActivities(intents);
    }

    @Override
    public void startActivities(Intent[] intents, Bundle options) {
        logV("startActivities(intents: " + intents + ", optionsBundle: " + options + ")");
        super.startActivities(intents, options);
    }

    @Override
    public void startIntentSender(IntentSender intent, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws IntentSender.SendIntentException {
        logV("startIntentSender(intentSender: " + intent + ", fillInIntent: " + fillInIntent + ", flagsMask: " + flagsMask + ", flagsValues: " + flagsValues + ", extraFlags: " + extraFlags + ")");
        super.startIntentSender(intent, fillInIntent, flagsMask, flagsValues, extraFlags);
    }

    @Override
    public void startIntentSender(IntentSender intent, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws IntentSender.SendIntentException {
        logV("startIntentSender(intentSender: " + intent + ", fillInIntent: " + fillInIntent + ", flagsMask: " + flagsMask + ", flagsValues: " + flagsValues + ", extraFlags: " + extraFlags + ", optionsBundle: " + options + ")");
        super.startIntentSender(intent, fillInIntent, flagsMask, flagsValues, extraFlags, options);
    }

    @Override
    public boolean startActivityIfNeeded(Intent intent, int requestCode) {
        logV("startActivityIfNeeded(intent: " + intent + ", requestCode: " + requestCode + ")");
        return super.startActivityIfNeeded(intent, requestCode);
    }

    @Override
    public boolean startActivityIfNeeded(Intent intent, int requestCode, Bundle options) {
        logV("startActivityIfNeeded(intent: " + intent + ", requestCode: " + requestCode + ", optionsBundle: " + options + ")");
        return super.startActivityIfNeeded(intent, requestCode, options);
    }

    @Override
    public boolean startNextMatchingActivity(Intent intent) {
        logV("startNextMatchingActivity(intent: " + intent + ")");
        return super.startNextMatchingActivity(intent);
    }

    @Override
    public boolean startNextMatchingActivity(Intent intent, Bundle options) {
        logV("startNextMatchingActivity(intent: " + intent + ", optionsBundle: " + options + ")");
        return super.startNextMatchingActivity(intent, options);
    }

    @Override
    public void startActivityFromChild(Activity child, Intent intent, int requestCode) {
        logV("startActivityFromChild(childActivity: " + child + ", intent: " + intent + ", requestCode: " + requestCode + ")");
        super.startActivityFromChild(child, intent, requestCode);
    }

    @Override
    public void startActivityFromChild(Activity child, Intent intent, int requestCode, Bundle options) {
        logV("startActivityFromChild(childActivity: " + child + ", intent: " + intent + ", requestCode: " + requestCode + ", optionsBundle: " + options + ")");
        super.startActivityFromChild(child, intent, requestCode, options);
    }

    @Override
    public void startActivityFromFragment(android.app.Fragment fragment, Intent intent, int requestCode) {
        logV("startActivityFromFragment(fragment: " + fragment + ", intent: " + intent + ", requestCode: " + requestCode + ")");
        super.startActivityFromFragment(fragment, intent, requestCode);
    }

    @Override
    public void startActivityFromFragment(android.app.Fragment fragment, Intent intent, int requestCode, Bundle options) {
        logV("startActivityFromFragment(fragment: " + fragment + ", intent: " + intent + ", requestCode: " + requestCode + ", optionsBundle: " + options + ")");
        super.startActivityFromFragment(fragment, intent, requestCode, options);
    }

    @Override
    public void startIntentSenderFromChild(Activity child, IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws IntentSender.SendIntentException {
        logV("startIntentSenderFromChild(childActivity: " + child + ", intent: " + intent + ", requestCode: " + requestCode + ", fillInIntent: " + fillInIntent + ", flagsMask: " + flagsMask + ", flagsValues: " + flagsValues + ", extraFlags: " + extraFlags + ")");
        super.startIntentSenderFromChild(child, intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags);
    }

    @Override
    public void startIntentSenderFromChild(Activity child, IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws IntentSender.SendIntentException {
        logV("startIntentSenderFromChild(childActivity: " + child + ", intent: " + intent + ", requestCode: " + requestCode + ", fillInIntent: " + fillInIntent + ", flagsMask: " + flagsMask + ", flagsValues: " + flagsValues + ", extraFlags: " + extraFlags + ", optionsBundle: " + options + ")");
        super.startIntentSenderFromChild(child, intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        logV("overridePendingTransition(enterAnim: " + enterAnim + ", exitAnim: " + exitAnim + ")");
        super.overridePendingTransition(enterAnim, exitAnim);
    }

    @Nullable
    @Override
    public String getCallingPackage() {
        logV("getCallingPackage()");
        return super.getCallingPackage();
    }

    @Nullable
    @Override
    public ComponentName getCallingActivity() {
        logV("getCallingActivity()");
        return super.getCallingActivity();
    }

    @Override
    public void setVisible(boolean visible) {
        logV("setVisible(visible: " + visible + ")");
        super.setVisible(visible);
    }

    @Override
    public boolean isFinishing() {
        logV("isFinishing()");
        return super.isFinishing();
    }

    @Override
    public boolean isDestroyed() {
        logV("isDestroyed()");
        return super.isDestroyed();
    }

    @Override
    public boolean isChangingConfigurations() {
        logV("isChangingConfigurations()");
        return super.isChangingConfigurations();
    }

    @Override
    public void recreate() {
        logV("recreate()");
        super.recreate();
    }

    @Override
    public void finish() {
        logV("finish()");
        super.finish();
    }

    @Override
    public void finishAffinity() {
        logV("finishAffinity()");
        super.finishAffinity();
    }

    @Override
    public void finishFromChild(Activity child) {
        logV("finishFromChild(childActivity: " + child + ")");
        super.finishFromChild(child);
    }

    @Override
    public void finishAfterTransition() {
        logV("finishAfterTransition()");
        super.finishAfterTransition();
    }

    @Override
    public void finishActivity(int requestCode) {
        logV("finishActivity(requestCode: " + requestCode + ")");
        super.finishActivity(requestCode);
    }

    @Override
    public void finishActivityFromChild(Activity child, int requestCode) {
        logV("finishActivityFromChild(childActivity: " + child + ", requestCode: " + requestCode + ")");
        super.finishActivityFromChild(child, requestCode);
    }

    @Override
    public void finishAndRemoveTask() {
        logV("finishAndRemoveTask()");
        super.finishAndRemoveTask();
    }

    @Override
    public boolean releaseInstance() {
        logV("releaseInstance()");
        return super.releaseInstance();
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        logV("onActivityReenter(requestCode: " + resultCode + ", dataIntent: " + data + ")");
        super.onActivityReenter(resultCode, data);
    }

    @Override
    public PendingIntent createPendingResult(int requestCode, Intent data, int flags) {
        logV("createPendingResult(requestCode: " + requestCode + ", dataIntent: " + data + ", flags: " + flags + ")");
        return super.createPendingResult(requestCode, data, flags);
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        logV("setRequestedOrientation(requestedOrientation: " + requestedOrientation + ")");
        super.setRequestedOrientation(requestedOrientation);
    }

    @Override
    public int getRequestedOrientation() {
        logV("getRequestedOrientation()");
        return super.getRequestedOrientation();
    }

    @Override
    public int getTaskId() {
        logV("getTaskId()");
        return super.getTaskId();
    }

    @Override
    public boolean isTaskRoot() {
        logV("isTaskRoot()");
        return super.isTaskRoot();
    }

    @Override
    public boolean moveTaskToBack(boolean nonRoot) {
        logV("moveTaskToBack(nonRoot: " + nonRoot + ")");
        return super.moveTaskToBack(nonRoot);
    }

    @NonNull
    @Override
    public String getLocalClassName() {
        logV("getLocalClassName()");
        return super.getLocalClassName();
    }

    @Override
    public ComponentName getComponentName() {
        logV("getComponentName()");
        return super.getComponentName();
    }

    @Override
    public SharedPreferences getPreferences(int mode) {
        logV("getPreferences(mode: " + mode + ")");
        return super.getPreferences(mode);
    }

    @Override
    public Object getSystemService(String name) {
        logV("getSystemService(name: " + name + ")");
        return super.getSystemService(name);
    }

    @Override
    public void setTitle(CharSequence title) {
        logV("setTitle(title: " + title + ")");
        super.setTitle(title);
    }

    @Override
    public void setTitle(int titleId) {
        logV("setTitle(titleId: " + titleId + ")");
        super.setTitle(titleId);
    }

    @Override
    public void setTitleColor(int textColor) {
        logV("setTitleColor(textColor: " + textColor + ")");
        super.setTitleColor(textColor);
    }

    @Override
    protected void onChildTitleChanged(Activity childActivity, CharSequence title) {
        logV("onChildTitleChanged(childActivity: " + childActivity + ", title: " + title + ")");
        super.onChildTitleChanged(childActivity, title);
    }

    @Override
    public void setTaskDescription(ActivityManager.TaskDescription taskDescription) {
        logV("setTaskDescription(taskDescription: " + taskDescription + ")");
        super.setTaskDescription(taskDescription);
    }

    @Override
    public boolean isImmersive() {
        logV("isImmersive()");
        return super.isImmersive();
    }

    @Override
    public boolean requestVisibleBehind(boolean visible) {
        logV("requestVisibleBehind(visible: " + visible + ")");
        return super.requestVisibleBehind(visible);
    }

    @Override
    public void onVisibleBehindCanceled() {
        logV("onVisibleBehindCanceled()");
        super.onVisibleBehindCanceled();
    }

    @Override
    public void onEnterAnimationComplete() {
        logV("onEnterAnimationComplete()");
        super.onEnterAnimationComplete();
    }

    @Override
    public void setImmersive(boolean i) {
        logV("setImmersive(i: " + i + ")");
        super.setImmersive(i);
    }

    @Nullable
    @Override
    public android.view.ActionMode startActionMode(android.view.ActionMode.Callback callback) {
        logV("startActionMode(callback: " + callback + ")");
        return super.startActionMode(callback);
    }

    @Nullable
    @Override
    public android.view.ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback) {
        logV("onWindowStartingActionMode(callback: " + callback + ")");
        return super.onWindowStartingActionMode(callback);
    }

    @Override
    public void onActionModeStarted(android.view.ActionMode mode) {
        logV("onActionModeStarted(mode: " + mode + ")");
        super.onActionModeStarted(mode);
    }

    @Override
    public void onActionModeFinished(android.view.ActionMode mode) {
        logV("onActionModeFinished(mode: " + mode + ")");
        super.onActionModeFinished(mode);
    }

    @Override
    public boolean shouldUpRecreateTask(Intent targetIntent) {
        logV("shouldUpRecreateTask(targetIntent: " + targetIntent + ")");
        return super.shouldUpRecreateTask(targetIntent);
    }

    @Override
    public boolean navigateUpTo(Intent upIntent) {
        logV("navigateUpTo(upIntent: " + upIntent + ")");
        return super.navigateUpTo(upIntent);
    }

    @Override
    public boolean navigateUpToFromChild(Activity child, Intent upIntent) {
        logV("navigateUpToFromChild(childActivity: " + child + ", upIntent: " + upIntent + ")");
        return super.navigateUpToFromChild(child, upIntent);
    }

    @Nullable
    @Override
    public Intent getParentActivityIntent() {
        logV("getParentActivityIntent()");
        return super.getParentActivityIntent();
    }

    @Override
    public void setEnterSharedElementCallback(android.app.SharedElementCallback callback) {
        logV("setEnterSharedElementCallback(callback: " + callback + ")");
        super.setEnterSharedElementCallback(callback);
    }

    @Override
    public void setExitSharedElementCallback(android.app.SharedElementCallback callback) {
        logV("setExitSharedElementCallback(callback: " + callback + ")");
        super.setExitSharedElementCallback(callback);
    }

    @Override
    public void postponeEnterTransition() {
        logV("postponeEnterTransition()");
        super.postponeEnterTransition();
    }

    @Override
    public void startPostponedEnterTransition() {
        logV("startPostponedEnterTransition()");
        super.startPostponedEnterTransition();
    }

    @Override
    public void startLockTask() {
        logV("startLockTask()");
        super.startLockTask();
    }

    @Override
    public void stopLockTask() {
        logV("stopLockTask()");
        super.stopLockTask();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        logV("attachBaseContext(newBaseContext: " + newBase + ")");
        super.attachBaseContext(newBase);
    }

    @Override
    public void applyOverrideConfiguration(Configuration overrideConfiguration) {
        logV("applyOverrideConfiguration(overrideConfiguration: " + overrideConfiguration);
        super.applyOverrideConfiguration(overrideConfiguration);
    }

    @Override
    public Resources getResources() {
        logV("getResources()");
        return super.getResources();
    }

    @Override
    public void setTheme(int resid) {
        logV("setTheme(resID: " + resid + ")");
        super.setTheme(resid);
    }

    @Override
    public Resources.Theme getTheme() {
        logV("getTheme()");
        return super.getTheme();
    }

    @Override
    public Context getBaseContext() {
        logV("getBaseContext()");
        return super.getBaseContext();
    }

    @Override
    public AssetManager getAssets() {
        logV("getAssets()");
        return super.getAssets();
    }

    @Override
    public PackageManager getPackageManager() {
        logV("getPackageManager()");
        return super.getPackageManager();
    }

    @Override
    public ContentResolver getContentResolver() {
        logV("getContentResolver()");
        return super.getContentResolver();
    }

    @Override
    public Looper getMainLooper() {
        logV("getMainLooper()");
        return super.getMainLooper();
    }

    @Override
    public Context getApplicationContext() {
        logV("getApplicationContext()");
        return super.getApplicationContext();
    }

    @Override
    public ClassLoader getClassLoader() {
        logV("getClassLoader()");
        return super.getClassLoader();
    }

    @Override
    public String getPackageName() {
        logV("getPackageName()");
        return super.getPackageName();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        logV("getApplicationInfo()");
        return super.getApplicationInfo();
    }

    @Override
    public String getPackageResourcePath() {
        logV("getPackageResourcePath()");
        return super.getPackageResourcePath();
    }

    @Override
    public String getPackageCodePath() {
        logV("getPackageCodePath()");
        return super.getPackageCodePath();
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        logV("getSharedPreferences(name: " + name + ", mode: " + mode + ")");
        return super.getSharedPreferences(name, mode);
    }

    @Override
    public FileInputStream openFileInput(String name) throws FileNotFoundException {
        logV("openFileInput(name: " + name + ")");
        return super.openFileInput(name);
    }

    @Override
    public FileOutputStream openFileOutput(String name, int mode) throws FileNotFoundException {
        logV("openFileOutput(name: " + name + ", mode: " + mode + ")");
        return super.openFileOutput(name, mode);
    }

    @Override
    public boolean deleteFile(String name) {
        logV("deleteFile(name: " + name + ")");
        return super.deleteFile(name);
    }

    @Override
    public File getFileStreamPath(String name) {
        logV("getFileStreamPath(name: " + name + ")");
        return super.getFileStreamPath(name);
    }

    @Override
    public String[] fileList() {
        logV("fileList()");
        return super.fileList();
    }

    @Override
    public File getFilesDir() {
        logV("getFilesDir()");
        return super.getFilesDir();
    }

    @Override
    public File getNoBackupFilesDir() {
        logV("getNoBackupFilesDir()");
        return super.getNoBackupFilesDir();
    }

    @Override
    public File getExternalFilesDir(String type) {
        logV("getExternalFilesDir(type: " + type + ")");
        return super.getExternalFilesDir(type);
    }

    @Override
    public File[] getExternalFilesDirs(String type) {
        logV("getExternalFilesDirs(type: " + type + ")");
        return super.getExternalFilesDirs(type);
    }

    @Override
    public File getObbDir() {
        logV("getObbDir()");
        return super.getObbDir();
    }

    @Override
    public File[] getObbDirs() {
        logV("getObbDirs()");
        return super.getObbDirs();
    }

    @Override
    public File getCacheDir() {
        logV("getCacheDir()");
        return super.getCacheDir();
    }

    @Override
    public File getCodeCacheDir() {
        logV("getCodeCacheDir()");
        return super.getCodeCacheDir();
    }

    @Override
    public File getExternalCacheDir() {
        logV("getExternalCacheDir()");
        return super.getExternalCacheDir();
    }

    @Override
    public File[] getExternalCacheDirs() {
        logV("getExternalCacheDirs()");
        return super.getExternalCacheDirs();
    }

    @Override
    public File[] getExternalMediaDirs() {
        logV("getExternalMediaDirs()");
        return super.getExternalMediaDirs();
    }

    @Override
    public File getDir(String name, int mode) {
        logV("getDir(name: " + name + ", mode: " + mode + ")");
        return super.getDir(name, mode);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        logV("openOrCreateDatabase(name: " + name + ", mode: " + mode + ", factory: " + factory + ")");
        return super.openOrCreateDatabase(name, mode, factory);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        logV("openOrCreateDatabase(name: " + name + ", mode: " + mode + ", factory: " + factory + ", errorHandler: " + errorHandler + ")");
        return super.openOrCreateDatabase(name, mode, factory, errorHandler);
    }

    @Override
    public boolean deleteDatabase(String name) {
        logV("deleteDatabase(name: " + name + ")");
        return super.deleteDatabase(name);
    }

    @Override
    public File getDatabasePath(String name) {
        logV("getDatabasePath(name: " + name + ")");
        return super.getDatabasePath(name);
    }

    @Override
    public String[] databaseList() {
        logV("databaseList()");
        return super.databaseList();
    }

    @Override
    public Drawable getWallpaper() {
        logV("getWallpaper()");
        return super.getWallpaper();
    }

    @Override
    public Drawable peekWallpaper() {
        logV("peekWallpaper()");
        return super.peekWallpaper();
    }

    @Override
    public int getWallpaperDesiredMinimumWidth() {
        logV("getWallpaperDesiredMinimumWidth()");
        return super.getWallpaperDesiredMinimumWidth();
    }

    @Override
    public int getWallpaperDesiredMinimumHeight() {
        logV("getWallpaperDesiredMinimumHeight()");
        return super.getWallpaperDesiredMinimumHeight();
    }

    @Override
    public void setWallpaper(Bitmap bitmap) throws IOException {
        logV("setWallpaper(bitmap: " + bitmap + ")");
        super.setWallpaper(bitmap);
    }

    @Override
    public void setWallpaper(InputStream data) throws IOException {
        logV("setWallpaper(data: " + data + ")");
        super.setWallpaper(data);
    }

    @Override
    public void clearWallpaper() throws IOException {
        logV("clearWallpaper()");
        super.clearWallpaper();
    }

    @Override
    public void sendBroadcast(Intent intent) {
        logV("sendBroadcast(intent: " + intent + ")");
        super.sendBroadcast(intent);
    }

    @Override
    public void sendBroadcast(Intent intent, String receiverPermission) {
        logV("sendBroadcast(intent: " + intent + ", receiverPermission: " + receiverPermission + ")");
        super.sendBroadcast(intent, receiverPermission);
    }

    @Override
    public void sendOrderedBroadcast(Intent intent, String receiverPermission) {
        logV("sendOrderedBroadcast(intent: " + intent + ", receiverPermission: " + receiverPermission + ")");
        super.sendOrderedBroadcast(intent, receiverPermission);
    }

    @Override
    public void sendOrderedBroadcast(Intent intent, String receiverPermission, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
        logV("sendOrderedBroadcast(intent: " + intent + ", receiverPermission: " + receiverPermission + ", resultReceiver: " + resultReceiver + ", scheduler: " + scheduler + ", initialCode: " + initialCode + ", initialData: " + initialData + ", initialExtras: " + initialExtras + ")");
        super.sendOrderedBroadcast(intent, receiverPermission, resultReceiver, scheduler, initialCode, initialData, initialExtras);
    }

    @Override
    public void sendBroadcastAsUser(Intent intent, UserHandle user) {
        logV("sendBroadcastAsUser(intent: " + intent + ", userHandle: " + user + ")");
        super.sendBroadcastAsUser(intent, user);
    }

    @Override
    public void sendBroadcastAsUser(Intent intent, UserHandle user, String receiverPermission) {
        logV("sendBroadcastAsUser(intent: " + intent + ", userHandle: " + user + ", receiverPermission: " + receiverPermission + ")");
        super.sendBroadcastAsUser(intent, user, receiverPermission);
    }

    @Override
    public void sendOrderedBroadcastAsUser(Intent intent, UserHandle user, String receiverPermission, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
        logV("sendOrderedBroadcastAsUser(intent: " + intent + ", userHandle: " + user + ", receiverPermission: " + receiverPermission + ", broadcastResultReceiver: " + resultReceiver + ", scheduler: " + scheduler + ", initialCode: " + initialCode + ", initialData: " + initialData + ", initialExtras: " + initialExtras + ")");
        super.sendOrderedBroadcastAsUser(intent, user, receiverPermission, resultReceiver, scheduler, initialCode, initialData, initialExtras);
    }

    @Override
    public void sendStickyBroadcast(Intent intent) {
        logV("sendStickyBroadcast(intent: " + intent + ")");
        super.sendStickyBroadcast(intent);
    }

    @Override
    public void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
        logV("sendStickyOrderedBroadcast(intent: " + intent + ", broadcastResultReceiver: " + resultReceiver + ", scheduler: " + scheduler + ", initialCode: " + initialCode + ", initialData: " + initialData + ", initialExtras: " + initialExtras + ")");
        super.sendStickyOrderedBroadcast(intent, resultReceiver, scheduler, initialCode, initialData, initialExtras);
    }

    @Override
    public void removeStickyBroadcast(Intent intent) {
        logV("removeStickyBroadcast(intent: " + intent + ")");
        super.removeStickyBroadcast(intent);
    }

    @Override
    public void sendStickyBroadcastAsUser(Intent intent, UserHandle user) {
        logV("sendStickyBroadcastAsUser(intent: " + intent + ", userHandle: " + user + ")");
        super.sendStickyBroadcastAsUser(intent, user);
    }

    @Override
    public void sendStickyOrderedBroadcastAsUser(Intent intent, UserHandle user, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
        logV("sendStickyOrderedBroadcastAsUser(intent: " + intent + ", userHandle: " + user + ", broadcastResultReceiver: " + resultReceiver + ", scheduler: " + scheduler + ", initialCode: " + initialCode + ", initialData: " + initialData + ", initialExtras: " + initialExtras + ")");
        super.sendStickyOrderedBroadcastAsUser(intent, user, resultReceiver, scheduler, initialCode, initialData, initialExtras);
    }

    @Override
    public void removeStickyBroadcastAsUser(Intent intent, UserHandle user) {
        logV("removeStickyBroadcastAsUser(intent: " + intent + ", userHandle: " + user  + ")");
        super.removeStickyBroadcastAsUser(intent, user);
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        logV("registerReceiver(broadcastReceiver: " + receiver + ", intentFiler: " + filter);
        return super.registerReceiver(receiver, filter);
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, String broadcastPermission, Handler scheduler) {
        logV("registerReceiver(broadcastReceiver: " + receiver + ", intentFilter: " + filter + ", broadcastPermission: " + broadcastPermission + ", scheduler: " + scheduler + ")");
        return super.registerReceiver(receiver, filter, broadcastPermission, scheduler);
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        logV("unregisterReceiver(receiver: " + receiver + ")");
        super.unregisterReceiver(receiver);
    }

    @Override
    public ComponentName startService(Intent service) {
        logV("startService(intent: " + service + ")");
        return super.startService(service);
    }

    @Override
    public boolean stopService(Intent name) {
        logV("stopService(intent: " + name + ")");
        return super.stopService(name);
    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        logV("bindService(intent: " + service + ", serviceConnection: " + conn + ", flags: " + flags + ")");
        return super.bindService(service, conn, flags);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        logV("unbindService(serviceConnection: " + conn + ")");
        super.unbindService(conn);
    }

    @Override
    public boolean startInstrumentation(ComponentName className, String profileFile, Bundle arguments) {
        logV("startInstrumentation(componentName: " + className + ", profileFile: " + profileFile + ", argumentBundle: " + arguments + ")");
        return super.startInstrumentation(className, profileFile, arguments);
    }

    @Override
    public int checkPermission(String permission, int pid, int uid) {
        logV("checkPermission(permission: " + permission + ", pid: " + pid + ", uid: " + uid + ")");
        return super.checkPermission(permission, pid, uid);
    }

    @Override
    public int checkCallingPermission(String permission) {
        logV("checkCallingPermission(permission: " + permission + ")");
        return super.checkCallingPermission(permission);
    }

    @Override
    public int checkCallingOrSelfPermission(String permission) {
        logV("checkCallingOrSelfPermission(permission: " + permission + ")");
        return super.checkCallingOrSelfPermission(permission);
    }

    @Override
    public void enforcePermission(String permission, int pid, int uid, String message) {
        logV("enforcePermission(permission: " + permission + ", pid: " + pid + ", uid: " + uid + ", message: " + message + ")");
        super.enforcePermission(permission, pid, uid, message);
    }

    @Override
    public void enforceCallingPermission(String permission, String message) {
        logV("enforceCallingPermission(permission: " + permission + ", message: " + message + ")");
        super.enforceCallingPermission(permission, message);
    }

    @Override
    public void enforceCallingOrSelfPermission(String permission, String message) {
        logV("enforceCallingOrSelfPermission(permission: " + permission + ", message: " + message + ")");
        super.enforceCallingOrSelfPermission(permission, message);
    }

    @Override
    public void grantUriPermission(String toPackage, Uri uri, int modeFlags) {
        logV("grantUriPermission(toPackage: " + toPackage + ", uri: " + uri + ", modeFlags: " + modeFlags + ")");
        super.grantUriPermission(toPackage, uri, modeFlags);
    }

    @Override
    public void revokeUriPermission(Uri uri, int modeFlags) {
        logV("revokeUriPermission(uri: " + uri + ", modeFlags: " + modeFlags + ")");
        super.revokeUriPermission(uri, modeFlags);
    }

    @Override
    public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags) {
        logV("checkUriPermission(uri: " + uri + ", pid: " + pid + ", uid: " + uid + ", modeFlags: " + modeFlags + ")");
        return super.checkUriPermission(uri, pid, uid, modeFlags);
    }

    @Override
    public int checkCallingUriPermission(Uri uri, int modeFlags) {
        logV("checkCallingUriPermission(uri: " + uri + ", modeFlags: " + modeFlags + ")");
        return super.checkCallingUriPermission(uri, modeFlags);
    }

    @Override
    public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags) {
        logV("checkCallingOrSelfUriPermission(uri: " + uri + ", modeFlags: " + modeFlags + ")");
        return super.checkCallingOrSelfUriPermission(uri, modeFlags);
    }

    @Override
    public int checkUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid, int modeFlags) {
        logV("checkUriPermission(uri: " + uri + ", readPermission: " + readPermission + ", writePermission: " + writePermission + ", pid: " + pid + ", uid: " + uid + ", modeFlags: " + modeFlags + ")");
        return super.checkUriPermission(uri, readPermission, writePermission, pid, uid, modeFlags);
    }

    @Override
    public void enforceUriPermission(Uri uri, int pid, int uid, int modeFlags, String message) {
        logV("enforceUriPermission(uri: " + uri + ", pid: " + pid + ", uid: " + uid + ", modeFlags: " + modeFlags + ", message: " + message + ")");
        super.enforceUriPermission(uri, pid, uid, modeFlags, message);
    }

    @Override
    public void enforceCallingUriPermission(Uri uri, int modeFlags, String message) {
        logV("enforceCallingUriPermission(uri: " + uri + ", modeFlags: " + modeFlags + ", message: " + message + ")");
        super.enforceCallingUriPermission(uri, modeFlags, message);
    }

    @Override
    public void enforceCallingOrSelfUriPermission(Uri uri, int modeFlags, String message) {
        logV("enforceCallingOrSelfUriPermission(uri: " + uri + ", modeFlags: " + modeFlags + ", message: " + message + ")");
        super.enforceCallingOrSelfUriPermission(uri, modeFlags, message);
    }

    @Override
    public void enforceUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid, int modeFlags, String message) {
        logV("enforceUriPermission(uri: " + uri + ", readPermission: " + readPermission + ", writePermission: " + writePermission + ", pid: " + pid + ", uid: " + uid + ", modeFlags: " + modeFlags + ", message: " + message + ")");
        super.enforceUriPermission(uri, readPermission, writePermission, pid, uid, modeFlags, message);
    }

    @Override
    public Context createPackageContext(String packageName, int flags) throws PackageManager.NameNotFoundException {
        logV("createPackageContext(packageName: " + packageName + ", flags: " + flags + ")");
        return super.createPackageContext(packageName, flags);
    }

    @Override
    public Context createConfigurationContext(Configuration overrideConfiguration) {
        logV("createConfigurationContext(overrideConfiguration: " + overrideConfiguration + ")");
        return super.createConfigurationContext(overrideConfiguration);
    }

    @Override
    public Context createDisplayContext(Display display) {
        logV("createDisplayContext(display: " + display + ")");
        return super.createDisplayContext(display);
    }

    @Override
    public boolean isRestricted() {
        logV("isRestricted()");
        return super.isRestricted();
    }

    @Override
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        logV("registerComponentCallbacks(componentCallbacks: " + callback);
        super.registerComponentCallbacks(callback);
    }

    @Override
    public void unregisterComponentCallbacks(ComponentCallbacks callback) {
        logV("unregisterComponentCallbacks(componentCallbacks: " + callback);
        super.unregisterComponentCallbacks(callback);
    }
}
