package com.gmail.yalovikdima.itacademy.dz5;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.gmail.yalovikdima.itacademy.util.WifiBroadcastReceiver;

public class WifiService extends Service {

    private WifiBroadcastReceiver wifiBroadcastReceiver = new WifiBroadcastReceiver();
    private MyBinder myBinder = new MyBinder();

    public static final String STATE = "STATE";
    public static final String CHECK_WIFI = "CHECK_WIFI";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiBroadcastReceiver, intentFilter);
        return  myBinder;
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        unregisterReceiver(wifiBroadcastReceiver);
        super.unbindService(conn);
    }

    public void checkWifiState() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        sendState(mWifi.isConnected());
    }

    public void sendState(boolean state) {
        Intent intMes = new Intent(CHECK_WIFI);
        intMes.putExtra(STATE, state);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intMes);
    }

    class MyBinder extends Binder {
        WifiService getService() {
            return WifiService.this;
        }
    }

}
