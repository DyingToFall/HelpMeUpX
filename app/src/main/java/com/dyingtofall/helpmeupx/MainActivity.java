package com.dyingtofall.helpmeupx;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
{

    Button sendBtn;

    //String phoneNo;
    //String message;
    //private SMS msms;
    protected SMS smsSend;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendBtn = (Button) findViewById(R.id.button1);
        smsSend = new SMS(this);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                //SMS smsSend = new SMS();
                smsSend.sendSMSMessage();

                //msms.sendSMSMessage();    //potential use for when using SMS class

            }
        });
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


    }

   /* protected void sendSMSMessage()  //needs to be moevd back to SMS class
    {
        phoneNo = "15413908735";
        message = "Help I have fallen and can't get up";

        PendingIntent pi = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);   // needs to be changed back to SMS class when moved
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNo, null, message, pi, null);

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            return true;
        }*/
        switch(item.getItemId())
        {
            case R.id.bluetooth:
                BluetoothDialogFrag blueToothDialog = new BluetoothDialogFrag();
                blueToothDialog.show(getFragmentManager(), "bluetooth dialog");
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }


}
