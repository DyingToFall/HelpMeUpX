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
 * Created by James W on 9/20/2017.
 */

public class PermsAddFeaturesNaviHelperDialog extends DialogFragment {


    TextView afnhHeader;
    TextView afnhMainText;
    ImageView afnhImage;
    Button afnhbackButton;
    Button afnhforwardButton;
    int afnhDialogState;
    //Bundle btBundle;

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
        afnhDialogState = 0;
    }




    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View setupView = getActivity().getLayoutInflater().inflate(R.layout.permissions_pic_back_layout, null);
        builder.setView(setupView);

        afnhHeader = (TextView) setupView.findViewById(R.id.backPicHeaderText);
        afnhMainText = (TextView) setupView.findViewById(R.id.blue_helper_text);
        afnhImage = (ImageView) setupView.findViewById(R.id.blue_helper_image);
        afnhbackButton = (Button) setupView.findViewById(R.id.backHelpButton);
        afnhforwardButton = (Button) setupView.findViewById(R.id.forwardHelpButton);

        afnhHeader.setText(R.string.perm_addfeat_header);
        afnhMainText.setText(R.string.perm_addfeat_nav_step_one);
        afnhImage.setImageResource(R.mipmap.dropdown_menu);
        afnhbackButton.setVisibility(View.INVISIBLE);

        afnhforwardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (afnhDialogState==0)
                {
                    afnhMainText.setText(R.string.perm_addfeat_nav_step_two);
                    afnhDialogState = afnhDialogState + 1;
                    afnhbackButton.setVisibility(View.VISIBLE);
                }
                else if (afnhDialogState==1)
                {
                    afnhMainText.setText(R.string.perm_addfeat_nav_step_three);
                    afnhDialogState = afnhDialogState + 1;
                }
                else if (afnhDialogState==2)
                {
                    afnhMainText.setText(R.string.perm_addfeat_nav_step_four);
                    afnhDialogState = afnhDialogState + 1;
                }
                else if (afnhDialogState>=3)
                {
                    PermsAddFeaturesBackFinalDialog pfinDialog = new PermsAddFeaturesBackFinalDialog();
                    pfinDialog.setCancelable(false);
                    pfinDialog.show(getFragmentManager(), "Permissions Add Features Final Dialog");
                }



            }
        });

        afnhbackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {

                if (afnhDialogState==0 || afnhDialogState==1) {
                    afnhMainText.setText(R.string.perm_addfeat_nav_step_one);
                    afnhDialogState = 0;
                    afnhbackButton.setVisibility(View.INVISIBLE);
                }
                else if (afnhDialogState==2)
                {
                    afnhMainText.setText(R.string.perm_addfeat_nav_step_two);
                    afnhDialogState = afnhDialogState - 1;
                }
                else if (afnhDialogState==3)
                {
                    afnhMainText.setText(R.string.perm_addfeat_nav_step_three);
                    afnhDialogState = afnhDialogState - 1;
                }
                else if (afnhDialogState>=4)
                {
                    afnhMainText.setText(R.string.perm_addfeat_nav_step_four);
                    afnhDialogState = 4;
                }



            }
        });

        return builder.create();
    }
}
