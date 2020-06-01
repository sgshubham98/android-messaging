package com.example.messagingapp;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.messagingapp.receiver.DeliveryReciever;
import com.example.messagingapp.receiver.SentReciever;

import java.util.regex.Pattern;

public class NewMessage extends AppCompatActivity implements View.OnClickListener {

    private EditText editPhoneNumber;
    private EditText editSMSMessage;
    String phoneNumber;
    String smsMessage;
    BroadcastReceiver sendBR = new SentReciever();
    BroadcastReceiver deliverBR = new DeliveryReciever();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        setSupportActionBar((Toolbar)findViewById(R.id.message_toolbar));
        setTitle("Send new SMS");
        init();
    }

    private void init() {
        editPhoneNumber = findViewById(R.id.phone_number);
        editSMSMessage = findViewById(R.id.sms_message);
        Button sendButton = findViewById(R.id.btNewSend);
        sendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btNewSend) {
            phoneNumber = editPhoneNumber.getText().toString();
            smsMessage = editSMSMessage.getText().toString();
            if (phoneNumber != null && isValidMobile(phoneNumber)) {
                if (smsMessage != null && smsMessage.trim().length() > 0) {
                    sendSMSNow();
                } else{
                    Toast.makeText(this, "Please enter a non-empty message", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendSMSNow(){
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);

        registerReceiver(sendBR, new IntentFilter(SENT));
        registerReceiver(deliverBR, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, smsMessage, sentPI, deliveredPI);
    }

    private boolean isValidMobile(String phone) {
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() > 6 && phone.length() <= 13;
        }
        return false;
    }

}
