package com.dyingtofall.helpmeupx;

import android.app.IntentService;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.Nullable;

import com.google.android.gms.identity.intents.Address;

import java.util.List;
import java.util.Locale;

/**
 * Created by James W on 8/20/2017.
 */

public class FetchAddressIntentService extends IntentService {

    FetchAddressIntentService(String FAIS) {
        super(FAIS);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        String errorMessage = "";

        // Get the location passed to this service through an extra.
        Location location = intent.getParcelableExtra(
                Constants.LOCATION_DATA_EXTRA);

        // ...

        List<Address> addresses = null;

    }
}
