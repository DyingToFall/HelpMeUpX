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
 * Created by James W on 9/17/2017.
 */

public class PermsAlarmMessageInitialDialog extends DialogFragment {


    TextView amHeader;
    TextView amMainText;
    Button ambackButton;
    Button amforwardButton;
    int amDialogState;
    Bundle amSetupBundle;


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
        amDialogState = 0;
    }




    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View setupView = getActivity().getLayoutInflater().inflate(R.layout.permissions_back_compat_layout, null);
        builder.setView(setupView);

        //last bit of contacts
        amHeader = (TextView) setupView.findViewById(R.id.backHeaderText);
        amMainText = (TextView) setupView.findViewById(R.id.backMainText);
        ambackButton = (Button) setupView.findViewById(R.id.backButton);
        amforwardButton = (Button) setupView.findViewById(R.id.forwardButton);


        amHeader.setText(R.string.perm_contacts_header);
        amMainText.setText(R.string.perm_contacts_final_text);
        ambackButton.setText("Section 4");
        amforwardButton.setText("Finish");



        amforwardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (amDialogState==0)
                {
                    DialogDismisser dd = new DialogDismisser();
                    dd.dismissAllDialogs(getFragmentManager());
                    Toast.makeText(getActivity(),R.string.perm_finished, Toast.LENGTH_LONG);
                }
                else if (amDialogState==1)
                {
                    amMainText.setText(R.string.perm_alarmmess_initial_secondtext);
                    amDialogState= amDialogState + 1;
                }
                else if (amDialogState==2)
                {
                    amMainText.setText(R.string.perm_alarmmess_initial_thirdtext);
                    amDialogState= amDialogState + 1;
                }
                else if (amDialogState>=3)
                {
                    PermsAlarmMessageHelperDialog pmhDialog = new PermsAlarmMessageHelperDialog();
                    pmhDialog.setCancelable(false);
                    pmhDialog.show(getFragmentManager(), "Permissions Alarm Message Helper Dialog");
                }



            }
        });

        ambackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (amDialogState==0)
                {
                    amHeader.setText(R.string.perm_alarmmess_header);
                    amMainText.setText(R.string.perm_alarmmess_initial_maintext);
                    ambackButton.setText("Back");
                    amforwardButton.setText("Next");
                    amDialogState= amDialogState + 1;
                }
                else if (amDialogState==1)
                {
                    amHeader.setText(R.string.perm_contacts_header);
                    amMainText.setText(R.string.perm_contacts_final_text);
                    ambackButton.setText("Section 4");
                    amforwardButton.setText("Finish");
                    amDialogState = amDialogState - 1;
                }
                else if (amDialogState==2)
                {
                    amHeader.setText(R.string.perm_alarmmess_header);
                    amMainText.setText(R.string.perm_alarmmess_initial_maintext);
                    ambackButton.setText("Back");
                    amforwardButton.setText("Next");
                    amDialogState= amDialogState + 1;
                }
                else if (amDialogState==3)
                {
                    amMainText.setText(R.string.perm_alarmmess_initial_secondtext);
                    amDialogState= amDialogState - 1;
                }
                else if (amDialogState>=4)
                {
                    amMainText.setText(R.string.perm_alarmmess_initial_thirdtext);
                    amDialogState= 3;
                }

            }
        });

        return builder.create();
    }
}