package com.sysdevmobile.vibratedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;

import java.util.Objects;

public class MyBroadcastReceiver extends BroadcastReceiver{

    private static final String TAG = "MyBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        long time;
        Log.d(TAG,""+intent.getAction());
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Objects.requireNonNull(intent.getAction()).equals(context.getString(R.string.custom_intent))) {
            time = intent.getLongExtra("time", 500);
            Log.d(TAG, "onReceive: "+time);
           try {
               assert v != null;
               Objects.requireNonNull(v).vibrate(time);
           }
            catch (NullPointerException npe)
            {
                npe.printStackTrace();
            }

        }
    }
}
