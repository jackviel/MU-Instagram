package com.example.instagwam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class LaunchScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);
        //try {
            //TimeUnit.SECONDS.sleep(2);
       // } catch (InterruptedException e) {
        //    e.printStackTrace();
       // }
        Intent intent = new Intent(this, LoginScreenActivity.class);
        this.startActivity(intent);
    }
}