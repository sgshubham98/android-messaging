package com.example.messagingapp.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DeliveryReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (getResultCode()){
            case Activity.RESULT_OK:
                Toast.makeText(context, "Delivery Successful", Toast.LENGTH_SHORT).show();
                break;
            case Activity.RESULT_CANCELED:
                Toast.makeText(context, "Delivery Failed", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
