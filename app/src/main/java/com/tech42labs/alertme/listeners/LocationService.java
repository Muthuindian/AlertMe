package com.tech42labs.alertme.listeners;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tech42labs.alertme.MainActivity;

import java.net.URISyntaxException;
import java.util.ServiceConfigurationError;

import br.com.safety.locationlistenerhelper.core.SettingsLocationTracker;

/**
 * Created by mari on 06/05/17.
 */

public class LocationService extends Service {


    private boolean isRunning;
    private Context context;
    private Thread locationThread;
    private Intent intent;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        this.context = this;
        this.isRunning = false;
        this.locationThread = new Thread(sendLocation);


        super.onCreate();
    }


    private Runnable sendLocation = new Runnable() {
        @Override
        public void run() {


            Location locationData = intent.getParcelableExtra(SettingsLocationTracker.LOCATION_MESSAGE);
            Log.e("Location: ", "Latitude: " + locationData.getLatitude() + "Longitude:" + locationData.getLongitude());

            /*SharedPreferences preferences = context.getSharedPreferences("Location" , MODE_PRIVATE);
            Log.e("Latitude" , String.valueOf(preferences.getLong("Latitude",0)));
            Log.e("Longitude"  , String.valueOf(preferences.getLong("Longitude" , 0)));
            */
            stopSelf();
        }
    };


    @Override
    public void onDestroy() {
        this.isRunning = false;
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(!this.isRunning) {
            this.isRunning = true;
            this.locationThread.start();
        }
        return START_STICKY;
    }
}
