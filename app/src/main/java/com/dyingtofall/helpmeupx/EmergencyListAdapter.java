package com.dyingtofall.helpmeupx;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by James W on 9/3/2017.
 */

public class EmergencyListAdapter extends ArrayAdapter{

    ArrayList<String> eArrayList;
    Context conte;

    public EmergencyListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<String> strings) {
        super(context, resource, strings);
        eArrayList = strings;
        conte = context;
    }


    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public String getItem(int i)
    {
        if ((null != this.eArrayList) && !this.eArrayList.isEmpty())
        {
            if ((i >= 0) && (i < this.eArrayList.size()))
                return this.eArrayList.get(i);
        }

        return null;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
        {
            v = inflater.inflate(R.layout.list_item, parent, false);
        }


        TextView listContact = (TextView) v.findViewById(R.id.contactInfo);
        String conInfo;

        conInfo = eArrayList.get(position);
        listContact.setText(conInfo);



        return v;

    }
}
