package com.dyingtofall.helpmeupx;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;
//THIS CLASS IS NOT USED BUT SAVED IF NEEDED
public class BlueToothDataAdapter extends BaseAdapter
{
    private ArrayList<String> devices;
    private LayoutInflater layoutInflater;
    BluetoothAdapter blueToothAdapter;

    private TextView getTextView(View view, int textViewId)
    {
        return (TextView) view.findViewById(textViewId);
    }

    public BlueToothDataAdapter(Context context, ArrayList<String> devices)
    {
        this.devices = devices;
        this.layoutInflater = LayoutInflater.from(context);
        blueToothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    public int getCount()
    {
        return ((null != this.devices) ? this.devices.size() : 0);
    }

    @Override
    public Object getItem(int i)
    {
        if ((null != this.devices) && !this.devices.isEmpty())
        {
            if ((i >= 0) && (i < this.devices.size()))
            {
                return this.devices.get(i);
            }
        }
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container)
    {
        if (null == convertView)
        {
            convertView = this.layoutInflater.inflate(R.layout.bluetooth_list_row, null);
            if (null == convertView)
                return null;
        }

        TextView textViewName = (TextView) convertView.findViewById(R.id.textViewName);

        BlueToothDialogFragment bluetooth = (BlueToothDialogFragment) this.getItem(position);
        if (null != bluetooth)
        {
            Set<BluetoothDevice> pairedDevices = blueToothAdapter.getBondedDevices();
            for(BluetoothDevice bt: pairedDevices)
            {
                devices.add(bt.getName());
            }
            convertView.setTag(bluetooth);
        }

        return convertView;
    }
}
