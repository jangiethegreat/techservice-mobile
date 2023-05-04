package com.example.tech_service;

import static com.example.tech_service.Constant.SHOW_BRGYID;
import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech_service.Fragment.SignInFragment;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private NavigationView navigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);

        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView, navController);  }


        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();

            if (id == R.id.menuLogout) {
                // Send a logout request to the server
                String url = "http://yourserver.com/logout";
                StringRequest request = new StringRequest(Request.Method.GET, Constant.LOGOUT,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Clear user session data
                                SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                                preferences.edit().clear().apply();
                                // Navigate to login activity
                                Intent intent = new Intent(UserActivity.this, SignInFragment.class);
                                startActivity(intent);
                                finish();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });
                Volley.newRequestQueue(this).add(request);
                return true;
            }

            return super.onOptionsItemSelected(item);
        }


    }



//        navigationView = findViewById(R.id.main_nav);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() }
////            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                // handle navigation item clicks
//                switch (item.getItemId()) {
//                    case R.id.menuLogout:
//                        // perform logout action
//                        performLogout();
//                        return true;
//                    default:
//                        return false;
//                }
//            }
//        });
//    }
//
//    private void performLogout() {
//        // send logout request to API using Volley
//
//        StringRequest request = new StringRequest(Request.Method.POST, Constant.LOGOUT, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                // handle successful logout response
//                // navigate to login screen
//                Intent intent = new Intent(UserActivity.this, SignInFragment.class);
//                startActivity(intent);
//                finish(); // close MainActivity to prevent user from returning with back button
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // handle error response
//                Toast.makeText(UserActivity.this, "Logout failed", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
