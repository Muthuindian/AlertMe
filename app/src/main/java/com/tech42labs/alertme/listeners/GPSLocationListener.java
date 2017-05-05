package com.tech42labs.alertme.listeners;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by mari on 3/17/17.
 */

public class GPSLocationListener implements LocationListener {
    private Context context;

    @Override
    public void onLocationChanged(Location location) {
        Context context=null;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong("latitude" , (long) location.getLatitude());
        editor.putLong("longitude" , (long) location.getLongitude());
        editor.apply();
        Log.e("latitude" , String.valueOf(location.getLatitude()));
        Log.e("longitude" , String.valueOf(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

        Log.e("Status:","Provider:"+status+" "+provider);

    }

    @Override
    public void onProviderEnabled(String provider) {

        Toast.makeText( context, "Gps Enabled",	Toast.LENGTH_SHORT ).show();

    }

    @Override
    public void onProviderDisabled(String provider) {

        Toast.makeText( context, "Gps Disabled",	Toast.LENGTH_SHORT).show();

    }
}
