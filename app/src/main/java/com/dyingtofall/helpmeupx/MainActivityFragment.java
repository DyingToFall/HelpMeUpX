package com.dyingtofall.helpmeupx;


import android.Manifest;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_CONTACTS;
import static com.dyingtofall.helpmeupx.BluetoothRfCommFrag.ACCESS_COARSE_LOCATION;


public class MainActivityFragment extends Fragment implements ActivityCompat.OnRequestPermissionsResultCallback

{
    public static final String TAG1 = "MainActivity";
    private static final int REQUEST_BLUETOOTH = 0;
    private Context context;
    public CountDownTimer timer = null;
    Button contactBtn, cancelBtn;
    String phoneNo, soundURI;
    TextView textViewCounter;
    AlarmSoundPool alarmSoundPool;
    HandlerHelper handlerHelper;
    protected SMS smsSend;
    AlarmController alarmController;
    Animation animate = null;
    private final String TAG = "Debugging";
    private final static int REQUEST_CODE_ENABLE_BLUETOOTH = 0;
    protected static final int SUCCESS_CONNECT = 0;
    protected static final int MESSAGE_READ = 1;


    // UI elements
    private TextView lblLocation;




    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);

        textViewCounter =(TextView) view.findViewById(R.id.textViewAlarm);

        animate = AnimationUtils.loadAnimation(getActivity(), R.anim.animation);

        sendSMSMessage();

        timer = new CountDownTimer(60000, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                textViewCounter.setText("Sending Messages In: " + millisUntilFinished / 1000 + " Seconds");
            }

            public void onFinish()
            {
                textViewCounter.setText("Messages Sending!");
            }
        };

        cancelBtn = (Button) view.findViewById(R.id.CancelAlarm);


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                AlarmSoundPool alarmSoundPool = new AlarmSoundPool(getContext());
                String buttonText = cancelBtn.getText().toString();

                if(buttonText.equals("Ready"))
                {

                    cancelBtn.startAnimation(animate);
                    cancelBtn.setText("Cancel");
                }

                else if(buttonText.equals("Cancel"))
                {
                    //
                    alarmSoundPool.stopSound();
                    cancelBtn.clearAnimation();
                    cancelBtn.setText("Ready");

                }
            }
        });




        lblLocation = (TextView) view.findViewById(R.id.textView);

        contactBtn = (Button) view.findViewById(R.id.GetContacts);


        contactBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                ContactListDialogFragment cLDFrag = new ContactListDialogFragment();
                cLDFrag.show(getFragmentManager(),"Contact List Dialog");
                //cLDFrag.show(getSupportFragmentManager(),"Contact List Dialog");


            }
        });


        return view;

    }


    //Currently not being used but would be for in sendSMSMessage() if needed
    public void doAlpha(View v)
    {
        //Animation animate = new Animation();

       // cancelBtn.startAnimation(animate);
       //contactBtn.startAnimation(animate);
    }


    protected void sendSMSMessage()
    {
        //SMSHeadlessClass smsHeadlessClass = new SMSHeadlessClass();
        //Bundle receiveArgs = new Bundle();

        //receiveArgs = getArguments();
        phoneNo = "16199525946";

        //smsHeadlessClass.getArguments(receiveArgs);

        //String incomingString = receiveArgs.getString("Message");
        //SmsManager sms = SmsManager.getDefault();

       // textViewCounter =(TextView) view.findViewById(R.id.textView2);

        SmsManager sms = SmsManager.getDefault();
        String incomingString = "fall";

        switch (incomingString)
        {
            case "fall":
                String message1 = "Help I have fallen";
                sms.sendTextMessage(phoneNo, null, message1, null, null);
                AlarmSoundPool alarmSoundPool = new AlarmSoundPool(getContext());
                alarmSoundPool.playSound();
                //alarmSoundPool.playLoop();
                //cancelBtn.startAnimation(animate);

                break;

            case "panic":
                String message2 = "Help I pushed the panic button";
                sms.sendTextMessage(phoneNo, null, message2, null, null);
                //    alarmController.playSound(soundURI);
                break;

        }


        //Commented out for testing purposes. Will be used for actual run
       /* HandlerHelper sendHandler = new HandlerHelper(cancelBtn, this, animate, timer);
        sendHandler.postDelayed(new Runnable()
        {

            @Override
            public void run()
            {
              //doAlpha(contactBtn);
                Bundle receiveArgs = new Bundle();

                receiveArgs = getArguments();
                phoneNo = "16199525946";
                timer.start();
                cancelBtn.startAnimation(animate);
                AlarmController alarmController = new AlarmController(context);
                alarmController.playSound(soundURI);


                //String incomingString = receiveArgs.getString("Message");
                SmsManager sms = SmsManager.getDefault();
                String incomingString = "fall";

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
        }, 3000);*/






    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
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
            {
                FragmentManager fragmanager = getActivity().getFragmentManager();
                BluetoothRfCommFrag bluetoothRfCommFrag = new BluetoothRfCommFrag();
                bluetoothRfCommFrag.show(fragmanager, "bluetooth dialog");
            }
            return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private View mLayout;

}

