package com.iamantony.todo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.Configuration;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "ToDo";

    // onCreate() will be called when the application is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");

        setContentView(R.layout.activity_main);
    }

    // onConfigurationChanged() will be called if there will be runtime changes is Android.
    // Such as language change, location, hardware, etc.
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConfigurationChanged()");

        if (newConfig.keyboardHidden == Configuration.HARDKEYBOARDHIDDEN_YES)
        {
            Log.i(TAG, "keyboard hidden");
        }
    }

    // onLowMemory() will be called when system will be low on memory and will ask app
    // to release unnecessary resources.
    @Override
    public final void onLowMemory() {
        super.onLowMemory();
        Log.i(TAG, "onLowMemory()");
    }

    // onTrimMemory() called when the run time determines that the current application
    // should attempt to trim its memory overhead – typically when it moves to the background.
    // It includes a level parameter that provides the context around the request.
    @Override
    public final void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.i(TAG, "onTrimMemory(): " + level);
    }
}
