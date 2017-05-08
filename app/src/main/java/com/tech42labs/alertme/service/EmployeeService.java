package com.tech42labs.alertme.service;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.R.attr.id;

/**
 * Created by mari on 08/05/17.
 */

public class EmployeeService {


    public static String base_url = "http://192.168.0.155:7777/api/employee/";


    private static String result="";

    public static String registerToken(String id , String token) throws JSONException, IOException {
        return post(id , createJSON(token));


    }

    static String createJSON(String token) throws JSONException {

        JSONObject obj = new JSONObject().put("tokenNumber", token) ;
        return obj.toString();
    }

    static String post(String id ,String json) throws IOException, JSONException {

        base_url = base_url + id;
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8") , json);
        final Request request = new Request.Builder()
                .url(base_url)
                .addHeader("Content-Type" , "application/json")
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    }



