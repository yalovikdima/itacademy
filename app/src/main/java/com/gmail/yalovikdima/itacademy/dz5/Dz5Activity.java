package com.gmail.yalovikdima.itacademy.dz5;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Switch;

import com.gmail.yalovikdima.itacademy.R;

import static com.gmail.yalovikdima.itacademy.dz5.WifiService.*;
import static com.gmail.yalovikdima.itacademy.dz5.WifiService.CHECK_WIFI;
import static com.gmail.yalovikdima.itacademy.dz5.WifiService.STATE;

public class Dz5Activity extends Activity {

    private Switch switchWifi;
    private WifiService wifiService;
    private ServiceConnection serviceConnection;

    public static Intent getIntent(Context context) {
        return new Intent(context, Dz5Activity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz5);
        switchWifi = findViewById(R.id.wifi_switch);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                wifiService = ((WifiService.MyBinder) iBinder).getService();
                wifiService.checkWifiState();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, WifiService.class);
        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);

        IntentFilter intentFilter = new IntentFilter(CHECK_WIFI);

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        unbindService(serviceConnection);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if (intent.getBooleanExtra(STATE, false)) {
                    switchWifi.setChecked(true);
                    switchWifi.setText("WiFi is ON");
                } else {
                    switchWifi.setChecked(false);
                    switchWifi.setText("WiFi is OFF");
                }

            }
        }
    };

}
