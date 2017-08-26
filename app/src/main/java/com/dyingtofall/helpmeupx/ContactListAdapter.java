package com.dyingtofall.helpmeupx;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by James W on 8/16/2017.
 */

public class ContactListAdapter extends ArrayAdapter<ContactListInfo> {

    ArrayList<ContactListInfo> cArrayList;


    public ContactListAdapter(@NonNull Context context, int textViewResourceId, ArrayList<ContactListInfo> objects) {
        super(context,textViewResourceId, objects);
        cArrayList = objects;

    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public ContactListInfo getItem(int i)
    {
        if ((null != this.cArrayList) && !this.cArrayList.isEmpty())
        {
            if ((i >= 0) && (i < this.cArrayList.size()))
                return this.cArrayList.get(i);
        }

        return null;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.contact_list_row_layout, null);
        TextView listContact = (TextView) v.findViewById(R.id.listContactInfo);
        CheckBox checkBox = (CheckBox) v.findViewById(R.id.listCheckBox);
        String contactInformation;

        //might not need this since using arrayadapter not baseadapter
        //ContactListInfo cLInfo = (ContactListInfo) this.getItem(position);

        contactInformation = cArrayList.get(position).getContactName() + "\n" +
                cArrayList.get(position).getContactPhone();

        listContact.setText(contactInformation);


        //checking if the checkbox is active or not and checking it if it is
        if (cArrayList.get(position).getContactBox() == true)
        {
            checkBox.setChecked(true);
        }
        else
        {
            checkBox.setChecked(false);
        }

        return v;

    }

}



