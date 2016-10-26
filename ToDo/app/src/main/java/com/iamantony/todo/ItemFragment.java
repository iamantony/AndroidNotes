package com.iamantony.todo;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.iamantony.todo.OnNewItemAddedListener;

public class ItemFragment extends Fragment {
    public static final String TAG = "ItemFragment";
    private OnNewItemAddedListener onNewItemAddedListener;
    private EditText m_editText = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstaceState) {
        View view = inflater.inflate(R.layout.item_fragment, container, false);

        m_editText = (EditText) view.findViewById(R.id.editTextFragment);
        m_editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) ||
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        String newItem = m_editText.getText().toString();
                        onNewItemAddedListener.onNewItemAdded(newItem);
                        m_editText.setText("");
                        return true;
                    }
                }
                return false;
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            onNewItemAddedListener = (OnNewItemAddedListener)activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    " must implement OnNewItemAddedListener");
        }
    }
}
