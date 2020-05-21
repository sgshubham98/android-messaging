package com.example.messagingapp.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SentReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (getResultCode()){
            case Activity.RESULT_OK:
                Toast.makeText(context, "SMS Sent", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, "SMS sent failed", Toast.LENGTH_SHORT).show();
        }
    }
}
