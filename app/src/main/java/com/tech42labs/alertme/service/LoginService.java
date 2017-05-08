package com.tech42labs.alertme.service;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mari on 07/05/17.
 */

public class LoginService {

    private static String result="";


    public static String getUserDetais(String url) throws JSONException, IOException {

        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
