package com.example.poojagupta.stopwatch06;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;

public class BoundService extends Service {


    long start;
    long millis;
    long seconds;
    long minutes;
    long timeInMillisWhenStopped;
    long elapsedTimeMillis;
    private IBinder binder = null;
    Boolean flag;

    // set variables when service is created. This is one time activity
    @Override
    public void onCreate() {
        binder = new MyBinder();
        timeInMillisWhenStopped = 0;
        elapsedTimeMillis = 0;
        flag = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY; // if service is killed while starting it restarts
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder; // return binder object to the component which needs to bind to the service
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return this.stopService(intent); // stop the service when all the components have unbound
    }

    public String getTime() {
        // check if the service is restarted after stopping or if the stop was reset
        if (flag) {
            start = SystemClock.uptimeMillis();
            flag = false;
        }
        // calculate the minutes seconds and millis for the timer
        elapsedTimeMillis = SystemClock.uptimeMillis() - start;
        millis = timeInMillisWhenStopped + elapsedTimeMillis;
        seconds = millis / 1000;
        minutes = seconds / 60;
        seconds %= 60;
        return String.format("%d:%02d:%03d", minutes, seconds, millis % 1000);
    }

    // capture the time when the timer was stopped which will be used when the timer is started again
    public void stopTimer() {
        timeInMillisWhenStopped = millis;
        flag = true;
    }

    // reset the stored time
    public void resetTimer() {
        timeInMillisWhenStopped = 0;
        flag = true;
    }

    public class MyBinder extends Binder {

        BoundService getService() {
            return BoundService.this;
        }
    }
}
