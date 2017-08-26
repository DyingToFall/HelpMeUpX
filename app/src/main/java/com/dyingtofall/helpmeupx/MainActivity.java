package com.dyingtofall.helpmeupx;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity
{
    LocationManager locationManager;

    private static final int REQUEST_PERMISSIONS = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

       /* Button sendBtn;
        sendBtn = (Button) findViewById(R.id.abutton1);
        sendBtn.setText("axe");

        Button contactBtn;
        contactBtn = (Button) findViewById(R.id.abutton2);
/*
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{ android.Manifest.permission.READ_CONTACTS},
                REQUEST_PERMISSIONS);
*/

       /* contactBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                ContactListDialogFragment cLDFrag = new ContactListDialogFragment();

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{ android.Manifest.permission.READ_CONTACTS},
                        REQUEST_PERMISSIONS);

               // cLDFrag.show(getSupportFragmentManager(), "Contact List Dialog");



            }
        });
*/



    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                if (grantResults.length > 0)  {
                    //Call whatever you want
                    ContactListDialogFragment cLDFrag = new ContactListDialogFragment();
                    cLDFrag.show(getSupportFragmentManager(), "Contact List Dialog");
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Enable Permissions from settings",
                            Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent();
                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                                    intent.setData(Uri.parse("package:" + getPackageName()));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                    startActivity(intent);
                                }
                            }).show();
                }
                return;
            }
        }
    }


}
