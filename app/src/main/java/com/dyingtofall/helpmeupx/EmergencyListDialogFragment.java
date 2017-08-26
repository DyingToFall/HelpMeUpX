package com.dyingtofall.helpmeupx;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by James W on 8/15/2017.
 */

public class EmergencyListDialogFragment extends DialogFragment {

    ListView emergencyList;
    ArrayList<String> eArrayList;
    ArrayAdapter<String> eAdapter;
    Cursor cursorE;

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View elView = getActivity().getLayoutInflater().inflate(R.layout.contact_list_layout, null);
        builder.setView(elView);
        builder.setTitle("Emergency Contacts");

        return builder.create();
    }
}
