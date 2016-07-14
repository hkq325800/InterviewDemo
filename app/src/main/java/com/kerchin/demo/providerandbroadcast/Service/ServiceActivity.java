package com.kerchin.demo.providerandbroadcast.Service;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.kerchin.demo.providerandbroadcast.R;

/**
 * Created by Kerchin on 2016/7/14 0014.
 */
public class ServiceActivity extends Activity {
    ServiceConnection sc;
    MyBindService service;
    Intent intentForBind;
    Intent intentForStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        intentForBind = new Intent(ServiceActivity.this, MyBindService.class);
        intentForStart = new Intent(ServiceActivity.this, MyService.class);
        sc = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder binder) {
                //当启动源和service连接意外丢失会调用这个方法 如崩溃和强杀
                service = ((MyBindService.MyBinder) binder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                //当启动源和service成功连接后会调用这个方法
            }
        };
    }

    public void startService(View v) {
        startService(intentForStart);
    }

    public void bindService(View v) {
        bindService(intentForBind
                , sc, Service.BIND_AUTO_CREATE);
    }

    public void startBindService(View v){
        startService(intentForBind);
        bindService(intentForBind
                , sc, Service.BIND_AUTO_CREATE);
    }

    public void play(View v) {
        if (service != null)
            service.play();
        else
            Log.e("ServiceActivity", "service is null");
    }

    public void unbindService(View v) {
        unbindService(sc);
    }

    public void stopUnbindService(View v){
        stopService(intentForBind);
        unbindService(sc);
    }

    public void stopService(View v){
        stopService(intentForStart);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(service!=null)
            unbindService(sc);
        stopService(intentForStart);
        stopService(intentForBind);
    }
}
