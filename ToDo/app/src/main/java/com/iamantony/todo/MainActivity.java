package com.iamantony.todo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.Configuration;
import android.view.View;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.EditText;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "ToDo";
    private ListView m_listView = null;
    private EditText m_editText = null;

    // onCreate() will be called when the application is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");

        setContentView(R.layout.activity_main);

        m_listView = (ListView)findViewById(R.id.lvTodo);
        m_editText = (EditText) findViewById(R.id.etTodo);

        final ArrayList<String> todoItems = new ArrayList<String>();
        final ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, todoItems);

        m_listView.setAdapter(arrAdapter);

        m_editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) ||
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        todoItems.add(0, m_editText.getText().toString());
                        arrAdapter.notifyDataSetChanged();
                        m_editText.setText("");
                        return true;
                    }
                }
                return false;
            }
        });
    }

    // onConfigurationChanged() will be called if there will be runtime changes
    // in Android (such as language change, location, hardware, etc.)
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

    // onLowMemory() will be called when system will be low on memory and will
    // ask app to release unnecessary resources.
    @Override
    public final void onLowMemory() {
        super.onLowMemory();
        Log.i(TAG, "onLowMemory()");
    }

    // onTrimMemory() called when the run time determines that the current
    // application should attempt to trim its memory overhead – typically
    // when it moves to the background. It includes a level parameter that
    // provides the context around the request.
    @Override
    public final void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.i(TAG, "onTrimMemory(): " + level);
    }

    // Called after onCreate has finished, use to restore UI state
    // Restore UI state from the savedInstanceState.
    // This bundle has also been passed to onCreate.
    // Will only be called if the Activity has been
    // killed by the system since it was last visible.
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState()");
    }

    // Called before subsequent visible lifetimes for an Activity process.
    // Load changes knowing that the Activity has already
    // been visible within this process.
    @Override
    public void onRestart(){
        super.onRestart();
        Log.i(TAG, "onRestart()");
    }

    // Called at the start of the visible lifetime.
    // Apply any required UI change now that the Activity is visible.
    @Override
    public void onStart(){
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    // Called at the start of the active lifetime.
    // Resume any paused UI updates, threads, or processes required
    // by the Activity but suspended when it was inactive.
    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    // Called to save UI state changes at the end of the active lifecycle.
    // Save UI state changes to the savedInstanceState.
    // This bundle will be passed to onCreate and
    // onRestoreInstanceState if the process is
    // killed and restarted by the run time.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.i(TAG, "onSaveInstanceState()");
        super.onSaveInstanceState(savedInstanceState);
    }

    // Called at the end of the active lifetime.
    // Suspend UI updates, threads, or CPU intensive processes
    // that don’t need to be updated when the Activity isn’t
    // the active foreground Activity.
    @Override
    public void onPause(){
        Log.i(TAG, "onPause()");
        super.onPause();
    }

    // Called at the end of the visible lifetime.
    // Suspend remaining UI updates, threads, or processing
    // that aren’t required when the Activity isn’t visible.
    // Persist all edits or state changes
    // as after this call the process is likely to be killed.
    @Override
    public void onStop(){
        Log.i(TAG, "onStop()");
        super.onStop();
    }

    // Sometimes called at the end of the full lifetime.
    // Clean up any resources including ending threads,
    // closing database connections etc.
    @Override
    public void onDestroy(){
        Log.i(TAG, "onDestroy()");
        super.onDestroy();
    }
}
