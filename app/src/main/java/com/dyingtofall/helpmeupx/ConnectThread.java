package com.dyingtofall.helpmeupx;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler; //added due to obtain message not being under single use java.util

import java.io.IOException;

import static com.dyingtofall.helpmeupx.BluetoothRfCommFrag.MY_UUID;
import static com.dyingtofall.helpmeupx.BluetoothRfCommFrag.SUCCESS_CONNECT;

public class ConnectThread extends Thread
{
    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    BluetoothAdapter btAdapter;
    Handler mHandler;


    public ConnectThread(BluetoothDevice device) {
        BluetoothSocket tmp = null;
        mmDevice = device;
        try {
            tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
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
