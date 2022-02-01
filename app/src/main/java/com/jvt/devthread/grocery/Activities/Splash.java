package com.jvt.devthread.grocery.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.jvt.devthread.grocery.MainActivity;
import com.jvt.devthread.grocery.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(Splash.this, MainActivity.class));
            finish();
        }, 1500);
    }
}