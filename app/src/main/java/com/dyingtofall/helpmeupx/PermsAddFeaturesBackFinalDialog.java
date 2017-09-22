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
 * Created by James W on 9/20/2017.
 */

public class PermsAddFeaturesBackFinalDialog extends DialogFragment {


    TextView finHeader;
    TextView finMainText;
    Button finbackButton;
    Button finforwardButton;
    int finDialogState;
    Bundle finSetupBundle;


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
        finDialogState = 0;
    }




    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View setupView = getActivity().getLayoutInflater().inflate(R.layout.permissions_back_compat_layout, null);
        builder.setView(setupView);

        finHeader = (TextView) setupView.findViewById(R.id.backHeaderText);
        finMainText = (TextView) setupView.findViewById(R.id.backMainText);
        finbackButton = (Button) setupView.findViewById(R.id.backButton);
        finforwardButton = (Button) setupView.findViewById(R.id.forwardButton);
        finbackButton.setVisibility(View.INVISIBLE);

        finHeader.setText(R.string.perm_addfeat_header);
        finMainText.setText(R.string.perm_addfeat_back_process_text);

        finforwardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (finDialogState==0)
                {
                    finMainText.setText(R.string.perm_addfeat_back_process_moretext);
                    finbackButton.setVisibility(View.VISIBLE);
                    finDialogState=finDialogState + 1;
                }
                else if (finDialogState==1)
                {
                    finMainText.setText(R.string.perm_addfeat_back_process_finaltext);
                    finDialogState=finDialogState + 1;
                }
                else if (finDialogState==2)
                {
                    DialogDismisser dd = new DialogDismisser();
                    dd.dismissAllDialogs(getFragmentManager());
                    Toast.makeText(getActivity(),R.string.perm_finished, Toast.LENGTH_LONG);

                }


            }
        });

        finbackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (finDialogState==0||finDialogState==1)
                {
                    finbackButton.setVisibility(View.INVISIBLE);
                    finMainText.setText(R.string.perm_addfeat_back_process_text);
                    finDialogState=0;
                }
                else if (finDialogState==2)
                {
                    finMainText.setText(R.string.perm_addfeat_back_process_moretext);
                    finDialogState=finDialogState-1;
                }
                else if (finDialogState>=3)
                {
                    finMainText.setText(R.string.perm_addfeat_back_process_finaltext);
                    finDialogState=3;
                }



            }
        });

        return builder.create();
    }
}

