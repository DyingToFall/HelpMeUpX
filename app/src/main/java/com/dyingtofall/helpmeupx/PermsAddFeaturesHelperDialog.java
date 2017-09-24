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

public class PermsAddFeaturesHelperDialog extends DialogFragment {


    TextView afhHeader;
    TextView afhMainText;
    ImageView afhImage;
    Button afhbackButton;
    Button afhforwardButton;
    int afhDialogState;
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
        afhDialogState = 0;
    }




    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View setupView = getActivity().getLayoutInflater().inflate(R.layout.permissions_pic_enhanced_layout, null);
        builder.setView(setupView);

        afhHeader = (TextView) setupView.findViewById(R.id.backPicHeaderText);
        afhMainText = (TextView) setupView.findViewById(R.id.blue_helper_text);
        afhImage = (ImageView) setupView.findViewById(R.id.blue_helper_image);
        afhbackButton = (Button) setupView.findViewById(R.id.backHelpButton);
        afhforwardButton = (Button) setupView.findViewById(R.id.forwardHelpButton);

        afhHeader.setText(R.string.perm_addfeat_header);
        afhMainText.setText(R.string.perm_addfeat_db_step_one);
        //change later have no image resource yet
        afhImage.setImageResource(R.mipmap.database_emergency_list);
        afhbackButton.setVisibility(View.INVISIBLE);

        afhforwardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (afhDialogState==0)
                {
                    afhMainText.setText(R.string.perm_addfeat_db_step_two);
                    afhImage.setImageResource(R.mipmap.database_reliability_list);
                    afhDialogState = afhDialogState + 1;
                    afhbackButton.setVisibility(View.VISIBLE);
                }
                else if (afhDialogState==1)
                {
                    afhMainText.setText(R.string.perm_addfeat_db_step_three);
                    afhDialogState = afhDialogState + 1;
                }
                else if (afhDialogState==2)
                {
                    afhMainText.setText(R.string.perm_addfeat_db_step_four);
                    afhDialogState = afhDialogState + 1;
                }
                else if (afhDialogState==3)
                {
                    PermsAddFeaturesNavIniDialog pfnDialog = new PermsAddFeaturesNavIniDialog();
                    pfnDialog.setCancelable(false);
                    pfnDialog.show(getFragmentManager(), "Permissions Navigation Initial Dialog");
                }


            }
        });

        afhbackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {

                if (afhDialogState==0 || afhDialogState==1) {
                    afhMainText.setText(R.string.perm_addfeat_db_step_one);
                    afhImage.setImageResource(R.mipmap.database_emergency_list);
                    afhDialogState = 0;
                    afhbackButton.setVisibility(View.INVISIBLE);
                }
                else if (afhDialogState==2)
                {
                    afhImage.setImageResource(R.mipmap.database_reliability_list);
                    afhMainText.setText(R.string.perm_addfeat_db_step_two);
                    afhDialogState = afhDialogState - 1;
                }
                else if (afhDialogState==3)
                {
                    afhMainText.setText(R.string.perm_addfeat_db_step_three);
                    afhDialogState = afhDialogState - 1;
                }
                else if (afhDialogState>=4)
                {
                    afhMainText.setText(R.string.perm_addfeat_db_step_four);
                    afhDialogState = afhDialogState - 1;
                }

            }
        });

        return builder.create();
    }
}