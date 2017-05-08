package com.tech42labs.alertme.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.tech42labs.alertme.LoginActivity;

import java.util.HashMap;

/**
 * Created by mari on 07/05/17.
 */

public class SessionService {

    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    public SessionService(Context context){
        this._context = context;
        pref = _context.getSharedPreferences("session", 0);
        editor = pref.edit();
    }

    //Create login session
    public void createUserLoginSession(int id, String companyName , int key){

        editor.putBoolean("isLoggedIn", true);
        editor.putInt("ID", id);
        editor.putString("COMPANY", companyName);
        editor.putInt("KEY", key);
        editor.commit();
    }


    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else do anything
     * */
    public boolean checkLogin(){

        if(!this.isUserLoggedIn()){

            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);

            return true;
        }
        return false;
    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){

        HashMap<String, String> user = new HashMap<String, String>();
        user.put("ID", pref.getString("ID", null));
        user.put("COMPANY", pref.getString("COMPANY", null));
        user.put("KEY", pref.getString("KEY", null));

        return user;
    }


    /**
     * Clear session details
     * */
    public void logoutUser(){

        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }


    public boolean isUserLoggedIn(){
        return pref.getBoolean("isLoggedIn", false);
    }
}
