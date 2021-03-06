package com.dyingtofall.helpmeupx;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by James W on 8/15/2017.
 */

public class EmergencyListDialogFragment extends DialogFragment {

    ListView emergencyList;
    ArrayList<String> eArrayList;
    HashSet<String> eHashSet;
    ArrayAdapter<String> eAdapter;
    SharedPreferences spEm;
    SharedPreferences.Editor sEdit;


    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View elView = getActivity().getLayoutInflater().inflate(R.layout.actual_emergency_list_layout, null);
        builder.setView(elView);
        builder.setTitle("Emergency Contacts");

        eArrayList = new ArrayList<String>();
        eHashSet = new HashSet<String>();
        eAdapter = new EmergencyListAdapter(getContext(), R.layout.list_item, eArrayList);
        emergencyList = (ListView) elView.findViewById(R.id.listViewEl);
        TextView eV = (TextView) elView.findViewById(R.id.elView);


        spEm = getContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        sEdit = spEm.edit();

        if (spEm.contains("EmergencySize"))
        {
           int ourNum = spEm.getInt("EmergencySize",0);
            if (ourNum > 0) {
                for (int i = 0; i < ourNum; i++)
                {
                    String tempStrings = spEm.getString("EmNum"+Integer.toString(i),"Failed to grab Number Error");
                    String tempNames = spEm.getString("EmName"+Integer.toString(i),"Failed to grab Number Error");
                    eArrayList.add(i,tempStrings);
                    eHashSet.add(tempNames);
                }
            }
            String stuffstring = getString(R.string.emergency_dialog_header);
            String tempstringsize = "You currently have " + Integer.toString(eHashSet.size()) + " contacts ";
            String tempstringnum = "and you are using " + Integer.toString(eArrayList.size()) + " phone numbers.";
            eV.setText(stuffstring + tempstringsize + tempstringnum + "\n\n");
        }
        else
        {
            eV.setText(R.string.empty_emergency_list);
        }
        emergencyList.setAdapter(eAdapter);
        //Toast.makeText(getContext(), "EmergencySize is " + Integer.toString(spEm.getInt("EmergencySize",0)), Toast.LENGTH_LONG).show();

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("Modify", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
                ContactListDialogFragment cLDFrag = new ContactListDialogFragment();
                cLDFrag.setCancelable(false);
                cLDFrag.show(getFragmentManager(),"Contact List Dialog");
            }
        });

        return builder.create();
    }
}
