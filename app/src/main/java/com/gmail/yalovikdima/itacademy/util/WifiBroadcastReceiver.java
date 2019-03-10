package com.gmail.yalovikdima.itacademy.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class WifiBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("message");
        intent.putExtra("wifi", "wifi");
        Log.d("receiver", "Got message: " + msg);
    }
}
