package com.dyingtofall.helpmeupx;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;

import static com.google.android.gms.location.LocationRequest.create;


public class SMS extends Activity
{
    //Button sendBtn;
    String phoneNo;
    String message;

    public MainActivity mainActivity;


    public SMS(MainActivity mainActivity)
    {
        super();
        this.mainActivity = mainActivity;



    }







   /* @Override
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
    }*/
    //this.mainActivity = mainActivity;
    //this.context = context;
    protected void sendSMSMessage()
    {
        Bundle mArgs = new Bundle();
       // mArgs = getArguments();
        String incomingString;
      // Bundle incBundle = getIntent().getExtras();

       // if(savedInstanceState == null)
       // extras = getIntent().getExtras();
        //phoneNo = "15413908735";
        phoneNo = "16199525946";
        message = "Help I have fallen and can't get up";
        //PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(), SMS.class), 0);   // needs to be changed back to SMS class when moved
        PendingIntent pi = PendingIntent.getActivity(mainActivity, 0, new Intent(this, SMS.class), 0);
       // PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(getApplicationContext(), SMS.class), 0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNo, null, message, pi, null);

    }

}