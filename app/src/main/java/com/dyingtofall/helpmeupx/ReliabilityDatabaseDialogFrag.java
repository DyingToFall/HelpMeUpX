package com.dyingtofall.helpmeupx;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Home on 9/22/2017.
 */

public class ReliabilityDatabaseDialogFrag extends DialogFragment
{

    TextView reliabilityView;
    ListView reliabilityList;
    ArrayAdapter<String> reliabilityListAdapter;
    ArrayList<String> reliabilityArrayList;

    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.database_reliability_main, null);
        builder.setView(view);
        reliabilityArrayList = new ArrayList<>();
        reliabilityView = (TextView) view.findViewById(R.id.textViewReliability);
        reliabilityList = (ListView) view.findViewById(R.id.listViewReliability);
        String testString = "Bluetooth connection lost 9/21/2017";
        String testString1 = "Bluetooth connection lost 9/18/2017";
        String testString2 = "Low battery 9/16/2017";
        String testString3 = "Bluetooth connection lost 9/15/2017";
        String testString4 = "Low battery 9/14/2017";
        String testString5 = "Bluetooth connection lost 9/11/2017";
        reliabilityArrayList.add(testString);
        reliabilityArrayList.add(testString1);
        reliabilityArrayList.add(testString2);
        reliabilityArrayList.add(testString3);
        reliabilityArrayList.add(testString4);
        reliabilityArrayList.add(testString5);
        reliabilityListAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.database_list_emergency, reliabilityArrayList);
        reliabilityList.setAdapter(reliabilityListAdapter);
        reliabilityListAdapter.notifyDataSetChanged();

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
