package com.kerchin.demo.InterviewDemo.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Kerchin on 2016/7/4 0004.
 */
public class BC1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "BC1"+intent.getStringExtra("msg"), Toast.LENGTH_SHORT).show();
    }
}
