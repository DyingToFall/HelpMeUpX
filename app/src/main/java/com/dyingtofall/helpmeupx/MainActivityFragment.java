package com.dyingtofall.helpmeupx;


import android.Manifest;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ChangedPackages;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.SharedLibraryInfo;
import android.content.pm.VersionedPackage;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.UserHandle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.XmlRes;
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
import static android.Manifest.permission.BLUETOOTH_ADMIN;
import static android.Manifest.permission.READ_CONTACTS;
import static android.content.Context.AUDIO_SERVICE;
import static com.dyingtofall.helpmeupx.BluetoothRfCommFrag.ACCESS_COARSE_LOCATION;


public class MainActivityFragment extends Fragment implements ActivityCompat.OnRequestPermissionsResultCallback

{
    public static final String TAG1 = "MainActivity";
    private static final int REQUEST_BLUETOOTH = 0;
    private Context context;
    public CountDownTimer timer = null;
    Button contactBtn, cancelBtn;
    String phoneNo;
    TextView textViewCounter;
    public static AudioManager audioManager;
    public static MediaPlayer mediaPlayer;
    HandlerHelper handlerHelper;
    MainActivity mainActivity;
    BluetoothRfCommFrag bluetoothRfCommFrag;
    AlarmController alarmController;
    Intent intent;
    Animation animate = null;
    private final String TAG = "Debugging";
    private final static int REQUEST_CODE_ENABLE_BLUETOOTH = 0;
    protected static final int SUCCESS_CONNECT = 0;
    protected static final int MESSAGE_READ = 1;
    SharedPreferences spS;
    SharedPreferences.Editor eSPS;
    EmergencyDatabaseDialogFrag emergencyDatabaseDialogFrag;
    ReliabilityDatabaseDialogFrag reliabilityDatabaseDialogFrag;
    PackageManager packageManager;
    BluetoothHeadless bluetoothHeadless;


