package com.dyingtofall.helpmeupx;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;


public class SMSHeadlessClass extends Fragment
{
    String phoneNo;
    String message;
    public MainActivity mainActivity;

    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setRetainInstance(true);

    }

    protected void sendSMSMessage()
    {
        SMSHeadlessClass smsHeadlessClass = new SMSHeadlessClass();
        Bundle receiveArgs = new Bundle();
        receiveArgs = getArguments();
        phoneNo = "16199525946";

        //smsHeadlessClass.getArguments(receiveArgs);

        String incomingString = receiveArgs.getString("Message");
        SmsManager sms = SmsManager.getDefault();

        switch (incomingString)
        {
            case "fall":
                String message1 = "Help I have fallen";
                sms.sendTextMessage(phoneNo, null, message1, null, null);
                break;

            case "panic":
                String message2 = "Help I pushed the panic button";
                sms.sendTextMessage(phoneNo, null, message2, null, null);
                break;

        }

        // Bundle incBundle = getIntent().getExtras();

        // if(savedInstanceState == null)
        // extras = getIntent().getExtras();
        //phoneNo = "15413908735";

        message = "Help I have fallen and can't get up";
        //PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(), SMS.class), 0);   // needs to be changed back to SMS class when moved
        //PendingIntent pi = PendingIntent.getActivity(mainActivity, 0, new Intent(mainActivity, SMSHeadlessClass.class), 0);
        // PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(getApplicationContext(), SMS.class), 0);

        //sms.sendTextMessage(phoneNo, null, message, pi, null);

    }

}
