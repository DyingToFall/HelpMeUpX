package com.dyingtofall.helpmeupx;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;


public class SMS extends Activity
{
    Button sendBtn;
    String phoneNo;
    String message;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        sendBtn = (Button) findViewById(R.id.button1);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();
            }
        });
    }

    protected void sendSMSMessage()
    {
        phoneNo = "15413908735";
        message = "Help I have fallen and can't get up";

        PendingIntent pi = PendingIntent.getActivity(this, 0,
                new Intent(this, SMS.class), 0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNo, null, message, pi, null);

    }
}