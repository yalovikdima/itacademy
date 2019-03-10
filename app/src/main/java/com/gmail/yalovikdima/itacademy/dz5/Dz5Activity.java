package com.gmail.yalovikdima.itacademy.dz5;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gmail.yalovikdima.itacademy.R;
import com.gmail.yalovikdima.itacademy.services.WifiService;
import com.gmail.yalovikdima.itacademy.util.WifiBroadcastReceiver;

public class Dz5Activity extends AppCompatActivity {

    private TextView textView;
    WifiBroadcastReceiver mMessageReceiver = new WifiBroadcastReceiver();

    public static Intent getIntent(Context context) {
        return new Intent(context, Dz5Activity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz5);
        textView = findViewById(R.id.textView);


    }

    @Override
    protected void onResume() {
        startService(new Intent(Dz5Activity.this,
                WifiService.class));

        IntentFilter intentFilter = new IntentFilter("custom-event-name");
        LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver, intentFilter);

        super.onResume();
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(
                mMessageReceiver);

        stopService(new Intent(Dz5Activity.this,
                WifiService.class));

        super.onPause();
    }

}
