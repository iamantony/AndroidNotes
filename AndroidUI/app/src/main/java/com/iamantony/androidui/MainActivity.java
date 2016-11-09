package com.iamantony.androidui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.AnalogClock;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AnalogClock analogClock = (AnalogClock)findViewById(R.id.aClock);
        final Button clockButton = (Button)findViewById(R.id.clockButton);
        clockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (analogClock.isShown()) {
                    analogClock.setVisibility(View.INVISIBLE);
                    clockButton.setText(R.string.clockBtnShow);
                }
                else {
                    analogClock.setVisibility(View.VISIBLE);
                    clockButton.setText(R.string.clockBtnHide);
                }

            }
        });
    }
}
