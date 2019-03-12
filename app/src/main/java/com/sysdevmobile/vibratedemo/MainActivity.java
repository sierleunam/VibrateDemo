package com.sysdevmobile.vibratedemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    IntentFilter intentFilter;
    MyBroadcastReceiver receiver;

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnVibrate = findViewById(R.id.buttonVibrate);
        final EditText et = findViewById(R.id.editText);
        receiver = new MyBroadcastReceiver();
        intentFilter = new IntentFilter("com.sysdevmobile.vibratedemo.VIBRATE");

        btnVibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long d=200;
                String ds = et.getText().toString();

                Log.d(TAG, "onClick: ds Value "+ds);
                try {
                    d = (long) Double.parseDouble(ds);
                }
                catch (NumberFormatException nfe)
                {
                    nfe.printStackTrace();
                }
                Log.d(TAG, "onClick: "+ d);
                Intent intent = new Intent("com.sysdevmobile.vibratedemo.VIBRATE");

                intent.putExtra("time",d);
                sendBroadcast(intent);
            }
        });

        et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                et.setImeActionLabel("Go",KeyEvent.KEYCODE_ENTER);
                if ((keyEvent.getAction()== KeyEvent.ACTION_DOWN) && i== KeyEvent.KEYCODE_ENTER) {
                    btnVibrate.performClick();
                    return true;
                }
                return false;
            }
        });
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }

}
