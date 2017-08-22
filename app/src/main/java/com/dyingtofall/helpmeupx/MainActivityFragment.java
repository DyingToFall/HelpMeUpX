package com.dyingtofall.helpmeupx;



import android.Manifest;
import android.app.FragmentManager;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;



public class MainActivityFragment extends Fragment implements ActivityCompat.OnRequestPermissionsResultCallback
{
    public static final String TAG1 = "MainActivity";
    private static final int REQUEST_BLUETOOTH = 0;
    Button sendBtn;
    protected SMS smsSend;

    private final String TAG = "Debugging";
    private final static int REQUEST_CODE_ENABLE_BLUETOOTH = 0;
    protected static final int SUCCESS_CONNECT = 0;
    protected static final int MESSAGE_READ = 1;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        sendBtn = (Button) view.findViewById(R.id.button1);
        smsSend = new SMS(this);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                //SMS smsSend = new SMS();
                smsSend.sendSMSMessage();



                //msms.sendSMSMessage();    //potential use for when using SMS class

            }
        });
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
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

    private void requestBluetoothPermissions()
    {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.BLUETOOTH) || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.BLUETOOTH_ADMIN))
        {
            Log.i(TAG, "Allowing Bluetooth connection");
            Snackbar.make(mLayout, R.string.permission_bluetooth_rationale, Snackbar.LENGTH_INDEFINITE ).setAction(R.string.ok, new View.OnClickListener()
            {
                @Override
                public void onClick (View view)
                {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.BLUETOOTH}, REQUEST_BLUETOOTH);
                }
            });
        }
    }

}
