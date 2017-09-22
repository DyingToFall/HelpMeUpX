package com.dyingtofall.helpmeupx;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by James W on 9/21/2017.
 */

public class SettingsDialog extends DialogFragment {

    TextView sHeader;
    TextView sMainText;
    Button radButton1;
    Button radButton2;
    Button radButton3;
    Button radButton4;
    Button radButton5;
    Button radButton6;
    TextView radText1;
    TextView radText2;
    TextView radText3;
    TextView radText4;
    TextView radText5;
    TextView radText6;
    Button sOKButton;
    Bundle sBundle;
    int showState;
    SharedPreferences setSP;
    SharedPreferences.Editor editSP;






    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View setupView = getActivity().getLayoutInflater().inflate(R.layout.settings_layout, null);
        builder.setView(setupView);


        sHeader = (TextView) setupView.findViewById(R.id.settings_sec_header);
        sMainText = (TextView) setupView.findViewById(R.id.settings_text);
        radButton1 = (Button) setupView.findViewById(R.id.radio_button_1);
        radButton2 = (Button) setupView.findViewById(R.id.radio_button_2);
        radButton3 = (Button) setupView.findViewById(R.id.radio_button_3);
        radButton4 = (Button) setupView.findViewById(R.id.radio_button_4);
        radButton5 = (Button) setupView.findViewById(R.id.radio_button_5);
        radButton6 = (Button) setupView.findViewById(R.id.radio_button_6);
        radText1 = (TextView) setupView.findViewById(R.id.radio_select_1);
        radText2 = (TextView) setupView.findViewById(R.id.radio_select_2);
        radText3 = (TextView) setupView.findViewById(R.id.radio_select_3);
        radText4 = (TextView) setupView.findViewById(R.id.radio_select_4);
        radText5 = (TextView) setupView.findViewById(R.id.radio_select_5);
        radText6 = (TextView) setupView.findViewById(R.id.radio_select_6);
        sOKButton = (Button) setupView.findViewById(R.id.settings_okay_button);
        sBundle = getArguments();
        showState = sBundle.getInt("SetState",0);
        setSP = getContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        editSP = setSP.edit();

        if (showState==1)
        {
            sHeader.setText(R.string.settings_setting_2);
            sMainText.setText(R.string.settings_set2_text);
            radText1.setText("30 seconds");
            radText2.setText("60 seconds (default)");
            radText3.setText("90 seconds");
            radText4.setText("120 seconds");
            radText5.setText("150 seconds");
            radText6.setText("180 seconds");
        }
        else if (showState==2)
        {
            sHeader.setText(R.string.settings_setting_3);
            sMainText.setText(R.string.settings_set3_text);
            radText1.setText("60 seconds");
            radText2.setText("120 seconds (default)");
            radText3.setText("180 seconds");
            radText4.setText("240 seconds");
            radText5.setText("300 seconds");
            radText6.setText("360 seconds");
        }
        else if (showState==3)
        {
            sHeader.setText(R.string.settings_setting_4);
            sMainText.setText(R.string.settings_set4_text);
            radText1.setText("Theme 1 (default)");
            radText2.setText("Theme 2");
            radText3.setText("Theme 3");
            radText4.setText("Theme 4");
            radText5.setText("Theme 5");
            radText6.setText("Theme 6");
        }


        radButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (showState==0)
                {
                    editSP.putInt("SetAlarm", 1);
                    editSP.commit();
                }
                else if (showState==1)
                {
                    editSP.putInt("SetMessDelay", 30);
                    editSP.commit();
                }
                else if (showState==2)
                {
                    editSP.putInt("SetMessInterval", 60);
                    editSP.commit();
                }
                else if (showState==3)
                {
                    editSP.putInt("SetDisplay", 1);
                    editSP.commit();
                }
            }
        });

        radButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (showState==0)
                {
                    editSP.putInt("SetAlarm", 2);
                    editSP.commit();
                }
                else if (showState==1)
                {
                    editSP.putInt("SetMessDelay", 60);
                    editSP.commit();
                }
                else if (showState==2)
                {
                    editSP.putInt("SetMessInterval", 120);
                    editSP.commit();
                }
                else if (showState==3)
                {
                    editSP.putInt("SetDisplay", 2);
                    editSP.commit();
                }
            }
        });

        radButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (showState==0)
                {
                    editSP.putInt("SetAlarm", 3);
                    editSP.commit();
                }
                else if (showState==1)
                {
                    editSP.putInt("SetMessDelay", 90);
                    editSP.commit();
                }
                else if (showState==2)
                {
                    editSP.putInt("SetMessInterval", 180);
                    editSP.commit();
                }
                else if (showState==3)
                {
                    editSP.putInt("SetDisplay", 3);
                    editSP.commit();
                }
            }
        });

        radButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (showState==0)
                {
                    editSP.putInt("SetAlarm", 4);
                    editSP.commit();
                }
                else if (showState==1)
                {
                    editSP.putInt("SetMessDelay", 120);
                    editSP.commit();
                }
                else if (showState==2)
                {
                    editSP.putInt("SetMessInterval", 240);
                    editSP.commit();
                }
                else if (showState==3)
                {
                    editSP.putInt("SetDisplay", 4);
                    editSP.commit();
                }
            }
        });

        radButton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (showState==0)
                {
                    editSP.putInt("SetAlarm", 5);
                    editSP.commit();
                }
                else if (showState==1)
                {
                    editSP.putInt("SetMessDelay", 150);
                    editSP.commit();
                }
                else if (showState==2)
                {
                    editSP.putInt("SetMessInterval", 300);
                    editSP.commit();
                }
                else if (showState==3)
                {
                    editSP.putInt("SetDisplay", 5);
                    editSP.commit();
                }
            }
        });

        radButton6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (showState==0)
                {
                    editSP.putInt("SetAlarm", 6);
                    editSP.commit();
                }
                else if (showState==1)
                {
                    editSP.putInt("SetMessDelay", 180);
                    editSP.commit();
                }
                else if (showState==2)
                {
                    editSP.putInt("SetMessInterval", 360);
                    editSP.commit();
                }
                else if (showState==3)
                {
                    editSP.putInt("SetDisplay", 6);
                    editSP.commit();
                }
            }
        });

        sOKButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
              dismiss();
            }
        });

        return builder.create();
    }
}
