package com.example.poojagupta.stopwatch06;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poojagupta.stopwatch06.BoundService.MyBinder;

public class MainActivity extends AppCompatActivity {

    // declare variables
    Button startServiceBtn;
    Button stopServiceBtn;
    Button startBtn;
    Button stopBtn;
    Button resetBtn;
    TextView timer;
    Boolean boundServiceActive = false;
    ServiceConnection serviceConnection;
    BoundService boundService;
    Boolean timerStarted = false;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialise variables
        startServiceBtn = (Button) findViewById(R.id.startServiceBtn);
        stopServiceBtn = (Button) findViewById(R.id.stopServiceBtn);
        startBtn = (Button) findViewById(R.id.startBtn);
        stopBtn = (Button) findViewById(R.id.stopBtn);
        resetBtn = (Button) findViewById(R.id.resetBtn);
        timer = (TextView) findViewById(R.id.timer);
        final Handler handler = new Handler();

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                boundServiceActive = true;
                MyBinder myBinder = (MyBinder) iBinder;
                boundService = myBinder.getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

                boundServiceActive = false;

            }
        };

        // start BoundService on START SERVICES button click
        startServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), BoundService.class);
                startService(intent);
                bindService(intent, serviceConnection, 0);
                Toast.makeText(getApplicationContext(), "Services Started", Toast.LENGTH_SHORT).show();
            }
        });

        // start timer on START button click
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Start Services Started", Toast.LENGTH_SHORT).show();

                timerStarted = true;

               /* while(boundServiceActive && timerStarted){
                    timer.setText(boundService.getTime());
                }*/

                if (boundServiceActive) {

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (timerStarted) {
                                timer.setText(boundService.getTime());
                                handler.postDelayed(this, 0);
                            }
                        }
                    });
                }
            }
        });

        // stop timer on STOP button click and remove the handler
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (boundServiceActive) {
                    timerStarted = false;
                    handler.removeCallbacksAndMessages(null);
                    boundService.stopTimer();
                }

            }
        });

        // reset timer on RESET button click and remove the handler
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (boundServiceActive) {
                    timerStarted = false;
                    handler.removeCallbacksAndMessages(null);
                    timer.setText("00:00:000");
                    boundService.resetTimer();
                }
            }
        });

        // stop service on STOP SERVICES button click
        stopServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (boundServiceActive) {
                    timerStarted = false;
                    // reset the timer when service is stopped
                    resetBtn.performClick();
                    unbindService(serviceConnection);
                    boundServiceActive = false;
                }
            }
        });


    }
}
