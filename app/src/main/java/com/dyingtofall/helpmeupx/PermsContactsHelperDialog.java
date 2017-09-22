package com.dyingtofall.helpmeupx;

import android.app.AlertDialog;
import android.app.Dialog;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by James W on 9/17/2017.
 */

public class PermsContactsHelperDialog extends DialogFragment {


    TextView chHeader;
    TextView chMainText;
    ImageView chImage;
    Button chbackButton;
    Button chforwardButton;
    int chDialogState;
    Bundle chSetupBundle;


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
        chDialogState = 0;
    }




    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View setupView = getActivity().getLayoutInflater().inflate(R.layout.permissions_pic_back_layout, null);
        builder.setView(setupView);

        chHeader = (TextView) setupView.findViewById(R.id.backPicHeaderText);
        chMainText = (TextView) setupView.findViewById(R.id.blue_helper_text);
        chImage = (ImageView) setupView.findViewById(R.id.blue_helper_image);
        chbackButton = (Button) setupView.findViewById(R.id.backHelpButton);
        chforwardButton = (Button) setupView.findViewById(R.id.forwardHelpButton);

        chHeader.setText(R.string.perm_contacts_header);
        chMainText.setText(R.string.perm_contacts_step_one);
        chImage.setImageResource(R.mipmap.blank_contact_list);



        chforwardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (chDialogState==0)
                {
                    chMainText.setText(R.string.perm_contacts_step_two);
                    chDialogState=chDialogState + 1;
                }
                else if (chDialogState==1)
                {
                    chMainText.setText(R.string.perm_contacts_step_three);
                    chImage.setImageResource(R.mipmap.blank_phone_list);
                    chDialogState=chDialogState + 1;
                }
                else if (chDialogState==2)
                {
                    chMainText.setText(R.string.perm_contacts_step_four);
                    chDialogState=chDialogState + 1;
                }
                else if (chDialogState==3)
                {
                    chMainText.setText(R.string.perm_contacts_step_five);
                    chDialogState=chDialogState + 1;
                }
                else if (chDialogState==4)
                {
                    chMainText.setText(R.string.perm_contacts_step_six);
                    chImage.setImageResource(R.mipmap.colored_phone_list);
                    chDialogState=chDialogState + 1;
                }
                else if (chDialogState==5)
                {
                    chMainText.setText(R.string.perm_contacts_step_seven);
                    chImage.setImageResource(R.mipmap.colored_contact_list);
                    chDialogState=chDialogState + 1;
                }
                else if (chDialogState==6)
                {
                    chMainText.setText(R.string.perm_contacts_step_eight);
                    chDialogState=chDialogState + 1;
                }
                else if (chDialogState==7)
                {
                    chMainText.setText(R.string.perm_contacts_step_nine);
                    chDialogState=chDialogState + 1;
                }
                else if (chDialogState==8)
                {
                    chMainText.setText(R.string.perm_contacts_step_ten);
                    chDialogState=chDialogState + 1;
                }
                else if (chDialogState==9)
                {
                    chMainText.setText(R.string.perm_contacts_step_eleven);
                    chImage.setImageResource(R.mipmap.emergency_list);
                    chDialogState=chDialogState + 1;
                    ContactListDialogFragment cLDFrag = new ContactListDialogFragment();
                    cLDFrag.setCancelable(false);
                    cLDFrag.show(getFragmentManager(),"Contact List Dialog");
                }
                else if (chDialogState==10)
                {
                    chMainText.setText(R.string.perm_contacts_step_twelve);
                    chDialogState=chDialogState + 1;
                }
                else if (chDialogState==11)
                {
                    chMainText.setText(R.string.perm_contacts_step_thirteen);
                    chDialogState=chDialogState + 1;
                    EmergencyListDialogFragment eldFrag = new EmergencyListDialogFragment();
                    eldFrag.setCancelable(false);
                    eldFrag.show(getFragmentManager(),"Emergency List Dialog");
                }
                else if (chDialogState==12)
                {
                    PermsAlarmMessageInitialDialog pamDialog = new PermsAlarmMessageInitialDialog();
                    pamDialog.setCancelable(false);
                    pamDialog.show(getFragmentManager(), "Permissions Alarm Message Initial Dialog");
                }


            }
        });

        chbackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {

                if (chDialogState == 0)
                {
                    PermsBluetoothHelperDialog pbhDialog = new PermsBluetoothHelperDialog();
                    pbhDialog.setCancelable(false);
                    pbhDialog.show(getFragmentManager(), "Permissions Bluetooth Helper Dialog");
                }
                else if (chDialogState == 1)
                {
                    chHeader.setText(R.string.perm_contacts_header);
                    chMainText.setText(R.string.perm_contacts_step_one);
                    chImage.setImageResource(R.mipmap.blank_contact_list);
                    chDialogState = chDialogState - 1;
                }
                else if (chDialogState == 2)
                {
                    chMainText.setText(R.string.perm_contacts_step_two);
                    chDialogState=chDialogState - 1;
                }
                else if (chDialogState == 3)
                {
                    chMainText.setText(R.string.perm_contacts_step_three);
                    chImage.setImageResource(R.mipmap.blank_phone_list);
                    chDialogState=chDialogState - 1;
                }
                else if (chDialogState == 4)
                {
                    chMainText.setText(R.string.perm_contacts_step_four);
                    chDialogState=chDialogState - 1;
                }
                else if (chDialogState == 5)
                {
                    chMainText.setText(R.string.perm_contacts_step_five);
                    chDialogState=chDialogState - 1;
                }
                else if (chDialogState == 6)
                {
                    chMainText.setText(R.string.perm_contacts_step_six);
                    chImage.setImageResource(R.mipmap.colored_phone_list);
                    chDialogState=chDialogState - 1;
                }
                else if (chDialogState == 7)
                {
                    chMainText.setText(R.string.perm_contacts_step_seven);
                    chImage.setImageResource(R.mipmap.colored_contact_list);
                    chDialogState=chDialogState - 1;
                }
                else if (chDialogState == 8)
                {
                    chMainText.setText(R.string.perm_contacts_step_eight);
                    chDialogState=chDialogState - 1;
                }
                else if (chDialogState == 9)
                {
                    chMainText.setText(R.string.perm_contacts_step_nine);
                    chDialogState=chDialogState - 1;
                }
                else if (chDialogState == 10)
                {
                    chMainText.setText(R.string.perm_contacts_step_ten);
                    chDialogState=chDialogState - 1;
                }
                else if (chDialogState == 11)
                {
                    chMainText.setText(R.string.perm_contacts_step_eleven);
                    chImage.setImageResource(R.mipmap.emergency_list);
                    chDialogState=chDialogState - 1;
                    ContactListDialogFragment cLDFrag = new ContactListDialogFragment();
                    cLDFrag.setCancelable(false);
                    cLDFrag.show(getFragmentManager(),"Contact List Dialog");
                }
                else if (chDialogState == 12)
                {
                    chMainText.setText(R.string.perm_contacts_step_twelve);
                    chDialogState=chDialogState - 1;
                }
                else if (chDialogState == 13)
                {
                    chMainText.setText(R.string.perm_contacts_step_thirteen);
                    chDialogState=chDialogState - 1;
                    EmergencyListDialogFragment eldFrag = new EmergencyListDialogFragment();
                    eldFrag.setCancelable(false);
                    eldFrag.show(getFragmentManager(),"Emergency List Dialog");
                }




            }
        });

        return builder.create();
    }
}

