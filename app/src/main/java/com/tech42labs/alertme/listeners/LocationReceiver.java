package com.tech42labs.alertme.listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.tech42labs.alertme.MainActivity;

import br.com.safety.locationlistenerhelper.core.*;

/**
 * Created by mari on 05/05/17.
 */

public class LocationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        /*Location locationData = intent.getParcelableExtra(SettingsLocationTracker.LOCATION_MESSAGE);
        Log.d("Location: ", "Latitude: " + locationData.getLatitude() + "Longitude:" + locationData.getLongitude());

        SharedPreferences sharedPreferences = context.getSharedPreferences("Location" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("Latitude" , (long) locationData.getLatitude());
        editor.putLong("Longitude" , (long) locationData.getLongitude());
        editor.commit();*/

        /*Intent backgroundService = new Intent(context , LocationService.class);
        context.startService(backgroundService);*/

        Location locationData = intent.getParcelableExtra(SettingsLocationTracker.LOCATION_MESSAGE);
        Log.e("Location: ", "Latitude: " + locationData.getLatitude() + "Longitude:" + locationData.getLongitude());

        //send your call to api or do any things with the of location data
    }
}
