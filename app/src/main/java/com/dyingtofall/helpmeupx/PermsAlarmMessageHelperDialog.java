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
 * Created by James W on 9/18/2017.
 */

public class PermsAlarmMessageHelperDialog extends DialogFragment {


    TextView amiHeader;
    TextView amiMainText;
    ImageView amiImage;
    Button amibackButton;
    Button amiforwardButton;
    int amiDialogState;
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
        amiDialogState = 0;
    }




    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View setupView = getActivity().getLayoutInflater().inflate(R.layout.permissions_pic_back_layout, null);
        builder.setView(setupView);

        amiHeader = (TextView) setupView.findViewById(R.id.backPicHeaderText);
        amiMainText = (TextView) setupView.findViewById(R.id.blue_helper_text);
        amiImage = (ImageView) setupView.findViewById(R.id.blue_helper_image);
        amibackButton = (Button) setupView.findViewById(R.id.backHelpButton);
        amiforwardButton = (Button) setupView.findViewById(R.id.forwardHelpButton);

        amiHeader.setText(R.string.perm_alarmmess_header);
        amiMainText.setText(R.string.perm_alarmmess_step_one);
        amiImage.setImageResource(R.mipmap.main_window);
        amibackButton.setVisibility(View.INVISIBLE);

        amiforwardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (amiDialogState==0)
                {
                    amiMainText.setText(R.string.perm_alarmmess_step_two);
                    amiDialogState = amiDialogState + 1;
                    amibackButton.setVisibility(View.VISIBLE);
                }
                else if (amiDialogState==1)
                {
                    amiMainText.setText(R.string.perm_alarmmess_step_three);
                    amiDialogState = amiDialogState + 1;
                }
                else if (amiDialogState==2)
                {
                    amiMainText.setText(R.string.perm_alarmmess_step_four);
                    amiDialogState = amiDialogState + 1;
                }
                else if (amiDialogState>=3)
                {
                    PermsAddFeaturesInitialDialog pafiDialog = new PermsAddFeaturesInitialDialog();
                    pafiDialog.setCancelable(false);
                    pafiDialog.show(getFragmentManager(), "Permissions Add Features Dialog");
                }

            }
        });

        amibackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {

                if (amiDialogState==0 || amiDialogState==1) {
                    amiHeader.setText(R.string.perm_alarmmess_header);
                    amiMainText.setText(R.string.perm_alarmmess_step_one);
                    amiImage.setImageResource(R.mipmap.main_window);
                    amibackButton.setVisibility(View.INVISIBLE);
                    amiDialogState=0;
                }
                else if (amiDialogState==2)
                {
                    amiMainText.setText(R.string.perm_alarmmess_step_two);
                    amiDialogState = amiDialogState - 1;
                }
                else if (amiDialogState==3)
                {
                    amiMainText.setText(R.string.perm_alarmmess_step_three);
                    amiDialogState = amiDialogState - 1;
                }
                else if (amiDialogState>=3)
                {
                    amiMainText.setText(R.string.perm_alarmmess_step_four);
                    amiDialogState = amiDialogState - 1;
                }

            }
        });

        return builder.create();
    }
}
