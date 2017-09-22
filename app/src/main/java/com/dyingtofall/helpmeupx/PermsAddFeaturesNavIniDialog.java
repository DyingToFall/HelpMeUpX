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

public class PermsAddFeaturesNavIniDialog extends DialogFragment {


    TextView anHeader;
    TextView anMainText;
    Button anbackButton;
    Button anforwardButton;
    int anDialogState;
    Bundle anSetupBundle;


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
        anDialogState = 0;
    }




    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View setupView = getActivity().getLayoutInflater().inflate(R.layout.permissions_back_compat_layout, null);
        builder.setView(setupView);

        //last bit of alarms and messaging
        anHeader = (TextView) setupView.findViewById(R.id.backHeaderText);
        anMainText = (TextView) setupView.findViewById(R.id.backMainText);
        anbackButton = (Button) setupView.findViewById(R.id.backButton);
        anforwardButton = (Button) setupView.findViewById(R.id.forwardButton);
        anbackButton.setVisibility(View.INVISIBLE);

        anHeader.setText(R.string.perm_addfeat_header);
        anMainText.setText(R.string.perm_addfeat_settings_maintext);

        anforwardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (anDialogState==0)
                {
                    anMainText.setText(R.string.perm_addfeat_settings_othertext);
                    anbackButton.setVisibility(View.VISIBLE);
                    anDialogState=anDialogState + 1;
                }
                else if (anDialogState==1)
                {
                    anMainText.setText(R.string.perm_addfeat_navigation_maintext);
                    anDialogState=anDialogState + 1;
                }
                else if (anDialogState==2)
                {
                    PermsAddFeaturesNaviHelperDialog pfnhDialog = new PermsAddFeaturesNaviHelperDialog();
                    pfnhDialog.setCancelable(false);
                    pfnhDialog.show(getFragmentManager(), "Permissions Add Navi Helper Dialog");
                }

            }
        });

        anbackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (anDialogState==0||anDialogState==1)
                {
                    anbackButton.setVisibility(View.INVISIBLE);
                    anMainText.setText(R.string.perm_addfeat_settings_maintext);
                    anDialogState=0;
                }
                else if (anDialogState==2)
                {
                    anMainText.setText(R.string.perm_addfeat_settings_othertext);
                    anDialogState=anDialogState - 1;
                }
                else if (anDialogState>=3)
                {
                    anMainText.setText(R.string.perm_addfeat_navigation_maintext);
                    anDialogState=2;
                }


            }
        });

        return builder.create();
    }
}