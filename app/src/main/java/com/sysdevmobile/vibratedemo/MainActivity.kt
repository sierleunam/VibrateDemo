package com.sysdevmobile.vibratedemo

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    internal lateinit var intentFilter: IntentFilter
    internal lateinit var receiver: MyBroadcastReceiver

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are *not* resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * [.onResumeFragments].
     */
    override fun onResume() {
        super.onResume()
        registerReceiver(receiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnVibrate = findViewById<Button>(R.id.buttonVibrate)
        val et = findViewById<EditText>(R.id.editText)
        receiver = MyBroadcastReceiver()
        intentFilter = IntentFilter("com.sysdevmobile.vibratedemo.VIBRATE")

        btnVibrate.setOnClickListener {
            var d: Long = 200
            val ds = et.text.toString()

            Log.d(TAG, "onClick: ds Value $ds")
            try {
                d = java.lang.Double.parseDouble(ds).toLong()
            } catch (nfe: NumberFormatException) {
                nfe.printStackTrace()
            }

            Log.d(TAG, "onClick: $d")
            val intent = Intent("com.sysdevmobile.vibratedemo.VIBRATE")

            intent.putExtra("time", d)
            sendBroadcast(intent)
        }

        et.setOnKeyListener(View.OnKeyListener { _, i, keyEvent ->
            et.setImeActionLabel("Go", KeyEvent.KEYCODE_ENTER)
            if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                btnVibrate.performClick()
                return@OnKeyListener true
            }
            false
        })
        // ATTENTION: This was auto-generated to handle app links.
        val appLinkIntent = intent
        appLinkIntent.action
        appLinkIntent.data
    }

    companion object {

        private const val TAG = "MainActivity"
    }

}
