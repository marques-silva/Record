package com.marques.record;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class RecService extends Service {
    Boolean start = false;
    RecordAudio gravacao = new RecordAudio();

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onRebind(Intent intent) {
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (start) {
            start = false;
            gravacao.onRecord(false);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (!start) {
            start = true;
            gravacao.onRecord(true);
        }
        return START_STICKY;
        //return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }


}
