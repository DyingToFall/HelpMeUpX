package com.dyingtofall.helpmeupx;

import android.*;
import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.v4.content.ContextCompat.checkSelfPermission;
import static android.support.v4.content.PermissionChecker.checkPermission;

/**
 * Created by James W on 9/11/2017.
 */

public class PermissionsInitialDialog extends DialogFragment {


    TextView pmainHeader;
    TextView pmainBlueText;
    TextView pmainSMSText;
    TextView pmainLocationText;
    TextView pmainAudioText;
    TextView pmainInternetText;
    TextView pmainContactsText;
    ImageView pmainBlueImage;
    ImageView pmainSMSImage;
    ImageView pmainLocationImage;
    ImageView pmainAudioImage;
    ImageView pmainInternetImage;
    ImageView pmainContactsImage;
    Button pnextButton;
    TextView pmainExtraText;
    int pButtonClickState;
    boolean goNext;
    Bundle piBundle;


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            int width = display.getWidth();
            int height = display.getHeight();
            dialog.getWindow().setLayout(width, height);
        }
        pButtonClickState = 0;
    }


    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View setupView = getActivity().getLayoutInflater().inflate(R.layout.perm_test_layout, null);
        builder.setView(setupView);

        goNext=false;

        piBundle = new Bundle();


        pmainHeader = (TextView) setupView.findViewById(R.id.testHeaderText);
        pmainBlueText = (TextView) setupView.findViewById(R.id.tbluetooth_text_brief);
        pmainSMSText = (TextView) setupView.findViewById(R.id.tsms_text_brief);
        pmainLocationText = (TextView) setupView.findViewById(R.id.tlocation_text_brief);
        pmainAudioText = (TextView) setupView.findViewById(R.id.taudio_text_brief);
        pmainInternetText = (TextView) setupView.findViewById(R.id.tinternet_text_brief);
        pmainContactsText = (TextView) setupView.findViewById(R.id.tcontacts_text_brief);
        pmainBlueImage = (ImageView) setupView.findViewById(R.id.tbluetooth_lay_image);
        pmainSMSImage = (ImageView) setupView.findViewById(R.id.tsms_lay_image);
        pmainLocationImage = (ImageView) setupView.findViewById(R.id.tlocation_lay_image);
        pmainAudioImage = (ImageView) setupView.findViewById(R.id.taudio_lay_image);
        pmainInternetImage = (ImageView) setupView.findViewById(R.id.tinternet_lay_image);
        pmainContactsImage = (ImageView) setupView.findViewById(R.id.tcontacts_lay_image);
        pmainExtraText = (TextView) setupView.findViewById(R.id.test_picker_text);
        pnextButton = (Button) setupView.findViewById(R.id.testButton);


        pmainBlueImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pButtonClickState = 1;
                piBundle.putInt("bcs", pButtonClickState);
                PermissionsPopUpDialog popDialog = new PermissionsPopUpDialog();
                popDialog.setCancelable(false);
                popDialog.setArguments(piBundle);
                popDialog.show(getFragmentManager(), "PopUp Dialog");
            }
        });

        pmainBlueText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pButtonClickState = 1;
                piBundle.putInt("bcs", pButtonClickState);
                PermissionsPopUpDialog popDialog = new PermissionsPopUpDialog();
                popDialog.setCancelable(false);
                popDialog.setArguments(piBundle);
                popDialog.show(getFragmentManager(), "PopUp Dialog");
            }
        });

        pmainSMSImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pButtonClickState = 2;
                piBundle.putInt("bcs", pButtonClickState);
                PermissionsPopUpDialog popDialog = new PermissionsPopUpDialog();
                popDialog.setCancelable(false);
                popDialog.setArguments(piBundle);
                popDialog.show(getFragmentManager(), "PopUp Dialog");
            }
        });

        pmainSMSText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pButtonClickState = 2;
                piBundle.putInt("bcs", pButtonClickState);
                PermissionsPopUpDialog popDialog = new PermissionsPopUpDialog();
                popDialog.setCancelable(false);
                popDialog.setArguments(piBundle);
                popDialog.show(getFragmentManager(), "PopUp Dialog");
            }
        });

        pmainLocationImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pButtonClickState = 3;
                piBundle.putInt("bcs", pButtonClickState);
                PermissionsPopUpDialog popDialog = new PermissionsPopUpDialog();
                popDialog.setCancelable(false);
                popDialog.setArguments(piBundle);
                popDialog.show(getFragmentManager(), "PopUp Dialog");

            }
        });

        pmainLocationText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pButtonClickState = 3;
                piBundle.putInt("bcs", pButtonClickState);
                PermissionsPopUpDialog popDialog = new PermissionsPopUpDialog();
                popDialog.setCancelable(false);
                popDialog.setArguments(piBundle);
                popDialog.show(getFragmentManager(), "PopUp Dialog");

            }
        });

        pmainAudioImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pButtonClickState = 4;
                piBundle.putInt("bcs", pButtonClickState);
                PermissionsPopUpDialog popDialog = new PermissionsPopUpDialog();
                popDialog.setCancelable(false);
                popDialog.setArguments(piBundle);
                popDialog.show(getFragmentManager(), "PopUp Dialog");
            }
        });

        pmainAudioText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pButtonClickState = 4;
                piBundle.putInt("bcs", pButtonClickState);
                PermissionsPopUpDialog popDialog = new PermissionsPopUpDialog();
                popDialog.setCancelable(false);
                popDialog.setArguments(piBundle);
                popDialog.show(getFragmentManager(), "PopUp Dialog");
            }
        });

        pmainInternetImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pButtonClickState = 5;
                piBundle.putInt("bcs", pButtonClickState);
                PermissionsPopUpDialog popDialog = new PermissionsPopUpDialog();
                popDialog.setCancelable(false);
                popDialog.setArguments(piBundle);
                popDialog.show(getFragmentManager(), "PopUp Dialog");
            }
        });

        pmainInternetText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pButtonClickState = 5;
                piBundle.putInt("bcs", pButtonClickState);
                PermissionsPopUpDialog popDialog = new PermissionsPopUpDialog();
                popDialog.setCancelable(false);
                popDialog.setArguments(piBundle);
                popDialog.show(getFragmentManager(), "PopUp Dialog");
            }
        });

        pmainContactsImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pButtonClickState = 6;
                piBundle.putInt("bcs", pButtonClickState);
                PermissionsPopUpDialog popDialog = new PermissionsPopUpDialog();
                popDialog.setCancelable(false);
                popDialog.setArguments(piBundle);
                popDialog.show(getFragmentManager(), "PopUp Dialog");
            }
        });

        pmainContactsText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pButtonClickState = 6;
                piBundle.putInt("bcs", pButtonClickState);
                PermissionsPopUpDialog popDialog = new PermissionsPopUpDialog();
                popDialog.setCancelable(false);
                popDialog.setArguments(piBundle);
                popDialog.show(getFragmentManager(), "PopUp Dialog");
            }
        });

        pnextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (goNext==false) {
                    permissionsRequest();
                    if (checkPermResults() == true)
                    {
                        goNext = true;
                        pmainExtraText.setText(R.string.perm_permissions_ready);
                    }
                    else
                    {
                        pmainExtraText.setText(R.string.perm_permissions_notready);
                    }
                }
                else
                {
                    PermsBluetoothSetupDialog pbDialog = new PermsBluetoothSetupDialog();
                    pbDialog.setCancelable(false);
                    pbDialog.setArguments(piBundle);
                    pbDialog.show(getFragmentManager(), "Permissions Bluetooth Dialog");
                }




            }
        });


        return builder.create();
    }





    //permissions stuff below




    void permissionsRequest() {

        List permissionsRequired = new ArrayList();
        final List<String> permissionsList = new ArrayList<String>();
        if (!checkPermission(permissionsList, android.Manifest.permission.BLUETOOTH))
            permissionsRequired.add("Bluetooth permission");
        if (!checkPermission(permissionsList, android.Manifest.permission.BLUETOOTH_ADMIN))
            permissionsRequired.add("Bluetooth admin permission");
        if (!checkPermission(permissionsList, android.Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsRequired.add("Fine Location");
        if (!checkPermission(permissionsList, android.Manifest.permission.ACCESS_COARSE_LOCATION))
            permissionsRequired.add("Coarse Location");
        if (!checkPermission(permissionsList, android.Manifest.permission.SEND_SMS))
            permissionsRequired.add("Send SMS");
        if (!checkPermission(permissionsList, android.Manifest.permission.READ_CONTACTS))
            permissionsRequired.add("Read Contacts");
        if (!checkPermission(permissionsList, android.Manifest.permission.INTERNET))
            permissionsRequired.add("Internet");
        if(!checkPermission(permissionsList, Manifest.permission.MODIFY_AUDIO_SETTINGS))
            permissionsRequired.add("Audio Modified");


        if (permissionsList.size() > 0) {
            if (permissionsRequired.size() > 0) {
                // Need Rationale
                String message = "You need to grant access to " + permissionsRequired.get(0);
                for (int i = 1; i < permissionsRequired.size(); i++)
                    message = message + ", " + permissionsRequired.get(i);
                showMessageOkCancel(message, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Build.VERSION.SDK_INT >= 23)
                            requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                                  11);
                    }
                });
                return;
            }
            if (Build.VERSION.SDK_INT >= 23)
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                        11);
            return;
        }
    }

    private void showMessageOkCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(getContext())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private  boolean checkPermission(List permissionList, String permission)
    {
        if(Build.VERSION.SDK_INT >=23)
            if(checkSelfPermission(getContext(),permission) != PackageManager.PERMISSION_GRANTED)
            {
                permissionList.add(permission);

                if(!shouldShowRequestPermissionRationale(permission))
                    return false;
            }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 11:
                Map<String, Integer> perms = new HashMap<String, Integer>();
                // Initial
                perms.put(android.Manifest.permission.BLUETOOTH, PackageManager.PERMISSION_GRANTED);
                perms.put(android.Manifest.permission.BLUETOOTH_ADMIN, PackageManager.PERMISSION_GRANTED);
                perms.put(android.Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(android.Manifest.permission.ACCESS_COARSE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(android.Manifest.permission.SEND_SMS, PackageManager.PERMISSION_GRANTED);
                perms.put(android.Manifest.permission.WRITE_CONTACTS, PackageManager.PERMISSION_GRANTED);
                perms.put(android.Manifest.permission.INTERNET, PackageManager.PERMISSION_GRANTED);
                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for ACCESS_FINE_LOCATION
                if (perms.get(android.Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED &&
                        perms.get(android.Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED &&
                        perms.get(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                        perms.get(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                        perms.get(android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED &&
                        perms.get(android.Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED &&
                        perms.get(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
                    // All Permissions Granted
                    Toast.makeText(getContext(), "All permissions Granted.", Toast.LENGTH_SHORT)
                            .show();
                } /*else {
                // Permission Denied
                Toast.makeText(getActivity(), "Some Permission is Denied.", Toast.LENGTH_SHORT)
                        .show();
            }*/
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    boolean checkPermResults()
    {
        List permissionsRequired = new ArrayList();
        final List<String> permissionsList = new ArrayList<String>();
        if (!checkPermission(permissionsList, android.Manifest.permission.BLUETOOTH))
            permissionsRequired.add("Bluetooth permission");
        if (!checkPermission(permissionsList, android.Manifest.permission.BLUETOOTH_ADMIN))
            permissionsRequired.add("Bluetooth admin permission");
        if (!checkPermission(permissionsList, android.Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsRequired.add("Fine Location");
        if (!checkPermission(permissionsList, android.Manifest.permission.ACCESS_COARSE_LOCATION))
            permissionsRequired.add("Coarse Location");
        if (!checkPermission(permissionsList, android.Manifest.permission.SEND_SMS))
            permissionsRequired.add("Send SMS");
        if (!checkPermission(permissionsList, android.Manifest.permission.READ_CONTACTS))
            permissionsRequired.add("Read Contacts");
        if (!checkPermission(permissionsList, android.Manifest.permission.INTERNET))
            permissionsRequired.add("Internet");
        if(!checkPermission(permissionsList, Manifest.permission.MODIFY_AUDIO_SETTINGS))
            permissionsRequired.add("Audio Modified");
        if (permissionsList.size() > 0)
            return false;
        return true;
    }

}