package com.example.androidrestfulapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidrestfulapi.network.MyIntentService;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MyTag!";
    private TextView mLog;
    Button btnRun;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getStringExtra(MyIntentService.SERVICE_PAYLOAD);
            logOutput(data);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyIntentService.class);
                startService(intent);
            }
        });

    }
    private void logOutput(String data){
        Log.d(TAG,"logOutput:"+data);
        mLog.setText(data+"\n");
    }
    private void initView() {
        mLog = findViewById(R.id.txtView);
        btnRun = findViewById(R.id.run);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReceiver,new IntentFilter(MyIntentService.SERVICE_MESSAGE));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(mReceiver);
    }
}
