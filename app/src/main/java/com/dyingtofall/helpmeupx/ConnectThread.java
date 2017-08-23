package com.dyingtofall.helpmeupx;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler; //added due to obtain message not being under single use java.util

import java.io.IOException;
import java.util.UUID;

import static com.dyingtofall.helpmeupx.BluetoothRfCommFrag.MY_UUID;
import static com.dyingtofall.helpmeupx.BluetoothRfCommFrag.SUCCESS_CONNECT;

public class ConnectThread extends Thread
{
    UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    BluetoothAdapter btAdapter;
    Handler mHandler;
    ConnectThread connectThread;
    BluetoothRfCommFrag bluetoothRfCommFrag;


    public ConnectThread(BluetoothDevice device, BluetoothAdapter iBluetoothAdapter, Handler iHandler,UUID uuid, BluetoothRfCommFrag iBluetoothRfCommFrag)
    {
        btAdapter = iBluetoothAdapter;
        mHandler = iHandler;
        bluetoothRfCommFrag = iBluetoothRfCommFrag;


        BluetoothSocket tmp = null;
        mmDevice = device;
        try {
            tmp = device.createRfcommSocketToServiceRecord(uuid);
        } catch (IOException e) { }
        mmSocket = tmp;
    }

    public void run() {

        btAdapter.cancelDiscovery();
        try {
            mmSocket.connect();
        } catch (IOException connectException) {
            try {
                mmSocket.close();
            } catch (IOException closeException) { }
            return;
        }

        mHandler.obtainMessage(SUCCESS_CONNECT, mmSocket).sendToTarget();




    }






    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }


}
