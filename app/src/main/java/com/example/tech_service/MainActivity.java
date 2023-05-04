package com.example.tech_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import com.example.tech_service.AuthActivity;
import com.example.tech_service.OnBoardActivity;
import com.example.tech_service.R;
import com.example.tech_service.UserActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeStatusBarColor(getResources().getColor(R.color.md_green_200));


        //this code will pause the app for 1.5 secs and then any thing in run method will run.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences userPref = getApplicationContext().getSharedPreferences("user",Context.MODE_PRIVATE);
                boolean isLoggedIn = userPref.getBoolean("isLoggedIn",false);

                if (isLoggedIn){
                    startActivity(new Intent(MainActivity.this, UserActivity.class));
                    finish();
                }

                else {
                    isFirstTime();
                }
            }
        },1500);
    }

    private void changeStatusBarColor(int color) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);
    }

    private void isFirstTime() {
        //for checking if the app is running for the very first time
        //we need to save a value to shared preferences
        SharedPreferences preferences = getApplication().getSharedPreferences("onBoard", Context.MODE_PRIVATE);
        boolean isFirstTime = preferences.getBoolean("isFirstTime",true);
        //default value true
        if (isFirstTime){
            // if its true then its first time and we will change it false
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstTime",false);
            editor.apply();

            // start Onboard activity
            startActivity(new Intent(MainActivity.this, OnBoardActivity.class));
            finish();
        }
        else{
            //start Auth Activity
            startActivity(new Intent(MainActivity.this, AuthActivity.class));
            finish();
        }
    }


}
