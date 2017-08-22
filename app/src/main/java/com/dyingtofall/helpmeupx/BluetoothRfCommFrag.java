package com.dyingtofall.helpmeupx;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import static android.app.Activity.RESULT_CANCELED;

public class BluetoothRfCommFrag extends DialogFragment
{
    private final String TAG = "Debugging";
    private final static int REQUEST_CODE_ENABLE_BLUETOOTH = 0;
    protected static final int SUCCESS_CONNECT = 0;
    protected static final int MESSAGE_READ = 1;
    public static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    BluetoothAdapter btAdapter;
    ConnectThread connectThread;
    ArrayList<String> arrayListpaired;
    ArrayAdapter<String> listAdapter,adapter;
    ListView listView,listViewPaired;
    Set<BluetoothDevice> deviceArray;
    ArrayList<String> pairedDevices;
    IntentFilter filter;
    BroadcastReceiver receiver;
    BluetoothDevice bdDevice;
    ArrayList<BluetoothDevice> arrayListBluetoothDevices = null;
    ArrayList<BluetoothDevice> arrayListPairedBluetoothDevices;
    ListItemClicked listItemClicked;
    ListItemClickedonPaired listItemClickedonPaired;
    Button button1, button2, button3, button4, button5;
    String tag = "debugging";

    private final Handler mHandler= new Handler()
    {

        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch(msg.what){
                case SUCCESS_CONNECT:

                    ConnectedThread connectedThread = new ConnectedThread((BluetoothSocket)msg.obj);
                    Toast.makeText(getActivity(),"Connect",Toast.LENGTH_LONG).show();

                    String s= "Succesfully connected";
                    connectedThread.write(s.getBytes());
                    Log.i(tag, "connected");
                    break;

                case MESSAGE_READ:
                    byte[] readbuff=(byte[])msg.obj;
                    String  string= readbuff.toString();
                    Toast.makeText(getActivity(), string,Toast.LENGTH_SHORT).show();
                    break;

            }
        }

    };




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View bluetoothView = getActivity().getLayoutInflater().inflate(R.layout.rfcomm_main, null);
        builder.setView(bluetoothView);
        builder.setTitle("BlueTooth Options");

        btAdapter = BluetoothAdapter.getDefaultAdapter();

        arrayListBluetoothDevices = new ArrayList<BluetoothDevice>();
        arrayListpaired = new ArrayList<String>();
        arrayListPairedBluetoothDevices = new ArrayList<BluetoothDevice>();

        adapter= new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.bluetooth_list_row, arrayListpaired);
        listItemClickedonPaired = new ListItemClickedonPaired();
        listViewPaired = (ListView) bluetoothView.findViewById(R.id.listView3);
        listItemClicked = new ListItemClicked();
        listView = (ListView) bluetoothView.findViewById(R.id.listView2);
        listAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.bluetooth_list_item_single_choice);
        listView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        listViewPaired.setAdapter(adapter);
        button1 = (Button) bluetoothView.findViewById(R.id.button1);
        button2 = (Button) bluetoothView.findViewById(R.id.button2);
        button3 = (Button) bluetoothView.findViewById(R.id.button3);
        button4 = (Button) bluetoothView.findViewById(R.id.button4);


        //This button turns on bluetooth
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                btAdapter.enable();

            }
        });

        //This button turns off bluetooth
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                if (btAdapter.isEnabled())
                {

                    listAdapter.clear();
                    btAdapter.disable();
                } else

                {
                    Toast.makeText(getActivity(), "Dont worry it's already off", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //This button searches for a bluetooth device
        button3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                listAdapter.clear();
                arrayListBluetoothDevices.clear();
                btAdapter.startDiscovery();

            }
        });

        //This button makes the device discoverable
        button4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                makeDiscoverable();
            }
        });

        //This button closes bluetooth window
        builder.setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

            }
        });

        init();
        getPairedDevices();
        return builder.create();

    }

    private void init() {

        listView.setOnItemClickListener(listItemClicked);
        listViewPaired.setOnItemClickListener(listItemClickedonPaired);

        filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);

        receiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {

                String Action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(Action)) {
                    Toast.makeText(getActivity(), "One Device Found", Toast.LENGTH_SHORT).show();
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                    if (arrayListBluetoothDevices.size() < 1) // this checks if the size of bluetooth device is 0,then add the
                    {                                           // device to the arraylist.
                        listAdapter.add(device.getName() + "\n" + device.getAddress());
                        arrayListBluetoothDevices.add(device);
                        listAdapter.notifyDataSetChanged();
                    }

                    else
                    {
                        boolean flag = true;    // flag to indicate that particular device is already in the arlist or not
                        for (int i = 0; i < arrayListBluetoothDevices.size(); i++) {
                            if (device.getAddress().equals(arrayListBluetoothDevices.get(i).getAddress())) {
                                flag = false;
                            }
                        }
                        if (flag == true) {
                            listAdapter.add(device.getName() + "\n" + device.getAddress());
                            arrayListBluetoothDevices.add(device);
                            listAdapter.notifyDataSetChanged();
                        }
                    }

                }
            }
        };
        getActivity().registerReceiver(receiver, filter);


    }

    private void createBond(BluetoothDevice btDevice){

        try
        {
            Method method = btDevice.getClass().getMethod("createBond", (Class[]) null);
            method.invoke(btDevice, (Object[]) null);
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
    }

    private void unpairDevice(BluetoothDevice device) {
        try {
            Method method = device.getClass().getMethod("removeBond", (Class[]) null);
            method.invoke(device, (Object[]) null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getPairedDevices() {

        Set<BluetoothDevice> pairedDevice = btAdapter.getBondedDevices();
        if(pairedDevice.size()>0)
        {
            for(BluetoothDevice device : pairedDevice)
            {
                arrayListpaired.add(device.getName()+"\n"+device.getAddress());
                arrayListPairedBluetoothDevices.add(device);
            }
        }
        adapter.notifyDataSetChanged();
    }


    public class ListItemClicked implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // TODO Auto-generated method stub
            bdDevice = arrayListBluetoothDevices.get(position);

            Log.i("Log", "The device : " + bdDevice.toString());

            getPairedDevices();
            createBond(bdDevice);
            adapter.notifyDataSetChanged();

            Log.i("Log", "The bond is created: with" + bdDevice.toString());


        }
    }


    private  class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

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

            //mHandler.obtainMessage(SUCCESS_CONNECT, mmSocket).sendToTarget();

        }


        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }

    }


    private  class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
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
            byte[] buffer;  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    buffer  = new byte[1024];
                    bytes = mmInStream.read(buffer);
                    // Send the obtained bytes to the UI activity
                    mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
                            .sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) { }
        }

        /* Call this from the main activity to shutdown the connection */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        btAdapter.cancelDiscovery();
        getActivity().unregisterReceiver(receiver);
    }



    class ListItemClickedonPaired implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
            bdDevice = arrayListPairedBluetoothDevices.get(position);

            unpairDevice(bdDevice);
            //arrayListPairedBluetoothDevices.clear();

        }
    }

    private void makeDiscoverable() {
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 150);
        startActivity(discoverableIntent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getActivity().getApplicationContext(), "Bluetooth must be enabled to start scanning", Toast.LENGTH_SHORT).show();

        } else

        {
            Toast.makeText(getActivity().getApplicationContext(), "Click on TURN_OFF to disable bluetooth", Toast.LENGTH_LONG).show();
        }

    }

}
