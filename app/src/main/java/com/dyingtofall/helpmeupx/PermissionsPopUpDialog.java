package com.dyingtofall.helpmeupx;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by James W on 9/12/2017.
 */

public class PermissionsPopUpDialog extends DialogFragment {


    TextView popHeader;
    TextView popMain;
    ImageView popImage;
    Button popButton;
    Bundle bunds;


    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View setupView = getActivity().getLayoutInflater().inflate(R.layout.permissions_popup_layout, null);
        builder.setView(setupView);

        popHeader = (TextView) setupView.findViewById(R.id.permspopupHeaderText);
        popMain = (TextView) setupView.findViewById(R.id.permspopupMainText);
        popImage = (ImageView) setupView.findViewById(R.id.permspopupimage);
        popButton = (Button) setupView.findViewById(R.id.permspopupOutButton);


        bunds = getArguments();
        int popState = bunds.getInt("bcs",1);

        if (popState==2)
        {
            popHeader.setText(R.string.perm_perms_perm2);
            popMain.setText(R.string.perm_SMS_info);
            popImage.setImageResource(R.mipmap.sms_image);
        }
        else if (popState==3)
        {
            popHeader.setText(R.string.perm_perms_perm3);
            popMain.setText(R.string.perm_location_info);
            popImage.setImageResource(R.mipmap.location_image);
        }
        else if (popState==4)
        {
            popHeader.setText(R.string.perm_perms_perm4);
            popMain.setText(R.string.perm_audio_info);
            popImage.setImageResource(R.mipmap.audio_image);
        }
        else if (popState==5)
        {
            popHeader.setText(R.string.perm_perms_perm5);
            popMain.setText(R.string.perm_internet_info);
            popImage.setImageResource(R.mipmap.internet_image);
        }
        else if (popState==6)
        {
            popHeader.setText(R.string.perm_perms_perm6);
            popMain.setText(R.string.perm_contacts_info);
            popImage.setImageResource(R.mipmap.contacts_image);
        }


       popButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                getDialog().dismiss();
            }
        });


        return builder.create();
    }
}