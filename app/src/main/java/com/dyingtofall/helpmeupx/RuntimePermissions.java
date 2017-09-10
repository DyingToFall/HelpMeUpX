package com.dyingtofall.helpmeupx;

import android.*;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Home on 9/2/2017.
 */

public class RuntimePermissions
{
  /*  public MainActivity mainActivity;

    public RuntimePermissions(MainActivity mainActivity)
    {
        super();
        this.mainActivity = mainActivity;

        List permissionsRequired = new ArrayList();
        final List<String> permissionsList = new ArrayList<String>();
        if (!

                checkPermission(permissionsList, android.Manifest.permission.BLUETOOTH))
            permissionsRequired.add("Bluetooth permission");
        if (!

                checkPermission(permissionsList, android.Manifest.permission.BLUETOOTH_ADMIN))
            permissionsRequired.add("Bluetooth admin permission");
        if (!

                checkPermission(permissionsList, android.Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsRequired.add("Fine Location");
        if (!

                checkPermission(permissionsList, android.Manifest.permission.ACCESS_COARSE_LOCATION))
            permissionsRequired.add("Coarse Location");
        if (!

                checkPermission(permissionsList, android.Manifest.permission.SEND_SMS))
            permissionsRequired.add("Send SMS");
        if (!

                checkPermission(permissionsList, android.Manifest.permission.READ_CONTACTS))
            permissionsRequired.add("Read Contacts");
        if (!

                checkPermission(permissionsList, android.Manifest.permission.INTERNET))
            permissionsRequired.add("Internet");


        if (permissionsList.size() > 0)

        {
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
        new AlertDialog.Builder(RuntimePermissions.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private  boolean checkPermission(List permissionList, String permission)
    {
        if(Build.VERSION.SDK_INT >=23)
            if(checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED)
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
                        perms.get(android.Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
                    // All Permissions Granted
                    Toast.makeText(RuntimePermissions.this, "All permissions Granted.", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }*/
}



