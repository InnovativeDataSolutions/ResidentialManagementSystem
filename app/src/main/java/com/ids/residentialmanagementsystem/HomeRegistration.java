package com.ids.residentialmanagementsystem;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HomeRegistration extends AppCompatActivity {

    Button homereg;
    EditText homeet,peaktime,peaktime2,offpeak,offpeak2;
    String homeid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        homereg = (Button)findViewById(R.id.homeidbut);
        homeet = (EditText)findViewById(R.id.homeidet);
        peaktime = (EditText)findViewById(R.id.peaktime);
        peaktime2 = (EditText)findViewById(R.id.peaktime2);
        offpeak = (EditText)findViewById(R.id.offpeak);
        offpeak2 = (EditText)findViewById(R.id.offpeak2);
    }

    public void homereg(View view){

        homeid = homeet.getText().toString();

        if (homeid.equals("")) {
            Toast.makeText(HomeRegistration.this, "Please enter your Home ID", Toast.LENGTH_SHORT).show();
        }else{
//            HomeReg b = new HomeReg();
//            b.execute(homeid);
            Intent intent = new Intent(this, UserRegistration.class);
            startActivity(intent);
        }
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }
    public void showTimePickerDialog2(View v2) {
        DialogFragment newFragment = new TimePickerFragment2();
        newFragment.show(getFragmentManager(), "timePicker");
    }

        class HomeReg extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String homeid = params[0];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://");
                String urlParams = "homeid="+homeid;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }

                is.close();
                httpURLConnection.disconnect();

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            String err=null;
            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
//                NAME = user_data.getString("name");
//                PASSWORD = user_data.getString("password");
//                EMAIL = user_data.getString("email");
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: "+e.getMessage();
            }

//            Intent i = new Intent(ctx, Register.class);
//            i.putExtra("name", NAME);
//            i.putExtra("password", PASSWORD);
//            i.putExtra("email", EMAIL);
//            i.putExtra("err", err);
//            startActivity(i);

        }
    }
}
