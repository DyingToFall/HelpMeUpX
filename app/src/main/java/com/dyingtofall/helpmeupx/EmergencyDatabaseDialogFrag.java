package com.dyingtofall.helpmeupx;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Home on 9/22/2017.
 */



public class EmergencyDatabaseDialogFrag extends DialogFragment
{
    TextView emergencyView;
    ListView emergencyList;
    ArrayAdapter<String> emergencyListAdapter;
    ArrayList<String> emergencyArrayList;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.database_emergency_main, null);
        builder.setView(view);

        emergencyView = (TextView) view.findViewById(R.id.textViewEmergency);
        emergencyList = (ListView) view.findViewById(R.id.listViewEmercengy);
        emergencyArrayList = new ArrayList<>();

        String testString = "Fall 9/22/2017";
        String testString1 = "Fall 9/20/2017";
        String testString2 = "Fall 9/19/2017";
        String testString3 = "Panic 9/18/2017";
        String testString4 = "Fall 9/15/2017";
        String testString5 = "Panic 9/10/2017";
        emergencyArrayList.add(testString);
        emergencyArrayList.add(testString1);
        emergencyArrayList.add(testString2);
        emergencyArrayList.add(testString3);
        emergencyArrayList.add(testString4);
        emergencyArrayList.add(testString5);
        emergencyListAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.database_list_emergency, emergencyArrayList);
        emergencyList.setAdapter(emergencyListAdapter);
        emergencyListAdapter.notifyDataSetChanged();



        //This button closes bluetooth window
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
