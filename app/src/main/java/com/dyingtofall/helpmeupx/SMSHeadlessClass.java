package com.dyingtofall.helpmeupx;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;


public class SMSHeadlessClass extends Fragment
{
    String phoneNo;
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


    }

}
