package com.example.tech_service;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserInfoActivity extends AppCompatActivity {

    private TextInputLayout layoutName, layoutAddress, layoutNumber;
    private TextInputEditText txtName, txtAddress, txtNumber;
    private TextView txtSelectPhoto;
    private Button btnContinue;
    private ImageView circleImageView;
    private static final int GALLERY_ADD_PROFILE = 1;
    private Bitmap bitmap = null;
    private SharedPreferences userPref;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        init();
    }

    private void init() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        userPref = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

//        layoutAddress = findViewById(R.id.txtLayoutAddressUserInfo);
        layoutName = findViewById(R.id.txtLayoutNameUserInfo);
        layoutNumber = findViewById(R.id.txtLayoutNumberUserInfo);
        txtNumber = findViewById(R.id.txtNumberUserInfo);
        txtName = findViewById(R.id.txtNameUserInfo);


        txtSelectPhoto = findViewById(R.id.txtSelectPhoto);
        btnContinue = findViewById(R.id.btnContinue);
        circleImageView = findViewById(R.id.imgUserInfo);

        txtSelectPhoto.setOnClickListener(v->{
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType("image/*");
            startActivityForResult(i,GALLERY_ADD_PROFILE);
        });

        btnContinue.setOnClickListener(v->{
            // validate fields
            if(validate()){
                saveUserInfo();
            }
        });

        //pick photo from gallery

        txtSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create an intent to pick an image from the gallery
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // start the activity with startActivityForResult
                startActivityForResult(intent, GALLERY_ADD_PROFILE);
            }
        });
    }

    // override onActivityResult to receive the selected image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check if the requestCode matches the one we used to start the image picker
        if (requestCode == GALLERY_ADD_PROFILE) {
            // check if the resultCode is RESULT_OK, which means the operation was successful
            if (resultCode == RESULT_OK) {
                // get the selected image URI from the data Intent
                Uri imageUri = data.getData();
                // set the image URI to the ImageView
                circleImageView.setImageURI(imageUri);
            }
        }
    }






    private boolean validate(){
        if (txtName.getText().toString().isEmpty()){
            layoutName.setErrorEnabled(true);
            layoutName.setError("Name is Required");
            return false;
        }
//        if (txtAddress.getText().toString().isEmpty()){
//            layoutAddress.setErrorEnabled(true);
//            layoutAddress.setError("Address is required");
//            return false;
//        }
        if (txtNumber.getText().toString().isEmpty()){
            layoutNumber.setErrorEnabled(true);
            layoutNumber.setError("Phone Number is required");
            return false;
        }

        return true;
    }


    private void saveUserInfo(){
        dialog.setMessage("Saving");
        dialog.show();

        String name = txtName.getText().toString().trim();
//        String address = txtAddress.getText().toString().trim();
        String phoneNum = txtNumber.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, Constant.SAVE_USER_INFO, response->{

            try {
                JSONObject object = new JSONObject(response);
                if (object.getBoolean("success")){
                    SharedPreferences.Editor editor = userPref.edit();
                    editor.putString("avatar",object.getString("avatar"));
                    editor.apply();
                    startActivity(new Intent(UserInfoActivity.this, UserActivity.class));
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            dialog.dismiss();

        },error ->{
            error.printStackTrace();
            dialog.dismiss();
        } ){

            //add token to headers



            //add params

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("name",name);
//                map.put("address",address);
                map.put("phoneNum",phoneNum);
                map.put("avatar",bitmapToString(bitmap));
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(UserInfoActivity.this);
        queue.add(request);
    }

    private String bitmapToString(Bitmap bitmap) {
        if (bitmap!=null){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            byte [] array = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(array,Base64.DEFAULT);
        }

        return "";
    }

}
