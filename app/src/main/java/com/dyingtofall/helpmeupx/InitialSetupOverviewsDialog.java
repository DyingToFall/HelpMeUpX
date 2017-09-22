package com.dyingtofall.helpmeupx;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by James W on 9/10/2017.
 */

public class InitialSetupOverviewsDialog extends DialogFragment {

    Button nextButton;
    TextView mainHeader;
    TextView mainText;
    int dialogState;

   @Override
   public void onStart()
   {
       super.onStart();
       Dialog dialog = getDialog();
       if (dialog != null)
       {
           int width = ViewGroup.LayoutParams.MATCH_PARENT;
           int height = ViewGroup.LayoutParams.MATCH_PARENT;
           dialog.getWindow().setLayout(width, height);
       }
       dialogState = 0;
   }




    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View setupView = getActivity().getLayoutInflater().inflate(R.layout.is_overview_layout, null);
        builder.setView(setupView);

        mainHeader = (TextView) setupView.findViewById(R.id.overviewHeaderText);
        mainText = (TextView) setupView.findViewById(R.id.overviewMainText);
        nextButton = (Button) setupView.findViewById(R.id.overviewButton);


        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
               if (dialogState == 0)
               {
                   mainText.setText(R.string.is_intro_secondarytext);
               }
               else if (dialogState == 1)
               {
                   mainHeader.setText(R.string.perm_intro_header);
                   mainText.setText(R.string.perm_intro_maintext);
               }
               else
               {
                   PermissionsInitialDialog piDialog = new PermissionsInitialDialog();
                   piDialog.setCancelable(false);
                   piDialog.show(getFragmentManager(), "Permissions Initial Dialog");
                   //dismiss();
               }

               dialogState = dialogState + 1;

            }
        });

        return builder.create();
    }
}


