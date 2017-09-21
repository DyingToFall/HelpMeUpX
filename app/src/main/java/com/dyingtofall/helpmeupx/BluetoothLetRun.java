package com.dyingtofall.helpmeupx;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.UUID;




/**
 * Created by Home on 9/20/2017.
 */

public class BluetoothLetRun
{
    protected static final int SUCCESS_CONNECT = 0;
    String tag = "debugging";
    private ArrayAdapter<String> chatArrayAdapter;
    private StringBuffer outStringBuffer;
    private EditText etMain;
    private final String TAG = "Debugging";
    private final static int REQUEST_CODE_ENABLE_BLUETOOTH = 0;
    protected static final int MESSAGE_READ = 1;
    public static final int MESSAGE_WRITE = 2;
    public static final int STATE_CONNECTED = 3;
    public static final int ACCESS_FINE_LOCATION = 4;
    public static final int ACCESS_COARSE_LOCATION = 5;
    public static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    BluetoothDevice bdDevice;
    BluetoothAdapter btAdapter;
    String btConnect = "B8:27:EB:F2:99:7A";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedEditor;
    ServerConnectedThread serverConnectedThread;
    MainActivity mainActivity;
    MainActivityFragment mainActivityFragment;



    public BluetoothLetRun(String btConnect, MainActivityFragment mFrag)
    {
        mainActivityFragment = mFrag;
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        bdDevice = btAdapter.getRemoteDevice(btConnect); //sets a string to the bdDevice for address
        BluetoothLetRun.ClientConnectThread clientConnectThread = new ClientConnectThread(bdDevice, btAdapter, MY_UUID);
        clientConnectThread.start();

    }

    private final Handler mHandler= new Handler()
    {

        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch(msg.what){
                case SUCCESS_CONNECT:
                    //Connected via RfComm
                    serverConnectedThread = new ServerConnectedThread((BluetoothSocket)msg.obj);
                    // Toast.makeText(getActivity(),"Connect",Toast.LENGTH_LONG).show();
                    serverConnectedThread.start();
                    Log.i(tag, "connected");
                    break;

                case MESSAGE_READ:
                    //Shows what messages are being received also decodes incoming text
                    byte[] readbuff=(byte[])msg.obj;
                    Charset usaScii = null;
                    usaScii = Charset.forName("US-ASCII");
                    ByteBuffer bb = ByteBuffer.wrap(readbuff);
                    CharBuffer TempCharBuffer = usaScii.decode(bb);
                    String string = TempCharBuffer.toString();
                    String newString = string.trim();

                    Bundle args = new Bundle();

                    args.putString("Message", newString);
                    if (args != null)
                    {
                        mainActivityFragment.setArguments(args);
                    }
                    if(newString.equals("fall"))
                    {
                       // Toast.makeText(getActivity(), "Help I have fallen",Toast.LENGTH_SHORT).show();
                        mainActivityFragment.sendSMSMessage();
                    }
                    else if (newString.equals("pani"))
                    {
                        // Toast.makeText(getActivity(), "Oh shit I need help",Toast.LENGTH_SHORT).show();
                        mainActivityFragment.sendSMSMessage();
                    }
                    else
                        //Toast.makeText(getActivity(), newString,Toast.LENGTH_SHORT).show();
                    break;

                case MESSAGE_WRITE:
                    //Used to send messaages once connected
                    byte[] writeBuf = (byte[])msg.obj;
                    String writeMessage = writeBuf.toString();
                    serverConnectedThread.write(writeMessage.getBytes());
                    Log.i(tag, "message sent");
                    break;

            }
        }

    };



    public class ClientConnectThread extends Thread
    {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
        BluetoothAdapter bluetoothAdapter;
        BluetoothLetRun.ServerConnectedThread serverConnectedThread;
        UUID MY_UUID;


        public ClientConnectThread(BluetoothDevice device, BluetoothAdapter blueAdapter, UUID uuid ) {
            bluetoothAdapter = blueAdapter;
            MY_UUID = uuid;
            BluetoothSocket tmp = null;
            mmDevice = device;
            try {
                tmp = device.createRfcommSocketToServiceRecord(uuid);
            } catch (IOException e) { }
            mmSocket = tmp;
        }

        public void run()
        {
            btAdapter.cancelDiscovery();
            try {
                mmSocket.connect();
            } catch (IOException connectException) {
                /*try {
                    mmSocket.close();
                } catch (IOException closeException) { }*/
                return;
            }

            mHandler.obtainMessage(SUCCESS_CONNECT, mmSocket).sendToTarget();
            ManageConnectedSocket(mmSocket);
        }

        void ManageConnectedSocket(BluetoothSocket Socket)
        {
            serverConnectedThread = new ServerConnectedThread(Socket);
            serverConnectedThread.start();

        }

        ServerConnectedThread GetConnectedThread()
        {
            return serverConnectedThread;
        }


        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }

    }



    private  class ServerConnectedThread extends Thread
    {
        private final BluetoothSocket mmSocket;

        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ServerConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
           // byte[] buffer;  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (true)
            {
                byte[] buffer  = new byte[1024];
                try {
                    // Read from the InputStream

                    bytes = mmInStream.read(buffer);
                    // Send the obtained bytes to the UI activity
                    mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
                            .sendToTarget();
                } catch (IOException e)
                {

                    //break;
                }

            }
        }

        /* Call this from the main activity to send data to the remote device */
        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);
                mHandler.obtainMessage(MESSAGE_WRITE, -1, -1, bytes).sendToTarget();
            } catch (IOException e) { }
        }

        /* Call this from the main activity to shutdown the connection */
        public void cancel()
        {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }


    }
}
