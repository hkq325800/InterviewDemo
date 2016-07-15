package com.kerchin.demo.InterviewDemo.Broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kerchin.demo.InterviewDemo.R;

public class BroadcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad);

        IntentFilter i = new IntentFilter("BC_one");
        //动态注册优先级稍高 且在manifest中不需要写明 但是启动时机与代码作用域相关
        BC3 bc3 = new BC3();
        registerReceiver(bc3, i);
    }

    public void NormalBroad(View v){
        Intent i = new Intent();
        i.putExtra("msg", "normal");
        i.setAction("BC_one");
        sendBroadcast(i);
    }

    public void OrderedBroad(View v){
        Intent i = new Intent();
        i.putExtra("msg", "ordered");
        i.setAction("BC_two");
        sendOrderedBroadcast(i, "");
    }

    public void StickyBroad(View v){
        Intent i = new Intent();
        i.putExtra("msg", "sticky");
        i.setAction("BC_three");
        sendStickyBroadcast(i);
    }
}
