package com.dyingtofall.helpmeupx;

/**
 * Created by James W on 9/14/2017.
 */
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
 * Created by James W on 9/12/2017.
 */

public class PermsBluetoothHelperDialog extends DialogFragment {


    TextView bthHeader;
    TextView bthMainText;
    ImageView bthImage;
    Button backhButton;
    Button forwardhButton;
    int bhDialogState;
    Bundle btBundle;

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
        bhDialogState = 0;
    }




    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View setupView = getActivity().getLayoutInflater().inflate(R.layout.permissions_pic_back_layout, null);
        builder.setView(setupView);

        bthHeader = (TextView) setupView.findViewById(R.id.backPicHeaderText);
        bthMainText = (TextView) setupView.findViewById(R.id.blue_helper_text);
        bthImage = (ImageView) setupView.findViewById(R.id.blue_helper_image);
        backhButton = (Button) setupView.findViewById(R.id.backHelpButton);
        forwardhButton = (Button) setupView.findViewById(R.id.forwardHelpButton);



        forwardhButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (bhDialogState==0)
                {
                    bthMainText.setText(R.string.perm_bluetooth_step_two);
                    bthImage.setImageResource(R.mipmap.what_to_find);
                    bhDialogState = bhDialogState + 1;
                }
                else if (bhDialogState==1)
                {
                    bthMainText.setText(R.string.perm_bluetooth_step_three);
                    bthImage.setImageResource(R.mipmap.what_to_select);
                    bhDialogState = bhDialogState + 1;
                }
                else if (bhDialogState==2)
                {
                    bthMainText.setText(R.string.perm_bluetooth_step_four);
                    bhDialogState = bhDialogState + 1;
                }
                else if (bhDialogState>2)
                {
                    PermsContactsInitialDialog pciDialog = new PermsContactsInitialDialog();
                    pciDialog.setCancelable(false);
                    pciDialog.show(getFragmentManager(), "Permissions Ini Contacts Dialog");
                    bhDialogState = bhDialogState + 1;


                    // JAMES FIX FROM QA THE ROBOTS WILL KILL US ALL
                    //Last bit of bluetooth is in this pciDialog
                    //BluetoothRfCommFrag btcFrag = new BluetoothRfCommFrag();
                    //btcFrag.setCancelable(false);
                    //FragmentManager fman = getActivity().getFragmentManager();
                    //btcFrag.show(fman, "bluetooth dialog");

                }

            }
        });

        backhButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                if (bhDialogState==0)
                {
                    btBundle = new Bundle();
                    btBundle.putBoolean("backPressed",true);
                    PermsBluetoothSetupDialog pbDialog = new PermsBluetoothSetupDialog();
                    pbDialog.setCancelable(false);
                    pbDialog.setArguments(btBundle);
                    pbDialog.show(getFragmentManager(), "Permissions Bluetooth Dialog");
                }
                else if (bhDialogState==1)
                {
                    bthMainText.setText(R.string.perm_bluetooth_step_one);
                    bthImage.setImageResource(R.mipmap.how_to_search);
                    bhDialogState = bhDialogState-1;
                }
                else if (bhDialogState==2)
                {
                    bthMainText.setText(R.string.perm_bluetooth_step_two);
                    bthImage.setImageResource(R.mipmap.what_to_find);
                    bhDialogState = bhDialogState-1;
                }
                else if (bhDialogState>2)
                {
                    bthMainText.setText(R.string.perm_bluetooth_step_three);
                    bthImage.setImageResource(R.mipmap.what_to_select);
                    bhDialogState = 2;
                }
            }
        });

        return builder.create();
    }
}