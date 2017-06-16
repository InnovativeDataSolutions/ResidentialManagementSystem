package com.ids.residentialmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class RegistrationConfirmation extends AppCompatActivity {

    Button acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_confirmation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        acc = (Button)findViewById(R.id.proceedbut);
    }

    public void regconf(View view){
        Intent intent = new Intent(this, AccessCodeConfirmation.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }
}
