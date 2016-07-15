package com.kerchin.demo.InterviewDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kerchin.demo.InterviewDemo.Broadcast.BroadcastActivity;
import com.kerchin.demo.InterviewDemo.ContentProvider.ContentProviderActivity;
import com.kerchin.demo.InterviewDemo.Service.ServiceActivity;

/**
 * Created by Kerchin on 2016/7/13 0013.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Broadcast(View v) {
        startActivity(new Intent(this, BroadcastActivity.class));
    }

    public void ContentProvider(View v) {
        startActivity(new Intent(this, ContentProviderActivity.class));
    }

    public void Service(View v) {
        startActivity(new Intent(this, ServiceActivity.class));
    }
}
