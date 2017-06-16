package com.ids.residentialmanagementsystem;

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

public class UserRegistration extends AppCompatActivity {

    Button userreg;
    EditText fnet,lnet,emailet,mobet;
    String homeid,fname,lname,email,mob,check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userreg = (Button)findViewById(R.id.adminRegbut);
        fnet = (EditText)findViewById(R.id.fnet);
        lnet = (EditText)findViewById(R.id.lnet);
        emailet = (EditText)findViewById(R.id.emailet);
        mobet = (EditText)findViewById(R.id.mobet);

        homeid = getIntent().getStringExtra("homeid");
    }

    public void userreg(View view){

        fname = fnet.getText().toString();
        lname = lnet.getText().toString();
        email = emailet.getText().toString();
        mob = mobet.getText().toString();

        if (fname.equals("")||lname.equals("")||email.equals("")){
            Toast.makeText(UserRegistration.this, "Please enter all infomation", Toast.LENGTH_SHORT).show();
        }else{
            UserReg ur = new UserReg();
            ur.execute(homeid,fname,lname,email,mob);
        }
    }

        class UserReg extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String homeid = params[0];
            String fn = params[1];
            String ln = params[2];
            String email = params[3];
            String mob = params[4];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://220.247.201.80:6079/Residential_Manage_SRV/admin_reg_post");
                String urlParams = "Home_ID="+homeid+"&First_Name="+fn+"&Last_Name="+ln+"&Email="+email+"&Mobile="+mob;

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
                check = root.getString("STATUS");
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: "+e.getMessage();
            }
            Toast.makeText(UserRegistration.this, s, Toast.LENGTH_LONG).show();
            if (check.contains("1")) {
                Intent i = new Intent(UserRegistration.this, RegistrationConfirmation.class);
                startActivity(i);
            }else{
                Toast.makeText(UserRegistration.this, "Invalid infoamtion please try again", Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public void onBackPressed() {
    }


}
