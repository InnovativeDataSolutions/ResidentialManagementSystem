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
    String fname,lname,email,mob;

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
    }

    public void userreg(View view){

        fname = fnet.getText().toString();
        lname = lnet.getText().toString();
        email = emailet.getText().toString();
        mob = mobet.getText().toString();

        if (fname.equals("")||lname.equals("")||email.equals("")||mob.equals("")){
            Toast.makeText(UserRegistration.this, "Please enter all infomation", Toast.LENGTH_SHORT).show();
        }else{
//            UserReg ur = new UserReg();
//            ur.execute(fname,lname,email,mob);
        Intent intent = new Intent(this, RegistrationConfirmation.class);
        startActivity(intent);
        }
    }

        class UserReg extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String fn = params[0];
            String ln = params[1];
            String email = params[2];
            String mob = params[3];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://");
                String urlParams = "fn="+fn+"&ln="+ln+"&email="+email+"&mob="+mob;

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
