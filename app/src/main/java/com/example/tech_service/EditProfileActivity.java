package com.example.tech_service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    EditText et_name, et_birthdate, et_age, et_gender, et_address_no, et_street, et_address_zone, et_phone_num;
    ImageView img_valid_id;
    Button save_btn;
    ProgressDialog progressDialog;
    String Sid, Sname, Sbirthdate, Sage, Sgender, SaddressNo, Sstreet, SaddressZone, SphoneNum,Svalid_id;

    String id, name, birthdate, age, gender, addressNo, street, addressZone, phoneNum,valid_id,uploadImage;
    Bitmap validId;
    private SharedPreferences preferences;

    private static final int PICK_IMAGE_REQUEST = 1;
    private ActivityResultLauncher<Intent> imagePickLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        progressDialog = new ProgressDialog(EditProfileActivity.this);
        progressDialog.setCancelable(false);





        preferences = EditProfileActivity.this.getSharedPreferences("user", Context.MODE_PRIVATE);
        Log.e("tanginamoJV", preferences.getAll().toString());
        Sid = String.valueOf(preferences.getInt("id", 0));
        Sname = preferences.getString("name", "");
        Sgender = preferences.getString("gender", "");
        Sage = preferences.getString("age", "");
        Sbirthdate = preferences.getString("birthdate", "");
        SphoneNum = preferences.getString("phoneNum", "");
        Sstreet = preferences.getString("street", "");
        SaddressNo = preferences.getString("addressNo", "");
        SaddressZone = preferences.getString("addressZone", "");
        Svalid_id = preferences.getString("valid_id", "");

        et_name = findViewById(R.id.et_name);
        et_birthdate = findViewById(R.id.et_birthdate);
        et_age = findViewById(R.id.et_age);
        et_gender = findViewById(R.id.et_gender);
        et_address_no = findViewById(R.id.et_address_no);
        et_street = findViewById(R.id.et_street);
        et_address_zone = findViewById(R.id.et_address_zone);
        et_phone_num = findViewById(R.id.et_phone_num);
        img_valid_id = findViewById(R.id.img_valid_id);
        save_btn = findViewById(R.id.save_btn);

        et_name.setText(Sname);
        et_birthdate.setText(Sbirthdate);
        et_age.setText(Sage);
        et_gender.setText(Sgender);
        et_address_no.setText(SaddressNo);
        et_street.setText(Sstreet);
        et_address_zone.setText(SaddressZone);
        et_phone_num.setText(SphoneNum);
        Glide.with(EditProfileActivity.this)
                .load("https://northsignalvillage.com/storage/users/"+ Svalid_id)
                .into(img_valid_id);


        imagePickLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                // Get the URI of the selected image
                                Uri uri = data.getData();

                                // Do something with the URI, such as load the image into an ImageView


                                img_valid_id.setImageURI(uri);


                                // Make an API request to upload the image
                                uploadImage = uploadImage(uri);


                            }
                        }
                    }
                });

        img_valid_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                imagePickLauncher.launch(intent);

            }
        });



        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateprofile();

            }
        });


    }


    public void updateprofile() {


        String url = "https://northsignalvillage.com/api/mobile/editprofile";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Toast.makeText(EditProfileActivity.this, "Update successful.", Toast.LENGTH_SHORT).show();
                // Update the user's details in the database or any other source
                // Redirect the user to the profile page or any other page
                Intent intent = new Intent(EditProfileActivity.this, AuthActivity.class);
                startActivity(intent);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(EditProfileActivity.this, "Update failed. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", Sid);
                params.put("name", et_name.getText().toString());
                params.put("birthdate",  et_birthdate.getText().toString());
                params.put("age", et_age.getText().toString());
                params.put("gender", et_gender.getText().toString());
                params.put("addressNo", et_address_no.getText().toString());
                params.put("street", et_street.getText().toString());
                params.put("addressZone", et_address_zone.getText().toString());
                params.put("phoneNum", et_phone_num.getText().toString());
                if (uploadImage == null){
                    params.put("valid_id", "null");
                }else{
                    params.put("valid_id", uploadImage);
                }
//
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditProfileActivity.this);
        requestQueue.add(stringRequest);
    }

    private byte[] getImageData(Uri uri) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            InputStream inputStream = EditProfileActivity.this.getContentResolver().openInputStream(uri);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
    private String uploadImage(Uri uri) {
        byte[] imageData = getImageData(uri);

        // Convert the image data to a Base64-encoded string
        String imageDataString = Base64.encodeToString(imageData, Base64.NO_WRAP);
        Log.e("tag", imageDataString);
        return imageDataString;
    }
}
