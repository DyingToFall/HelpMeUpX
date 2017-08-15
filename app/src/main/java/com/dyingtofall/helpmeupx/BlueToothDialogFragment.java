package com.dyingtofall.helpmeupx;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.Dialog;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Set;

public class BlueToothDialogFragment extends DialogFragment
{
    Button onBtn, offBtn, discBtn, listBtn;
    ListView list;
    public BlueToothDataAdapter blueToothDataAdapter;
    private static final int REQUEST_ENABLED = 0;
    private static final int REQUEST_DISCOVERABLE = 0;

    BluetoothAdapter blueToothAdapter;

    @Override
    public Dialog onCreateDialog(Bundle bundle)
    {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View bluetoothView = getActivity().getLayoutInflater().inflate(R.layout.bluetooth_fragment, null);
        builder.setView(bluetoothView);
        builder.setTitle("BlueTooth Options");

        onBtn = (Button) bluetoothView.findViewById(R.id.buttonOn);
        offBtn = (Button) bluetoothView.findViewById(R.id.buttonOff);
        discBtn = (Button) bluetoothView.findViewById(R.id.buttonDiscoverable);
       // listBtn = (Button) bluetoothView.findViewById(R.id.buttonList);
        //list= (ListView) bluetoothView.findViewById(R.id.list);

        blueToothAdapter = BluetoothAdapter.getDefaultAdapter();


        onBtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view)
        {
            //Turns On Bluetooth
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_ENABLED);

    }});

        offBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                //Turns off BlueTooth
                blueToothAdapter.disable();
            }});

        discBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if(!blueToothAdapter.isDiscovering())
                {
                    //Makes device discoverable
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    //startActivityForResult(intent, REQUEST_DISCOVERABLE);
                    startActivityForResult(intent, 0);
                }
            }});

        listBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                //Lists connected devices
                Set<BluetoothDevice> pairedDevices = blueToothAdapter.getBondedDevices();
                ArrayList<String> devices = new ArrayList();
                for(BluetoothDevice bt: pairedDevices)
                {
                    devices.add(bt.getName());
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.bluetooth_list_row, devices);
                list.setAdapter(arrayAdapter);
            }});

        builder.setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

            }
        });

        return builder.create();
    }



}
