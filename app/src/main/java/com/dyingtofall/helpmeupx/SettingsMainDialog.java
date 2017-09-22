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
 * Created by James W on 9/21/2017.
 */

public class SettingsMainDialog extends DialogFragment {

    TextView setHeader;
    TextView setMainText;
    Button alarmButton;
    Button messDelayButton;
    Button messFreqButton;
    Button displaySetButton;
    Button setFinishButton;
    Bundle settingsBundle;






    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View setupView = getActivity().getLayoutInflater().inflate(R.layout.settings_main_layout, null);
        builder.setView(setupView);


        setHeader = (TextView) setupView.findViewById(R.id.settings_header);
        setMainText = (TextView) setupView.findViewById(R.id.settings_maintext);
        alarmButton = (Button) setupView.findViewById(R.id.ringtone_button);
        messDelayButton = (Button) setupView.findViewById(R.id.mess_delay_button);
        messFreqButton = (Button) setupView.findViewById(R.id.mess_interval_button);
        displaySetButton = (Button) setupView.findViewById(R.id.display_settings_button);
        setFinishButton = (Button) setupView.findViewById(R.id.settings_finish_button);
        settingsBundle = new Bundle();


        alarmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                settingsBundle.putInt("SetState", 0);
                SettingsDialog sDialog = new SettingsDialog();
                sDialog.setCancelable(false);
                sDialog.setArguments(settingsBundle);
                sDialog.show(getFragmentManager(), "Settings Dialog");
            }
        });

        messDelayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                settingsBundle.putInt("SetState", 1);
                SettingsDialog sDialog = new SettingsDialog();
                sDialog.setCancelable(false);
                sDialog.setArguments(settingsBundle);
                sDialog.show(getFragmentManager(), "Settings Dialog");
            }
        });

        messFreqButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                settingsBundle.putInt("SetState", 2);
                SettingsDialog sDialog = new SettingsDialog();
                sDialog.setCancelable(false);
                sDialog.setArguments(settingsBundle);
                sDialog.show(getFragmentManager(), "Settings Dialog");
            }
        });

        displaySetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                settingsBundle.putInt("SetState", 3);
                SettingsDialog sDialog = new SettingsDialog();
                sDialog.setCancelable(false);
                sDialog.setArguments(settingsBundle);
                sDialog.show(getFragmentManager(), "Settings Dialog");
            }
        });

        setFinishButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                dismiss();
            }
        });


        return builder.create();
    }
}
