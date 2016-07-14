package com.kerchin.demo.providerandbroadcast.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Kerchin on 2016/7/14 0014.
 */
public class MyBindService extends Service {

    final static String tag = "MyBindService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(tag, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(tag, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(tag, "onBind");
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(tag, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(tag, "onDestroy");
    }

    public void play(){
        Log.i(tag, "play");
    }

    public class MyBinder extends Binder{
        public MyBindService getService(){
            return MyBindService.this;
        }
    }
}
