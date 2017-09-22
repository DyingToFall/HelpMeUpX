package com.dyingtofall.helpmeupx;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by James W on 9/12/2017.
 */

public class PermsBluetoothSetupDialog extends DialogFragment {


    TextView btHeader;
    TextView btMainText;
    Button backButton;
    Button forwardButton;
    int bDialogState;
    Bundle btSetupBundle;
    boolean finale;


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
        bDialogState = 0;
    }




    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View setupView = getActivity().getLayoutInflater().inflate(R.layout.permissions_back_compat_layout, null);
        builder.setView(setupView);


        btHeader = (TextView) setupView.findViewById(R.id.backHeaderText);
        btMainText = (TextView) setupView.findViewById(R.id.backMainText);
        backButton = (Button) setupView.findViewById(R.id.backButton);
        forwardButton = (Button) setupView.findViewById(R.id.forwardButton);
        backButton.setVisibility(View.INVISIBLE);

        btSetupBundle = getArguments();
        boolean backOrNot = btSetupBundle.getBoolean("backPressed", false);

        if (backOrNot==true)
        {
            backButton.setVisibility(View.VISIBLE);
            btMainText.setText(R.string.perm_bluetooth_initial_pretext);
            bDialogState=1;
        }


        forwardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (bDialogState==0)
                {
                    btMainText.setText(R.string.perm_bluetooth_initial_pretext);
                    backButton.setVisibility(View.VISIBLE);
                    bDialogState= bDialogState+1;
                }
                else if (bDialogState>=1)
                {
                    PermsBluetoothHelperDialog pbhDialog = new PermsBluetoothHelperDialog();
                    pbhDialog.setCancelable(false);
                    pbhDialog.show(getFragmentManager(), "Permissions Bluetooth Helper Dialog");
                }


            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {


                        btMainText.setText(R.string.perm_bluetooth_initial_maintext);
                        backButton.setVisibility(View.INVISIBLE);
                        bDialogState = 0;


            }
        });

        return builder.create();
    }
}