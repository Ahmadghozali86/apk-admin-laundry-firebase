package com.example.admin_laundry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3000;

    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLoggedIn()) {
                    Intent intent = new Intent(Splash.this, Home.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Splash.this, Login.class);
                    startActivity(intent);
                }
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private boolean isLoggedIn() {
        return sharedPreferences.getBoolean("isLoggedIn", false);
    }
}