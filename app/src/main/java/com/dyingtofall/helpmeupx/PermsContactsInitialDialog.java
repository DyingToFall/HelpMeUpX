package com.dyingtofall.helpmeupx;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by James W on 9/16/2017.
 */

public class PermsContactsInitialDialog extends DialogFragment {


    TextView cHeader;
    TextView cMainText;
    Button cbackButton;
    Button cforwardButton;
    int cDialogState;
    Bundle cSetupBundle;


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
        cDialogState = 0;
    }




    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View setupView = getActivity().getLayoutInflater().inflate(R.layout.permissions_back_enhanced_layout, null);
        builder.setView(setupView);

        //last bit of bluetooth
        cHeader = (TextView) setupView.findViewById(R.id.backHeaderText);
        cMainText = (TextView) setupView.findViewById(R.id.backMainText);
        cbackButton = (Button) setupView.findViewById(R.id.backButton);
        cforwardButton = (Button) setupView.findViewById(R.id.forwardButton);
        cbackButton.setVisibility(View.INVISIBLE);

        cMainText.setText(R.string.perm_bluetooth_final_text);



        cforwardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (cDialogState==0)
                {
                    cHeader.setText(R.string.perm_contacts_header);
                    cMainText.setText(R.string.perm_contacts_initial_maintext);
                    cbackButton.setVisibility(View.VISIBLE);
                    cDialogState = cDialogState+1;
                }
                else if (cDialogState==1)
                {
                    cMainText.setText(R.string.perm_contacts_initial_pretext);
                    cDialogState = cDialogState+1;
                }
                else if (cDialogState>=2)
                {
                    PermsContactsHelperDialog pchDialog = new PermsContactsHelperDialog();
                    pchDialog.setCancelable(false);
                    pchDialog.show(getFragmentManager(), "Permissions Contacts Helper Dialog");
                }


            }
        });

        cbackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {

                if (cDialogState == 0 || cDialogState == 1)
                {
                    cHeader.setText(R.string.perm_bluetooth_header);
                    cMainText.setText(R.string.perm_bluetooth_final_text);
                    cbackButton.setVisibility(View.INVISIBLE);
                    cDialogState = 0;
                }
                else
                {
                    cHeader.setText(R.string.perm_contacts_header);
                    cMainText.setText(R.string.perm_contacts_initial_maintext);
                    cDialogState =1;
                }

            }
        });

        return builder.create();
    }
}