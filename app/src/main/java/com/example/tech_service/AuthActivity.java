package com.example.tech_service;

import android.os.Bundle;

import com.example.tech_service.Fragment.SignInFragment;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;



public class AuthActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_auth);
//        getSupportFragmentManager().beginTransaction().replace(R.id.frameAuthContainer,new HomeStartFragment()).commit();
//
//    }

    setContentView(R.layout.activity_auth);
    getSupportFragmentManager().beginTransaction().replace(R.id.frameAuthContainer,new SignInFragment()).commit();

}
}