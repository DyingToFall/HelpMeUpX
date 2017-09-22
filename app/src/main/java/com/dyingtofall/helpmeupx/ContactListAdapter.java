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
 * Created by James W on 8/16/2017.
 */

public class ContactListAdapter extends ArrayAdapter<ContactListInfo> {

    ArrayList<ContactListInfo> cArrayList;
    Context cons;
    SharedPreferences sP = getContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
    SharedPreferences.Editor sPE;


    public ContactListAdapter(@NonNull Context context, int textViewResourceId, ArrayList<ContactListInfo> objects) {
        super(context,textViewResourceId, objects);
        cArrayList = objects;
        cons = context;


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
        if (convertView == null)
        {
            v = inflater.inflate(R.layout.list_item, parent, false);
        }
        TextView listContact = (TextView) v.findViewById(R.id.contactInfo);
        //String contactInformation;
        String contactName;

        /*
        if (viewHighlight > -1)
        {
            v.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
        }
        */
        //might not need this since using arrayadapter not baseadapter
        //ContactListInfo cLInfo = (ContactListInfo) this.getItem(position);

        /*
        contactInformation = cArrayList.get(position).getContactName() + "\n" +
                cArrayList.get(position).getContactPhone();

        listContact.setText(contactInformation);
        */

        contactName = cArrayList.get(position).getContactName();
        listContact.setText(contactName);


        Integer contactNums = sP.getInt("EmergencySize",0);

        if (contactNums > 0)
        {
            for (int i = 0; i < contactNums; i++)
            {
                String tempName = sP.getString("EmName"+Integer.toString(i),"Failed to grab Number Error");
                if (tempName.equals(contactName))
                {
                    v.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
                }
            }
        }



        /*
        ContactListDialogFragment cdView = new ContactListDialogFragment();

        if (cdView.sPref.contains(contactName))
        {
            v.setBackgroundColor(getContext().getResources().getColor(R.color.colorGreen));
        }
        */

        /*
        //checking if the checkbox is active or not and checking it if it is
        if (cArrayList.get(position).getContactBox() == true)
        {
            checkBox.setChecked(true);
        }
        else
        {
            checkBox.setChecked(false);
        }

*/
        return v;

    }


}



