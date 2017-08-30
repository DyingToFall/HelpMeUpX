package com.dyingtofall.helpmeupx;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by James W on 8/29/2017.
 */

public class PhonePickerDialogFragment extends DialogFragment {

    ListView pContactList;
    ArrayList<String> pLArrayList;
    PhonePickAdapter pLAdapter;
    TextView textView;



    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View plView = getActivity().getLayoutInflater().inflate(R.layout.emergency_list_layout, null);
        builder.setView(plView);
        builder.setTitle("Choose Phone Numbers");



        pLArrayList = new ArrayList<String>();
        pLAdapter = new PhonePickAdapter(getContext(), R.layout.list_item, pLArrayList);
        pContactList = (ListView) plView.findViewById(R.id.listViewEmergencyList);
        textView = (TextView) plView.findViewById(R.id.contactInfo);
        int phoneCounter = 0;

        Bundle mArgs = getArguments();
        int currentInt = mArgs.getInt("DialogPosition");
        String currentName = mArgs.getString("PhoneName");

        String[] extractedPhones = mArgs.getStringArray("PhoneNumbers");

        pLArrayList.add(currentName);
        pContactList.setAdapter(pLAdapter);

        while ((extractedPhones[phoneCounter] != "")&&(extractedPhones[phoneCounter] != null)&&(phoneCounter != 12))
        {
            pLArrayList.add(extractedPhones[phoneCounter]);
            phoneCounter++;
            pContactList.setAdapter(pLAdapter);
        }




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

            //contactList.setAdapter(cLAdapter);

        pContactList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        pContactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Mark this view row as selected.
                view.setSelected(true);
                view.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                Toast.makeText(getContext(), "item clicked : \n" + pContactList.getPositionForView(view), Toast.LENGTH_SHORT).show();
                EmergencyListDialogFragment eLD = new EmergencyListDialogFragment();
                eLD.show(getFragmentManager(),"Emergency List Dialog");
            }
        });


        return builder.create();
    }




}
