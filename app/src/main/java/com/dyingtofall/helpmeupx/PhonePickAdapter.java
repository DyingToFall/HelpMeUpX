package com.dyingtofall.helpmeupx;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by James W on 8/29/2017.
 */

public class PhonePickAdapter extends ArrayAdapter {
    ArrayList<String> pArrayList;
    Context cons;
    SharedPreferences sPpp = getContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
    SharedPreferences.Editor sPppE;


    public PhonePickAdapter(@NonNull Context context, int textViewResourceId, ArrayList<String> strings) {
        super(context,textViewResourceId, strings);
        pArrayList = strings;
        cons = context;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public String getItem(int i)
    {
        if ((null != this.pArrayList) && !this.pArrayList.isEmpty())
        {
            if ((i >= 0) && (i < this.pArrayList.size()))
                return this.pArrayList.get(i);
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

        conInfo = pArrayList.get(position);
        listContact.setText(conInfo);










        if (sPpp.contains(conInfo))
        {
            v.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
        }


        return v;

    }

}
