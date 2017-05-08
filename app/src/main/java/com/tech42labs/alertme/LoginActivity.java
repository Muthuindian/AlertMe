package com.tech42labs.alertme;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.tech42labs.alertme.service.LoginService;
import com.tech42labs.alertme.service.SessionService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import br.com.safety.locationlistenerhelper.core.LocationTracker;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mari on 07/05/17.
 */

public class LoginActivity extends AppCompatActivity implements TaskCompleted {


    CheckBox checkBox;

    EditText txtId , txtName , txtKey;

    Button login;

    SessionService sessionService;

    String userDetails=null;

    String URL = "http://192.168.0.155:7777/api/employee/";

    int idText , keyText;

    String companyName;
    private LocationTracker locationTracker;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkBox = (CheckBox) findViewById(R.id.terms);
        String checkBoxText = "I Agree the <a href='http://www.redbus.in/mob/mTerms.aspx' style:color: 'YELLOW';> Terms and Conditions</a>";

        checkBox.setText(Html.fromHtml(checkBoxText));
        checkBox.setMovementMethod(LinkMovementMethod.getInstance());

        txtId = (EditText) findViewById(R.id.emp_id);
        txtKey = (EditText) findViewById(R.id.key);
        txtName = (EditText) findViewById(R.id.company_name);
        login = (Button) findViewById(R.id.submit);



        sessionService = new SessionService(getApplicationContext());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                idText = Integer.parseInt(txtId.getText().toString());
                companyName = txtName.getText().toString();
                keyText = Integer.parseInt(txtKey.getText().toString());

                URL = URL + txtId.getText();

                new GetUserDetailsTask(LoginActivity.this).execute(URL);
            }
        });


        locationTracker = new LocationTracker("my.action")
                .setInterval(50000)
                .setGps(true)
                .setNetWork(false)
                .start(getBaseContext(), this);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        locationTracker.onRequestPermission(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onTaskComplete(String result) {
        Log.e("Url" , URL);
        Log.e("Response" , result);

        JSONObject object=null;


        try {
            object = new JSONObject(result);
            Log.e("ID", String.valueOf(object.getInt("employeeId")));
            Log.e("NAME" , String.valueOf(object.getInt("key")));


        if(String.valueOf(idText).trim().length() > 0 && String.valueOf(keyText).trim().length() >0) {

            if(keyText == (object.getInt("key"))) {
                sessionService.createUserLoginSession(idText , companyName , keyText);
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

                finish();

            }

            else {
                Toast.makeText(getApplicationContext(),
                        "Username/Password is incorrect",
                        Toast.LENGTH_LONG).show();

            }
        }

        else {
            Toast.makeText(getApplicationContext(),
                    "Please enter username and password",
                    Toast.LENGTH_LONG).show();
        }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private class GetUserDetailsTask extends AsyncTask<String , Void , String> {

        private ProgressDialog dialog;
        private Context context;
        private TaskCompleted task;

        public GetUserDetailsTask(Context context) {
            this.context = context;
            this.task = (TaskCompleted) context;
        }

        @Override
        protected void onPreExecute() {

            dialog = new ProgressDialog(context);
            dialog.setMessage("Please Wait");
            dialog.show();

        }

        @Override
        protected String doInBackground(String[] params) {

            String details = null;
                    
            try {
                details = LoginService.getUserDetais(params[0]);

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            
            return details;
        }

        @Override
        protected void onPostExecute(String s) {

            dialog.dismiss();
            task.onTaskComplete(s);
        }
    }

}

interface TaskCompleted {

    void onTaskComplete(String result);
}
