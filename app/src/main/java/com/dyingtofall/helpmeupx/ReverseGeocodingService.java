package com.dyingtofall.helpmeupx;

/**
 * Created by James W on 8/20/2017.
 */

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ReverseGeocodingService extends AsyncTask<Location, Void, Void> {
    private final Context aContext;
    private Address aAddress;

    public ReverseGeocodingService(Context context) {
        aContext = context;
    }

    @Override
    protected Void doInBackground(Location... locations) {
        Geocoder geocoder = new Geocoder(aContext, Locale.getDefault());

        Location loc = locations[0];
        List<Address> addresses = null;
        try {
            // get all the addresses fro the given latitude, and longitude
            addresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
        } catch (IOException e) {
            aAddress = null;
        }

        // if we have at least one address, use it
        if (addresses != null && addresses.size() > 0) {
            aAddress = addresses.get(0);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        // set the address on the UI thread
       //.onAddressAvailable(aAddress);
    }

}
