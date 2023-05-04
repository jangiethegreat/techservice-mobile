package com.example.tech_service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech_service.Model.Scholarship;
import com.example.tech_service.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link #newInstance} factory method to
// * create an instance of this fragment.
// */
public class ScholarshipFragment extends Fragment {

    private Button btnUploadCor, btnUploadGrades, ScholarshipSubmit;
    private EditText edtFullName,
            edtPhoneno,
            edtSchool,
            edtCourse,
            edtEmail;
    private Bitmap bitmap = null;
    private ProgressDialog dialog;
    private SharedPreferences preferences;
    private View view;
    String userID, username, useremail,usernumber,uploadImage,uploadImage1;
    private ActivityResultLauncher<Intent> imagePickLauncher,imagePickLauncher1;

    public ScholarshipFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_scholarship, container, false);
        init();
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
                                ImageView imageView = getView().findViewById(R.id.cor_imageview);

                                imageView.setImageURI(uri);


                                // Make an API request to upload the image
                                uploadImage = uploadImage(uri);


                            }
                        }
                    }
                });
        imagePickLauncher1 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                // Get the URI of the selected image
                                Uri uri = data.getData();

                                // Do something with the URI, such as load the image into an ImageView

                                ImageView imageView1 = getView().findViewById(R.id.grades_imageview);

                                imageView1.setImageURI(uri);

                                // Make an API request to upload the image

                                uploadImage1 = uploadImage1(uri);

                            }
                        }
                    }
                });

        return view;


    }


    private void init() {
        preferences = getActivity().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        Log.e("tanginamoJV", preferences.getAll().toString());
        userID = String.valueOf(preferences.getInt("id", 0));
        username = preferences.getString("name", "");
        useremail = preferences.getString("email", "");
        usernumber = preferences.getString("phoneNum", "");

        btnUploadCor = view.findViewById(R.id.btnUploadCor);
        btnUploadGrades = view.findViewById(R.id.btnUploadGrades);
        ScholarshipSubmit = view.findViewById(R.id.btnScholarshipSubmit);
        edtFullName = view.findViewById(R.id.edtFullName);
        edtFullName.setText(username);
        edtFullName.setEnabled(false);
        edtPhoneno = view.findViewById(R.id.edtPhoneno);
        edtPhoneno.setText(usernumber);
        edtPhoneno.setEnabled(false);
        edtSchool = view.findViewById(R.id.edtSchool);
        edtCourse = view.findViewById(R.id.edtCourse);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtEmail.setText(useremail);
        edtEmail.setEnabled(false);

        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);

        ScholarshipSubmit.setOnClickListener(v -> {
//

            if (edtFullName.getText().toString().isEmpty()
                    || edtPhoneno.getText().toString().isEmpty()
                    || edtSchool.getText().toString().isEmpty()
                    || edtCourse.getText().toString().isEmpty()
                    || uploadImage == null
                    || uploadImage1 == null) {
                Log.e("tanginamoJV", "tanginamo JV kulang input mo");
                Toast.makeText(getContext(), "Required", Toast.LENGTH_SHORT).show();
            } else {
                submit();
            }
        });

        btnUploadCor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                imagePickLauncher.launch(intent);

            }
        });
        btnUploadGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                imagePickLauncher1.launch(intent);
            }
        });

    }


    private void submit() {
        dialog.setMessage("Success");
        dialog.show();
        // Get the image data as a byte array


        StringRequest request = new StringRequest
                (Request.Method.POST, Constant.ADD_SCHOLARSHIP, response -> {


//                        try {
//                            JSONObject object = new JSONObject(response);
////                            if (object.getBoolean("success")) {
//                                JSONObject scholarshipObject = object.getJSONObject("scholarship");
//                                JSONObject userObject = scholarshipObject.getJSONObject("user");
//
////                                User user = new User();
////                                user.setId(userObject.getInt("id"));
////                                user.setName(userObject.getString("name"));
////                                user.setNumber(userObject.getString("phoneNum"));
//                                String id = String.valueOf(userObject.getInt("id"));
//                                String fullname = String.valueOf(userObject.getString("name"));
//
//                                Log.e("tag", id);
//                                Log.e("tag", fullname);
////                                String phone = String.valueOf(object.getString(""))
//                                Scholarship post = new Scholarship();
////                                post.setUser(user);
//                                post.setUser_id("id");
//                                post.setFname("putanginammoJV");
//                                post.setPhonenum(edtPhoneno.getText().toString());
//                                post.setSchool(edtSchool.getText().toString());
//                                post.setCourse(edtCourse.getText().toString());
//                                post.setEmail(edtEmail.getText().toString());
////                          post.setDate(barangayidObject.getString("created_at"));
////                          HomeFragment.arrayList.add(0, post);
////                          HomeFragment.recyclerView.getAdapter().notifyItemInserted(0);
////                          HomeFragment.recyclerView.getAdapter().notifyDataSetChanged();
//                                Toast.makeText(getContext(), "Posted", Toast.LENGTH_SHORT).show();
//                                ((AuthActivity) getContext()).finish();
//
////                            }
//                        }catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                    dialog.dismiss();

                }, error -> {
                    error.printStackTrace();
                    dialog.dismiss();
                }) {
            // add token to header


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = preferences.getString("token", "");
                HashMap<String, String> map = new HashMap<>();
                map.put("Authorization", "Bearer " + token);
                return map;
            }

            // add params

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> map = new HashMap<>();
                map.put("user_id", userID);
                map.put("scholarFname", edtFullName.getText().toString().trim());
                map.put("scholarPhonenum", edtPhoneno.getText().toString().trim());
                map.put("scholarSchool", edtSchool.getText().toString().trim());
                map.put("scholarCourse", edtCourse.getText().toString().trim());
                map.put("scholarEmail", edtEmail.getText().toString().trim());
                map.put("image", uploadImage);
                map.put("image1", uploadImage1);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

    }

    private byte[] getImageData(Uri uri) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
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
    private byte[] getImageData1(Uri uri) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
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
        return imageDataString;
    }
    private String uploadImage1(Uri uri) {
        byte[] imageData = getImageData1(uri);

        // Convert the image data to a Base64-encoded string
        String imageDataString1 = Base64.encodeToString(imageData, Base64.NO_WRAP);
        return imageDataString1;
    }


}
