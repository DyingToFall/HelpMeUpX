package com.dyingtofall.helpmeupx;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by James W on 9/18/2017.
 */

public class PermsAddFeaturesInitialDialog extends DialogFragment {


    TextView afHeader;
    TextView afMainText;
    Button afbackButton;
    Button afforwardButton;
    int afDialogState;
    Bundle afSetupBundle;


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
        afDialogState = 0;
    }




    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View setupView = getActivity().getLayoutInflater().inflate(R.layout.permissions_back_enhanced_layout, null);
        builder.setView(setupView);

        //last bit of alarms and messaging
        afHeader = (TextView) setupView.findViewById(R.id.backHeaderText);
        afMainText = (TextView) setupView.findViewById(R.id.backMainText);
        afbackButton = (Button) setupView.findViewById(R.id.backButton);
        afforwardButton = (Button) setupView.findViewById(R.id.forwardButton);


        afHeader.setText(R.string.perm_alarmmess_header);
        afMainText.setText(R.string.perm_alarmmess_final_maintext);
        afbackButton.setText("Section 5");
        afforwardButton.setText("Finish");



        afforwardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (afDialogState==0)
                {
                    DialogDismisser dd = new DialogDismisser();
                    dd.dismissAllDialogs(getFragmentManager());
                    Toast.makeText(getActivity(),R.string.perm_finished, Toast.LENGTH_LONG);
                }
                else if (afDialogState==1)
                {
                    afMainText.setText(R.string.perm_addfeat_second_maintext);
                    afDialogState = afDialogState + 1;
                }
                else if (afDialogState>=2)
                {
                    PermsAddFeaturesHelperDialog pfhDialog = new PermsAddFeaturesHelperDialog();
                    pfhDialog.setCancelable(false);
                    pfhDialog.show(getFragmentManager(), "Permissions Add Features Helper Dialog");
                }



            }
        });

        afbackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (afDialogState==0)
                {
                    afHeader.setText(R.string.perm_addfeat_header);
                    afMainText.setText(R.string.perm_addfeat_initial_maintext);
                    afbackButton.setText("Back");
                    afforwardButton.setText("Next");
                    afDialogState= afDialogState + 1;
                }
                else if (afDialogState==1)
                {
                    afHeader.setText(R.string.perm_alarmmess_header);
                    afMainText.setText(R.string.perm_alarmmess_final_maintext);
                    afbackButton.setText("Section 4");
                    afforwardButton.setText("Finish");
                    afDialogState = afDialogState - 1;
                }
                else if (afDialogState==2)
                {
                    afHeader.setText(R.string.perm_addfeat_header);
                    afMainText.setText(R.string.perm_addfeat_initial_maintext);
                    afbackButton.setText("Back");
                    afforwardButton.setText("Next");
                    afDialogState= afDialogState - 1;
                }
                else if (afDialogState>=3)
                {
                    afMainText.setText(R.string.perm_addfeat_second_maintext);
                    afDialogState = 2;
                }

            }
        });

        return builder.create();
    }
}