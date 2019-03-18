package com.gmail.yalovikdima.itacademy.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v4.content.LocalBroadcastManager;

import static com.gmail.yalovikdima.itacademy.dz5.WifiService.CHECK_WIFI;
import static com.gmail.yalovikdima.itacademy.dz5.WifiService.STATE;


public class WifiBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            switch (intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)) {
                case WifiManager.WIFI_STATE_ENABLED: {
                    sendState(context, true);
                    break;
                }
                case WifiManager.WIFI_STATE_DISABLED: {
                    sendState(context, false);
                    break;
                }

            }
        }
    }

    public void sendState(Context context, boolean state) {
        Intent intent = new Intent(CHECK_WIFI);
        intent.putExtra(STATE, state);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

}
