package com.ids.residentialmanagementsystem;

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

public class AccessCodeConfirmation extends AppCompatActivity {
    EditText username,password;
    Button acbut;
    String un,pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_code_confirmation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        acbut = (Button)findViewById(R.id.acbut);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
    }

    public void accesscode(View view){

        un = username.getText().toString();
        pw = password.getText().toString();
        if (un.equals("")||pw.equals("")){
            Toast.makeText(AccessCodeConfirmation.this, "Please enter credentials", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(AccessCodeConfirmation.this, "next page", Toast.LENGTH_SHORT).show();
//            AccessCodeConf ac = new AccessCodeConf();
//            ac.execute(acc);
        }
    }

    class AccessCodeConf extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String fn = params[0];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://");
                String urlParams = "fn="+fn;

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

    @Override
    public void onBackPressed() {
    }


}
