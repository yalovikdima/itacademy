package com.gmail.yalovikdima.itacademy.dz6;

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
import com.gmail.yalovikdima.itacademy.dz5.WifiService;

import static com.gmail.yalovikdima.itacademy.dz5.WifiService.CHECK_WIFI;
import static com.gmail.yalovikdima.itacademy.dz5.WifiService.STATE;

public class Dz6Activity extends Activity {

    public static Intent getIntent(Context context) {
        return new Intent(context, Dz6Activity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz6);
    }
}


