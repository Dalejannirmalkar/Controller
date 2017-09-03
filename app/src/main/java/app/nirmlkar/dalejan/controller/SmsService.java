package app.nirmlkar.dalejan.controller;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by dalejan on 3/9/17.
 */

public class SmsService extends Service {

   SmsReciver smsReciver=new SmsReciver();


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(smsReciver,new IntentFilter(
                TelephonyManager.ACTION_PHONE_STATE_CHANGED));
        Log.d("hhhhhhhhhh","jwehefgfhwqg");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(smsReciver);
        Log.d("hhhhhhhhhhydyytddfyt","jwehefgfhwqg");

    }

}
