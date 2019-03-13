package com.sysdevmobile.vibratedemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val time: Long
        Log.d(TAG, "" + intent.action!!)
        if (intent.action == context.getString(R.string.custom_intent)) {
            val v = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            time = intent.getLongExtra("time", 500)
            Log.d(TAG, "onReceive: $time")
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val effect = VibrationEffect.createOneShot(time, VibrationEffect.DEFAULT_AMPLITUDE)
                    v.vibrate(effect)
                } else {
                    v.vibrate(time)
                }
            } catch (npe: NullPointerException) {
                npe.printStackTrace()
            }

        }
    }

    companion object {

        private const val TAG = "MyBroadcastReceiver"
    }
}
