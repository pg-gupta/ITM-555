package com.example.poojagupta.lab5_poojagupta_a20413675;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Pooja Gupta
 *         Date: 02/23/2018
 *         Assignment/HW: #5
 */
public class MainActivity extends AppCompatActivity {

    // set initial duration of the slideshow to 5
    int duration = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get reference of the views of main activity
        Button slideShowBtn = (Button) findViewById(R.id.startSlideShowBtn);
        final TextView seekbarValue = (TextView) findViewById(R.id.editText);
        final SeekBar durationSeekBar = (SeekBar) findViewById(R.id.picDurationSeekBar);

        // set initial value of seekarbar
        durationSeekBar.setProgress(duration);

        // set changed value of seekbar to the text box
        durationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // keep minimum duration as 2 sec
                seekbarValue.setText(String.valueOf(i + 2));
                duration = i + 2;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // set onclick listener on the Slide Show button
        slideShowBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // instantiate second activity intent
                    Intent secondActivityIntent = new Intent(getApplicationContext(), SecondActivity.class);
                    // create a bundle to store seekbar value and use it in second activity
                    Bundle bundle = new Bundle();
                    bundle.putInt("Duration", duration);
                    secondActivityIntent.putExtras(bundle);
                    // start second activity
                    startActivity(secondActivityIntent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
