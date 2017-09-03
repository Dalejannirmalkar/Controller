package app.nirmlkar.dalejan.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by dalejan on 2/9/17.
 */

public class SmsReciver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        final SmsManager sms=SmsManager.getDefault();
        final Bundle bundle=intent.getExtras();
        try {
            if (bundle != null){
                final Object[] objects= (Object[]) bundle.get("pdus");
                AudioManager audio = (AudioManager)context.getSystemService(context.AUDIO_SERVICE);

                for (int i=0;i<objects.length;i++){

                    SmsMessage currentmessage=SmsMessage.createFromPdu((byte[]) objects[i]);
                    String phoneno=currentmessage.getDisplayOriginatingAddress();

                    String senderno=phoneno;
                    String message=currentmessage.getDisplayMessageBody();


                    if (message.equalsIgnoreCase("silent")){
                        audio.setRingerMode(0);
                    }
                    if (message.equalsIgnoreCase("normal")){
                     audio.setRingerMode(2);
                    }
                    if (message.equalsIgnoreCase("vibrate")){
                        audio.setRingerMode(1);
                    }


                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
