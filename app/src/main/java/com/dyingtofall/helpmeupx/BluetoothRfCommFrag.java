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
import android.provider.Telephony;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import static android.app.Activity.RESULT_CANCELED;


public class BluetoothRfCommFrag extends DialogFragment
{
    private final String TAG = "Debugging";
    private final static int REQUEST_CODE_ENABLE_BLUETOOTH = 0;
    protected static final int SUCCESS_CONNECT = 0;
    private ArrayAdapter<String> chatArrayAdapter;
    private StringBuffer outStringBuffer;
    private EditText etMain;
    protected static final int MESSAGE_READ = 1;
    public static final int MESSAGE_WRITE = 2;
    public static final int STATE_CONNECTED = 3;
    public static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    BluetoothAdapter btAdapter;
    BluetoothRfCommFrag bluetoothRfCommFrag;
    ClientConnectThread clientConnectThread;

    ServerConnectedThread serverConnectedThread;
    ArrayList<String> arrayListpaired;
    ArrayAdapter<String> listAdapter,adapter;
    ListView listView,listViewPaired;
    Set<BluetoothDevice> deviceArray;
    ArrayList<String> pairedDevices;
    IntentFilter filter;
    BroadcastReceiver receiver;
    BluetoothDevice bdDevice;
    protected SMSHeadlessClass sendText;

    ArrayList<BluetoothDevice> arrayListBluetoothDevices = null;
    ArrayList<BluetoothDevice> arrayListPairedBluetoothDevices;
    ListItemClicked listItemClicked;
    ListItemClickedonPaired listItemClickedonPaired;
    Button button1, button2, button3, button4, btnSend;
    String tag = "debugging";

    private final Handler mHandler= new Handler()
    {

        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch(msg.what){
                case SUCCESS_CONNECT:
                    //Connected via RfComm
                    serverConnectedThread = new ServerConnectedThread((BluetoothSocket)msg.obj);
                    Toast.makeText(getActivity(),"Connect",Toast.LENGTH_LONG).show();
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
                    SMSHeadlessClass sendMessage = new SMSHeadlessClass();
                    sendMessage.setArguments(args);
                    if(newString.equals("fall"))
                    {
                        Toast.makeText(getActivity(), "Help I have fallen",Toast.LENGTH_SHORT).show();
                        sendMessage.sendSMSMessage();
                    }
                    else if (newString.equals("panic"))
                    {
                        Toast.makeText(getActivity(), "Oh shit I need help",Toast.LENGTH_SHORT).show();
                        sendMessage.sendSMSMessage();
                    }
                    else
                        Toast.makeText(getActivity(), newString,Toast.LENGTH_SHORT).show();
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
        btnSend = (Button) bluetoothView.findViewById(R.id.btnSend);
        etMain = (EditText) bluetoothView.findViewById(R.id.etMain);




        //This button turns on bluetooth
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                btAdapter.enable();

            }
        });

        etMain.setOnEditorActionListener(mWriteListener);

        btnSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String message = etMain.getText().toString();
                sendMessage(message);
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


   //For in app messaging over bluetooth
    private void sendMessage (String message)
    {

        outStringBuffer = new StringBuffer("");
        if(message.length() > 0)
        {
            byte[] send = message.getBytes();

            ServerConnectedThread r;
            r = serverConnectedThread;
            r.write(send);
            outStringBuffer.setLength(0);
            etMain.setText(outStringBuffer);

        }
    }

    //For in app messaging over bluetooth
    private TextView.OnEditorActionListener mWriteListener = new TextView.OnEditorActionListener() {
        public boolean onEditorAction(TextView view, int actionId,
                                      KeyEvent event) {
            if (actionId == EditorInfo.IME_NULL
                    && event.getAction() == KeyEvent.ACTION_UP) {
                String message = view.getText().toString();
                sendMessage(message);
            }
            return true;
        }
    };



    private void init() {

        listView.setOnItemClickListener(listItemClicked);
        listViewPaired.setOnItemClickListener(listItemClickedonPaired);
        final ClientConnectThread clientConnectThread;


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
            ClientConnectThread clientConnectThread = new ClientConnectThread(bdDevice, btAdapter, MY_UUID);
            Log.i("Log", "The device : " + bdDevice.toString());

            getPairedDevices();
            createBond(bdDevice);
            adapter.notifyDataSetChanged();


            Log.i("Log", "The bond is created: with" + bdDevice.toString());


        }


    }


    private class ClientConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
        BluetoothAdapter bluetoothAdapter;
        ServerConnectedThread serverConnectedThread;
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



    private  class ServerConnectedThread extends Thread {
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
            byte[] buffer;  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (true)
            {
                buffer  = new byte[1024];
                try {
                    // Read from the InputStream

                    bytes = mmInStream.read(buffer);
                    // Send the obtained bytes to the UI activity
                    mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
                            .sendToTarget();
                } catch (IOException e)
                {

                    break;
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
            ClientConnectThread clientConnectThread = new ClientConnectThread(bdDevice, btAdapter, MY_UUID);
            clientConnectThread.start();
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

