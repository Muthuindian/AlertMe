package com.tech42labs.alertme.firebase;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.tech42labs.alertme.service.EmployeeService;

/**
 * Created by mari on 3/28/17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("Refreshed token: " , refreshedToken);

/*        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();

        // Save to SharedPreferences
        editor.putString("registration_id", refreshedToken);
        editor.apply();*/


       /* SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Token" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("registration_id" , refreshedToken);
        editor.apply();*/
        super.onTokenRefresh();
    }
}
