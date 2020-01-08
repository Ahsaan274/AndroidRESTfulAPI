package com.example.androidrestfulapi.network;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MyIntentService extends IntentService{
    public static final String SERVICE_PAYLOAD = "SERVICE_PAYLOAD";
    public static final String SERVICE_MESSAGE = "SERVICE_MESSAGE";

    //constructor must be empty or you will face the error.
    public MyIntentService() {
        super("");
    }
    @Override
    protected void onHandleIntent(Intent intent) {

        sendMessageToUI("Dummy Data");
    }

    private void sendMessageToUI(String data) {
        Intent intent = new Intent(SERVICE_MESSAGE);
        intent.putExtra(SERVICE_PAYLOAD,data);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}