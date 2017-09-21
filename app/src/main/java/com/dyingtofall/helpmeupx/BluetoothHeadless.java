package com.dyingtofall.helpmeupx;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.UUID;



/**
 * Created by Home on 9/20/2017.
 */

public class BluetoothHeadless extends Fragment
{
    MainActivity mainActivity;
    BluetoothRfCommFrag bluetoothRfCommFrag;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedEditor;
    MainActivityFragment mainActivityFragment;
    BluetoothLetRun bluetoothLetRun;
    String btConnect = "B8:27:EB:F2:99:7A";

    BluetoothHeadless(MainActivityFragment mAct)
    {
        super();
        mainActivityFragment = mAct;
    }

    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setRetainInstance(true);
       // bluetoothRfCommFrag.letsRun();
       // sharedPreferences = getContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        //sharedEditor = sharedPreferences.edit();
       // String btDeviceAddress = sharedPreferences.getString("btConnect", "");
        bluetoothLetRun = new BluetoothLetRun(btConnect, mainActivityFragment);
    }




}
