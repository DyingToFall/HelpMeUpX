package com.dyingtofall.helpmeupx;

import android.content.Context;
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


    public PhonePickAdapter(@NonNull Context context, int textViewResourceId, ArrayList<String> strings) {
        super(context,textViewResourceId, strings);
        pArrayList = strings;

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
        v = inflater.inflate(R.layout.list_item, parent, false);


        TextView listContact = (TextView) v.findViewById(R.id.contactInfo);
        String conInfo;

        conInfo = pArrayList.get(position);
        listContact.setText(conInfo);

        /*
        String contactInformation;
        String extractedPhones[];
        StringBuffer contactNumbers;
        int phoneCounter = 0;

        extractedPhones = pArrayList.get(position).getContactPhones();

        contactNumbers = new StringBuffer();

        while ((extractedPhones[phoneCounter] != "")&&(extractedPhones[phoneCounter] != null)&&(phoneCounter != 12))
        {
            contactNumbers.append(extractedPhones[phoneCounter] + "\n");
            phoneCounter++;
        }


        contactInformation = pArrayList.get(position).getContactName()
        + "\n" + contactNumbers;
        listContact.setText(contactInformation);

        */


        return v;

    }

}
