package com.example.tomal.finalprojectservice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnStart,btnStop;
    Intent in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart= (Button) findViewById(R.id.btnStart);
        btnStop= (Button) findViewById(R.id.btnStop);

        in= new Intent (this,MyService.class);

    }


    public void startService(View view) {

        startService(in);
    }

    public void stopService(View view) {
        stopService(in);
    }
}
