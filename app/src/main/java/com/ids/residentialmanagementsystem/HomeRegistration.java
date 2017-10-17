package com.ids.residentialmanagementsystem;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

    Button homereg, homeverify, peaktimebut, offpeaktimebut;
    Spinner serviceprovider, broadbandpackage;
    TextView to1, to2, homeinfotv,servprovtv,brdpacktv;
    EditText homeet,vcode, peaktime, peakcount, offpeakcount, peaktime2, offpeaktime, offpeaktime2, billrecycle;
    String homeid,vcodevar, peakC, offpeakC, peaktimeval, peaktime2val, offpeaktimeval, offpeaktime2val, billrecycleday, servprovresp, brdpackresp,check, check2;
    boolean inserthome;
    Context ctx = this;
    long count;
    Database db = new Database(ctx);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        count = db.checkreg();

        if (count > 0){
            Intent go = new Intent(ctx,AccessCodeConfirmation.class);
            startActivity(go);
        }

        serviceprovider = (Spinner) findViewById(R.id.servpro_sp);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.serviceproviders, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serviceprovider.setAdapter(adapter);
        servprovresp = serviceprovider.getSelectedItem().toString();

        broadbandpackage = (Spinner) findViewById(R.id.broadbandpack_sp);


        serviceprovider.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                servprovresp = serviceprovider.getSelectedItem().toString();
                System.out.println("serviceproviders" + servprovresp);
                if (servprovresp.contains("SLT")) {

                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getBaseContext(),
                            R.array.broadbandpacksSLT, android.R.layout.simple_spinner_item);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    broadbandpackage.setAdapter(adapter2);

                } else if (servprovresp.contains("DIALOG")) {

                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getBaseContext(),
                            R.array.broadbandpacksDialog, android.R.layout.simple_spinner_item);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    broadbandpackage.setAdapter(adapter2);

                } else if (servprovresp.contains("LANKA BELL")) {
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getBaseContext(),
                            R.array.broadbandpacksLB, android.R.layout.simple_spinner_item);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    broadbandpackage.setAdapter(adapter2);

                } else {
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getBaseContext(),
                            R.array.broadbandpacksDialog, android.R.layout.simple_spinner_item);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    broadbandpackage.setAdapter(adapter2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        broadbandpackage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                brdpackresp = broadbandpackage.getSelectedItem().toString();
                System.out.println("broadbandpackage" + broadbandpackage);
                if (servprovresp.contains("SLT")) {
                    if (brdpackresp.contains("Light")) {
                        peakcount.setText("5gb");
                        peaktime.setText("08:00");
                        offpeakcount.setText("5gb");
                        peaktime2.setText("00:00");
                        offpeaktime.setText("00:01");
                        offpeaktime2.setText("07:59");
                        //billrecycle.setText("25gb");

                    } else if (brdpackresp.contains("Family")) {
                        peakcount.setText("30gb");
                        peaktime.setText("08:00");
                        offpeakcount.setText("30gb");
                        peaktime2.setText("00:00");
                        offpeaktime.setText("00:01");
                        offpeaktime2.setText("07:59");
                        //billrecycle.setText("25gb");

                    } else if (brdpackresp.contains("Super Middle")) {
                        peakcount.setText("75gb");
                        peaktime.setText("08:00");
                        offpeakcount.setText("75gb");
                        peaktime2.setText("00:00");
                        offpeaktime.setText("00:01");
                        offpeaktime2.setText("07:59");
                        //billrecycle.setText("25gb");

                    } else {
                        //peakcount.setText("25gbYT");
                    }
                } else if (servprovresp.contains("DIALOG")) {
                    if (brdpackresp.contains("Mini")) {
                        peakcount.setText("5gb");
                        peaktime.setText("08:00");
                        offpeakcount.setText("5gb");
                        peaktime2.setText("00:00");
                        offpeaktime.setText("00:01");
                        offpeaktime2.setText("07:59");
                        //billrecycle.setText("25gb");

                    } else if (brdpackresp.contains("Lite")) {
                        peakcount.setText("10gb");
                        peaktime.setText("08:00");
                        offpeakcount.setText("10gb");
                        peaktime2.setText("00:00");
                        offpeaktime.setText("00:01");
                        offpeaktime2.setText("07:59");
                        //billrecycle.setText("25gb");

                    } else if (brdpackresp.contains("Plus")) {
                        peakcount.setText("25gb");
                        peaktime.setText("08:00");
                        offpeakcount.setText("25gb");
                        peaktime2.setText("00:00");
                        offpeaktime.setText("00:01");
                        offpeaktime2.setText("07:59");
                        //billrecycle.setText("25gb");

                    } else {
                        //peakcount.setText("22FVgb");
                    }
                } else if (servprovresp.contains("LANKA BELL")) {
                    if (brdpackresp.contains("Starter")) {
                        peakcount.setText("25gb");
                        peaktime.setText("08:00");
                        offpeakcount.setText("25gb");
                        peaktime2.setText("00:00");
                        offpeaktime.setText("00:01");
                        offpeaktime2.setText("07:59");
                        //billrecycle.setText("25gb");

                    } else if (brdpackresp.contains("Intro")) {
                        peakcount.setText("35gb");
                        peaktime.setText("08:00");
                        offpeakcount.setText("35gb");
                        peaktime2.setText("00:00");
                        offpeaktime.setText("00:01");
                        offpeaktime2.setText("07:59");
                        //billrecycle.setText("25gb");

                    } else if (brdpackresp.contains("Family")) {
                        peakcount.setText("70gb");
                        peaktime.setText("08:00");
                        offpeakcount.setText("70gb");
                        peaktime2.setText("00:00");
                        offpeaktime.setText("00:01");
                        offpeaktime2.setText("07:59");
                        //billrecycle.setText("25gb");

                    } else {
                        //peakcount.setText("78VVgb");
                    }
                } else {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        homereg = (Button) findViewById(R.id.homeidbut);
        homeverify = (Button) findViewById(R.id.homeidbut2);
        homeet = (EditText) findViewById(R.id.homeidet);
        vcode = (EditText) findViewById(R.id.vcode);
        peakcount = (EditText) findViewById(R.id.peakcount);
        offpeakcount = (EditText) findViewById(R.id.offpeakcount);
        peaktimebut = (Button) findViewById(R.id.peaktimebut);
        offpeaktimebut = (Button) findViewById(R.id.offpeaktimebut);
        peaktime = (EditText) findViewById(R.id.peaktime);
        peaktime2 = (EditText) findViewById(R.id.peaktime2);
        offpeaktime = (EditText) findViewById(R.id.offpeak);
        offpeaktime2 = (EditText) findViewById(R.id.offpeak2);
        billrecycle = (EditText) findViewById(R.id.billrecycleet);
        to1 = (TextView) findViewById(R.id.to1tv);
        to2 = (TextView) findViewById(R.id.to2tv);
        servprovtv = (TextView) findViewById(R.id.servprovTV);
        brdpacktv = (TextView) findViewById(R.id.brdpackTV);
        homeinfotv = (TextView) findViewById(R.id.homeidtv);

        homereg.setVisibility(View.GONE);
        peakcount.setVisibility(View.GONE);
        offpeakcount.setVisibility(View.GONE);
        peaktime.setVisibility(View.GONE);
        peaktimebut.setVisibility(View.GONE);
        offpeaktimebut.setVisibility(View.GONE);
        peaktime2.setVisibility(View.GONE);
        offpeaktime.setVisibility(View.GONE);
        offpeaktime2.setVisibility(View.GONE);
        billrecycle.setVisibility(View.GONE);
        to1.setVisibility(View.GONE);
        to2.setVisibility(View.GONE);
        servprovtv.setVisibility(View.GONE);
        brdpacktv.setVisibility(View.GONE);
        serviceprovider.setVisibility(View.GONE);
        broadbandpackage.setVisibility(View.GONE);

    }

    public void homereg2(View view2) {
        homeid = homeet.getText().toString();
        vcodevar = vcode.getText().toString();

        if (homeid.equals("") || vcodevar.equals("")) {
            Toast.makeText(HomeRegistration.this, "Please enter all infomation", Toast.LENGTH_SHORT).show();
        } else {
            HomeVerify hv = new HomeVerify();
            hv.execute(homeid, vcodevar);
            System.out.println("DATAOUT : " + homeid + vcodevar);
        }
    }

    class HomeVerify extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String homeid = params[0];
            String vercode = params[1];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://220.247.201.80:6079/Residential_Manage_SRV/home_verify_post");
                String urlParams = "Home_ID=" + homeid + "&Verification_Code=" + vercode;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }

                is.close();
                httpURLConnection.disconnect();

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            String err = null;
            try {
                JSONObject root = new JSONObject(s);
                check = root.getString("STATUS");
                System.out.println(check);
            } catch (JSONException e) {
                e.printStackTrace();
                err = "APP RESPONSE: " + e.getMessage();
            }
            System.out.println(s);

            Toast.makeText(HomeRegistration.this, s, Toast.LENGTH_LONG).show();

            if (check.contains("1")) {

                homereg.setVisibility(View.VISIBLE);
                peakcount.setVisibility(View.VISIBLE);
                offpeakcount.setVisibility(View.VISIBLE);
                peaktime.setVisibility(View.VISIBLE);
                peaktime2.setVisibility(View.VISIBLE);
                peaktimebut.setVisibility(View.VISIBLE);
                offpeaktimebut.setVisibility(View.VISIBLE);
                offpeaktime.setVisibility(View.VISIBLE);
                offpeaktime2.setVisibility(View.VISIBLE);
                to1.setVisibility(View.VISIBLE);
                to2.setVisibility(View.VISIBLE);
                billrecycle.setVisibility(View.VISIBLE);
                servprovtv.setVisibility(View.VISIBLE);
                brdpacktv.setVisibility(View.VISIBLE);
                serviceprovider.setVisibility(View.VISIBLE);
                broadbandpackage.setVisibility(View.VISIBLE);
                homeinfotv.setText("Please enter remaining infomation");

                homeverify.setVisibility(View.GONE);
                homeet.setVisibility(View.GONE);
                vcode.setVisibility(View.GONE);
            }else{
                homeinfotv.setText("You have already registered this device and created an admin!");
            }

        }
    }

    public void homereg(View view) {
        peakC = peakcount.getText().toString();
        offpeakC = offpeakcount.getText().toString();
        peaktimeval = peaktime.getText().toString();
        peaktime2val = peaktime2.getText().toString();
        offpeaktimeval = offpeaktime.getText().toString();
        offpeaktime2val = offpeaktime2.getText().toString();
        billrecycleday = billrecycle.getText().toString();

        if (peakC.equals("") || offpeakC.equals("") || peaktimeval.equals("") || peaktime2val.equals("") || offpeaktimeval.equals("") || offpeaktime2val.equals("")) {
            Toast.makeText(HomeRegistration.this, "Please enter all details!", Toast.LENGTH_SHORT).show();
        } else {
            HomeReg b = new HomeReg();
            b.execute(homeid, peakC, offpeakC, peaktimeval, peaktime2val, offpeaktimeval, offpeaktime2val, billrecycleday);
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
            String puv = params[1];
            String opuv = params[2];
            String pst = params[3];
            String pet = params[4];
            String opst = params[5];
            String opet = params[6];
            String prd = params[7];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://220.247.201.80:6079/Residential_Manage_SRV/home_reg_post");
                String urlParams = "Home_ID=" + homeid + "&Peak_Usage_Volume=" + puv + "&oFF_Peak_Usage_Volume=" + opuv + "&Peak_Start_Time=" + pst + "&Peak_End_Time=" + pet + "&Off_Peak_Start_Time=" + opst + "&Off_Peak_End_Time=" + opet + "&Package_Recycle_Day=" + prd;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }

                is.close();
                httpURLConnection.disconnect();

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            String err = null;
            try {
                JSONObject root = new JSONObject(s);
                check2 = root.getString("STATUS");
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: " + e.getMessage();
            }
            Toast.makeText(HomeRegistration.this, s, Toast.LENGTH_LONG).show();

            inserthome = db.inserthomedetails(homeid,peakC, offpeakC, peaktimeval, peaktime2val, offpeaktimeval, offpeaktime2val, billrecycleday,vcodevar);
            if (inserthome == true){
                Toast.makeText(ctx, "Home details have been recorded", Toast.LENGTH_SHORT).show();
            }

            Intent i = new Intent(HomeRegistration.this, UserRegistration.class);
            i.putExtra("homeid", homeid);
            startActivity(i);

        }
    }

    @Override
    public void onBackPressed() {
    }
}