    // UI elements
   // private TextView lblLocation;



    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);

        bluetoothHeadless = new BluetoothHeadless(this);
        bluetoothHeadless.onCreate(savedInstanceState);

        textViewCounter =(TextView) view.findViewById(R.id.textViewAlarm);

        animate = AnimationUtils.loadAnimation(getActivity(), R.anim.animation);

        //Overriding audio to max volume
        audioManager = (AudioManager) getContext().getSystemService(getActivity().AUDIO_SERVICE);
        final int userVolume = audioManager.getStreamVolume(AudioManager.STREAM_ALARM);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_PLAY_SOUND);
        final AlarmService alarmService = new AlarmService();

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

        spS = getContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        eSPS = spS.edit();
        //eSPS.putInt("EmergencySize", 5);
        //eSPS.clear();
        //eSPS.commit();

        cancelBtn = (Button) view.findViewById(R.id.CancelAlarm);


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {

                String buttonText = cancelBtn.getText().toString();



                 if(buttonText.equals("Cancel"))
                {
                   // audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, userVolume, AudioManager.FLAG_PLAY_SOUND);
                    cancelBtn.clearAnimation();
                    cancelBtn.setText("");
                    /*  if(mediaPlayer.isPlaying())
                    {
                        mediaPlayer.stop();
                    }*/
                    timer.cancel();
                    textViewCounter.setText("Timer");

                   // mediaPlayer.stop();

                }
            }
        });

        contactBtn = (Button) view.findViewById(R.id.GetContacts);
        contactBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                ContactListDialogFragment cLDFrag = new ContactListDialogFragment();
                cLDFrag.setCancelable(false);
                cLDFrag.show(getFragmentManager(),"Contact List Dialog");
                //cLDFrag.show(getSupportFragmentManager(),"Contact List Dialog");


            }
        });

        return view;

    }

    public void sendSMSMessage()
    {
        Bundle receiveArgs = new Bundle();
        cancelBtn.startAnimation(animate);
        //COMMENTED OUT TO TEST WITHOUT AUDIO, MUST BE ADDED BACK TO HAVE AUDIO!!!!!!!!!!!!!!!!
        //mediaPlayer = MediaPlayer.create(getContext(), R.raw.alarm);
        //mediaPlayer.start();
        //mediaPlayer.setLooping(true);
        cancelBtn.setText("Cancel");
        timer.start();
        mainActivity = (MainActivity) getActivity();
        String smsSend = mainActivity.displayLocation();
        ArrayList<String> phoneString = new ArrayList<>();
        receiveArgs = getArguments();

        //This is used to grab numbers from shared preferences
        if  (spS.getInt("EmergencySize", 0) == 0)
        {
            Toast.makeText(getActivity(), "You have no emergency contacts!", Toast.LENGTH_LONG).show();
            return;
        }
        for (int i = 0; i < spS.getInt("EmergencySize", 0); i++)
        {
            phoneString.add(spS.getString("EmNum" + Integer.toString(i), null));
        }
        phoneNo = spS.getString("EmNum0", "16199525946");


        String incomingString = receiveArgs.getString("Message");


        SmsManager sms = SmsManager.getDefault();



       HandlerHelper sendHandler = new HandlerHelper(cancelBtn, this, animate, timer);
        sendHandler.postDelayed(new Runnable()
        {

            @Override
            public void run()
            {

                Bundle receiveArgs = new Bundle();
                receiveArgs = getArguments();
                String smsSend = mainActivity.displayLocation();
                ArrayList<String> phoneString = new ArrayList<>();


                //receiveArgs = new Bundle();




                //cancelBtn.startAnimation(animate);
                //AlarmController alarmController = new AlarmController(context);
                //alarmController.playSound(soundURI);


                String incomingString = receiveArgs.getString("Message");
                if  (spS.getInt("EmergencySize", 0) == 0)
                {
                    Toast.makeText(getActivity(), "You have no emergency contacts!", Toast.LENGTH_LONG).show();
                    return;
                }
                for (int i = 0; i < spS.getInt("EmergencySize", 0); i++)
                {
                    phoneString.add(spS.getString("EmNum" + Integer.toString(i), null));
                }
                phoneNo = spS.getString("EmNum0", "16199525946");
                SmsManager sms = SmsManager.getDefault();
               // String incomingString = "fall";

                switch (incomingString)
                {
                    case "fall":
                        //String message1 = "Help I have fallen";
                        String message1 = "Help! I have fallen at " + smsSend;
                        for (int j =0; j <  phoneString.size(); j++)
                        {

                                sms.sendTextMessage(phoneString.get(j), null, message1, null, null);

                        }

                        break;

                    case "pani":
                       // String message2 = "Help I pushed the panic button";
                        String message2 = "Help! I pushed the panic button at " + smsSend;
                        for (int j =0; j <  phoneString.size(); j++)
                        {

                                sms.sendTextMessage(phoneString.get(j), null, message2, null, null);

                        }

                        break;

                }

            }
        }, 3000);






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

              //  FragmentManager fragmanager = getActivity().getFragmentManager();
                BluetoothRfCommFrag bluetoothRfCommFrag = new BluetoothRfCommFrag(this);
                bluetoothRfCommFrag.show(getFragmentManager(), "bluetooth dialog");
                return true;

            case R.id.emergencyList:

                EmergencyListDialogFragment eldFrag = new EmergencyListDialogFragment();
                eldFrag.setCancelable(false);
                eldFrag.show(getFragmentManager(),"Emergency List Dialog");
                return true;

            case R.id.reliabilityDatabase:

                ReliabilityDatabaseDialogFrag reliabilityDatabaseDialogFrag = new ReliabilityDatabaseDialogFrag();
                reliabilityDatabaseDialogFrag.setCancelable(false);
                reliabilityDatabaseDialogFrag.show(getFragmentManager(),"Emergency List Dialog");
                return true;

            case R.id.emergencyDatabase:

                EmergencyDatabaseDialogFrag emergencyDatabaseDialogFrag = new EmergencyDatabaseDialogFrag();
                emergencyDatabaseDialogFrag.setCancelable(false);
                emergencyDatabaseDialogFrag.show(getFragmentManager(),"Emergency List Dialog");
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private View mLayout;

}

