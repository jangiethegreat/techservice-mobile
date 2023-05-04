package com.example.tech_service;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech_service.Model.BrgyId;
import com.example.tech_service.Model.Certificate;
import com.example.tech_service.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BarangayIdFragment# factory method to
 * create an instance of this fragment.
 */
public class CertificateFragment extends Fragment {


    private Button btnRequirements, btnCertificateSubmit;
    private CheckBox cboxLateRegistration, cboxMedicalAssistance, cboxBurialAssistance, cboxLandOccupancy,
            cboxPublicAttorney, cboxPhilVetOffice, cboxNoDeroatoryRecord, cboxResidency, cboxAreyousure;
    private EditText edtextPurpose, edtextVoter, edtextPendingCase, edtextName, edtextGender, edtextBdate, edtextPlaceBirth, edtextNationality, edtextContactNo, edtextAddressNo,
            edtextAddressStreet, edtextAddressZone, edtextProAddress, edtextYrLiving;
    private View view;
    private Bitmap bitmap = null;
    private ProgressDialog dialog;
    private SharedPreferences preferences;
    String fullname,gender,age,birthdate,contactnumber,street,addressnumber,zone,userID;
    public CertificateFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_certificate, container, false);
        init();
        return view;
    }

    private void init() {
//        SharedPreferences userPref = getActivity().getApplicationContext().getSharedPreferences("user",getContext().MODE_PRIVATE);

        preferences = getActivity().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
//        Log.e("tanginamoJV", preferences.getAll().toString());
        userID = String.valueOf(preferences.getInt("id", 0));
//        preferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        fullname = preferences.getString("name", "");
        gender = preferences.getString("gender", "");
        age = preferences.getString("age", "");
        birthdate = preferences.getString("birthdate", "");
        contactnumber = preferences.getString("phoneNum", "");
        street = preferences.getString("street", "");
        addressnumber = preferences.getString("addressNo", "");
        zone = preferences.getString("addressZone", "");

        btnRequirements = view.findViewById(R.id.btnRequirements);
        btnCertificateSubmit = view.findViewById(R.id.btnCertificateSubmit);

        cboxLateRegistration = view.findViewById(R.id.cboxLateRegistration);
        cboxMedicalAssistance = view.findViewById(R.id.cboxMedicalAssistance);
        cboxBurialAssistance = view.findViewById(R.id.cboxBurialAssistance);
        cboxLandOccupancy = view.findViewById(R.id.cboxLandOccupancy);
        cboxPublicAttorney = view.findViewById(R.id.cboxPublicAttorney);
        cboxPhilVetOffice = view.findViewById(R.id.cboxPhilVetOffice);
        cboxNoDeroatoryRecord = view.findViewById(R.id.cboxNoDeroatoryRecord);
        cboxResidency = view.findViewById(R.id.cboxResidency);


        cboxAreyousure = view.findViewById(R.id.cboxAreyousure);
        edtextPurpose = view.findViewById(R.id.edtextPurpose);
        edtextVoter = view.findViewById(R.id.edtextVoter);
        edtextPendingCase = view.findViewById(R.id.edtextPendingCase);

        edtextName = view.findViewById(R.id.edtextName);
        edtextGender = view.findViewById(R.id.edtextGender);
        edtextBdate = view.findViewById(R.id.edtextBdate);
        edtextPlaceBirth = view.findViewById(R.id.edtextPlaceBirth);
        edtextNationality = view.findViewById(R.id.edtextNationality);
        edtextContactNo = view.findViewById(R.id.edtextContactNo);
        edtextAddressNo = view.findViewById(R.id.edtextAddressNo);
        edtextAddressStreet = view.findViewById(R.id.edtextAddressStreet);
        edtextAddressZone = view.findViewById(R.id.edtextAddressZone);
        edtextProAddress = view.findViewById(R.id.edtextProAddress);
        edtextYrLiving = view.findViewById(R.id.edtextYrLiving);

        edtextName.setText(fullname);
        edtextName.setEnabled(false);

        edtextGender.setText(gender);
        edtextGender.setEnabled(false);


        edtextBdate.setText(birthdate);
        edtextBdate.setEnabled(false);

        edtextContactNo.setText(contactnumber);
        edtextContactNo.setEnabled(false);

        edtextAddressNo.setText(addressnumber);
        edtextAddressNo.setEnabled(false);


        edtextAddressStreet.setText(street);
        edtextAddressStreet.setEnabled(false);


        edtextAddressZone.setText(zone);
        edtextAddressZone.setEnabled(false);

        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);

        CheckBox checkbox1 = view.findViewById(R.id.cboxLateRegistration);
        CheckBox checkbox2 = view.findViewById(R.id.cboxMedicalAssistance);
        CheckBox checkbox3 = view.findViewById(R.id.cboxBurialAssistance);
        CheckBox checkbox4 = view.findViewById(R.id.cboxLandOccupancy);
        CheckBox checkbox5 = view.findViewById(R.id.cboxPublicAttorney);
        CheckBox checkbox6 = view.findViewById(R.id.cboxPhilVetOffice);
        CheckBox checkbox7 = view.findViewById(R.id.cboxNoDeroatoryRecord);
        CheckBox checkbox8 = view.findViewById(R.id.cboxResidency);

        btnCertificateSubmit.setOnClickListener(v -> {
            if (edtextName.getText().toString().isEmpty()
                    ||edtextGender.getText().toString().isEmpty()
                    ||edtextBdate.getText().toString().isEmpty()
                    ||edtextPlaceBirth.getText().toString().isEmpty()
                    ||edtextNationality.getText().toString().isEmpty()
                    ||edtextContactNo.getText().toString().isEmpty()
                    ||edtextAddressNo.getText().toString().isEmpty()
                    ||edtextAddressStreet.getText().toString().isEmpty()
                    ||edtextAddressZone.getText().toString().isEmpty()
            ){
                Toast.makeText(getContext(), "Required", Toast.LENGTH_SHORT).show();
                Log.e("tanginamoJV", "tanginamo JV kulang input mo");
            } else {
                dialog.setMessage("Success");
                dialog.show();
                    if (checkbox1.isChecked()) {
                        String typeofrequest_id = "2";
                        String price = "100";
                        String typeofdoc = "Late Registration of Live Birth";
                        submit1(typeofrequest_id,typeofdoc,price);
                        checkbox1.setChecked(false);
                    }

                    if (checkbox2.isChecked()) {
                        String typeofrequest_id = "4";
                        String price = "50";
                        String typeofdoc = "Medical Assistance";
                        submit2(typeofrequest_id,typeofdoc,price);
                        checkbox2.setChecked(false);
                    }

                    if (checkbox3.isChecked()) {
                        String typeofrequest_id = "6";
                        String price = "50";
                        String typeofdoc = "Burial Assistance";
                        submit3(typeofrequest_id,typeofdoc,price);
                        checkbox3.setChecked(false);
                    }

                    if (checkbox4.isChecked()) {
                        String typeofrequest_id = "8";
                        String price = "120";
                        String typeofdoc = "Actual Occupancy/Land Titling";
                        submit4(typeofrequest_id,typeofdoc,price);
                        checkbox4.setChecked(false);
                    }

                    if (checkbox5.isChecked()) {
                        String typeofrequest_id = "3";
                        String price = "100";
                        String typeofdoc = "Public Attorneys Office (PAO)";
                        submit5(typeofrequest_id,typeofdoc,price);
                        checkbox5.setChecked(false);
                    }

                    if (checkbox6.isChecked()) {
                        String typeofrequest_id = "5";
                        String price = "90";
                        String typeofdoc = "Philippines Veterans Office (PVAO)";
                        submit6(typeofrequest_id,typeofdoc,price);
                        checkbox6.setChecked(false);
                    }

                    if (checkbox7.isChecked()) {
                        String typeofrequest_id = "7";
                        String price = "100";
                        String typeofdoc = "No Derogatory Record/Good Moral";
                        submit7(typeofrequest_id,typeofdoc,price);
                        checkbox7.setChecked(false);
                    }

                    if (checkbox8.isChecked()) {
                        String typeofrequest_id = "9";
                        String price = "120";
                        String typeofdoc = "Residency";
                        submit8(typeofrequest_id,typeofdoc,price);
                        checkbox8.setChecked(false);
                    }


            }
        });
    }


    private void submit1(String typeofrequest_id, String typeofdoc, String price) {
        StringRequest request = new StringRequest
                (Request.Method.POST, Constant.ADD_CERTIFICATE, response -> {
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
            map.put("purpose", edtextPurpose.getText().toString().trim());
            map.put("isRegistered", edtextVoter.getText().toString().trim());
            map.put("havePendingCase", edtextPendingCase.getText().toString().trim());
            map.put("name", edtextName.getText().toString().trim());
            map.put("gender", edtextGender.getText().toString().trim());
            map.put("birthdate", edtextBdate.getText().toString().trim());
            map.put("p_birth", edtextPlaceBirth.getText().toString().trim());
            map.put("nationality", edtextNationality.getText().toString().trim());
            map.put("contact_num", edtextContactNo.getText().toString().trim());
            map.put("addressNo", edtextAddressNo.getText().toString().trim());
            map.put("street", edtextAddressStreet.getText().toString().trim());
            map.put("addressZone", edtextAddressZone.getText().toString().trim());
            map.put("provincialAddress", edtextProAddress.getText().toString().trim());
            map.put("yearLiving", edtextYrLiving.getText().toString().trim());
            map.put("areYouSure", cboxAreyousure.getText().toString().trim());
            map.put("typeofrequest_id", typeofrequest_id);
            map.put("typeofdoc", typeofdoc);
            map.put("price", price);
//                map.put("photo", bitmapToString(bitmap));
            return map;
        }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

    }

    private void submit2(String typeofrequest_id, String typeofdoc, String price) {
        StringRequest request = new StringRequest
                (Request.Method.POST, Constant.ADD_CERTIFICATE, response -> {
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
                map.put("purpose", edtextPurpose.getText().toString().trim());
                map.put("isRegistered", edtextVoter.getText().toString().trim());
                map.put("havePendingCase", edtextPendingCase.getText().toString().trim());
                map.put("name", edtextName.getText().toString().trim());
                map.put("gender", edtextGender.getText().toString().trim());
                map.put("birthdate", edtextBdate.getText().toString().trim());
                map.put("p_birth", edtextPlaceBirth.getText().toString().trim());
                map.put("nationality", edtextNationality.getText().toString().trim());
                map.put("contact_num", edtextContactNo.getText().toString().trim());
                map.put("addressNo", edtextAddressNo.getText().toString().trim());
                map.put("street", edtextAddressStreet.getText().toString().trim());
                map.put("addressZone", edtextAddressZone.getText().toString().trim());
                map.put("provincialAddress", edtextProAddress.getText().toString().trim());
                map.put("yearLiving", edtextYrLiving.getText().toString().trim());
                map.put("areYouSure", cboxAreyousure.getText().toString().trim());
                map.put("typeofrequest_id", typeofrequest_id);
                map.put("typeofdoc", typeofdoc);
                map.put("price", price);

//                map.put("photo", bitmapToString(bitmap));
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

    }

    private void submit3(String typeofrequest_id, String typeofdoc, String price) {
        StringRequest request = new StringRequest
                (Request.Method.POST, Constant.ADD_CERTIFICATE, response -> {
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
                map.put("purpose", edtextPurpose.getText().toString().trim());
                map.put("isRegistered", edtextVoter.getText().toString().trim());
                map.put("havePendingCase", edtextPendingCase.getText().toString().trim());
                map.put("name", edtextName.getText().toString().trim());
                map.put("gender", edtextGender.getText().toString().trim());
                map.put("birthdate", edtextBdate.getText().toString().trim());
                map.put("p_birth", edtextPlaceBirth.getText().toString().trim());
                map.put("nationality", edtextNationality.getText().toString().trim());
                map.put("contact_num", edtextContactNo.getText().toString().trim());
                map.put("addressNo", edtextAddressNo.getText().toString().trim());
                map.put("street", edtextAddressStreet.getText().toString().trim());
                map.put("addressZone", edtextAddressZone.getText().toString().trim());
                map.put("provincialAddress", edtextProAddress.getText().toString().trim());
                map.put("yearLiving", edtextYrLiving.getText().toString().trim());
                map.put("areYouSure", cboxAreyousure.getText().toString().trim());
                map.put("typeofrequest_id", typeofrequest_id);
                map.put("typeofdoc", typeofdoc);
                map.put("price", price);
//                map.put("photo", bitmapToString(bitmap));
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

    }

    private void submit4(String typeofrequest_id, String typeofdoc, String price) {
        StringRequest request = new StringRequest
                (Request.Method.POST, Constant.ADD_CERTIFICATE, response -> {
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
                map.put("purpose", edtextPurpose.getText().toString().trim());
                map.put("isRegistered", edtextVoter.getText().toString().trim());
                map.put("havePendingCase", edtextPendingCase.getText().toString().trim());
                map.put("name", edtextName.getText().toString().trim());
                map.put("gender", edtextGender.getText().toString().trim());
                map.put("birthdate", edtextBdate.getText().toString().trim());
                map.put("p_birth", edtextPlaceBirth.getText().toString().trim());
                map.put("nationality", edtextNationality.getText().toString().trim());
                map.put("contact_num", edtextContactNo.getText().toString().trim());
                map.put("addressNo", edtextAddressNo.getText().toString().trim());
                map.put("street", edtextAddressStreet.getText().toString().trim());
                map.put("addressZone", edtextAddressZone.getText().toString().trim());
                map.put("provincialAddress", edtextProAddress.getText().toString().trim());
                map.put("yearLiving", edtextYrLiving.getText().toString().trim());
                map.put("areYouSure", cboxAreyousure.getText().toString().trim());
                map.put("typeofrequest_id", typeofrequest_id);
                map.put("typeofdoc", typeofdoc);
                map.put("price", price);
//                map.put("photo", bitmapToString(bitmap));
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

    }

    private void submit5(String typeofrequest_id, String typeofdoc, String price) {
        StringRequest request = new StringRequest
                (Request.Method.POST, Constant.ADD_CERTIFICATE, response -> {
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
                map.put("purpose", edtextPurpose.getText().toString().trim());
                map.put("isRegistered", edtextVoter.getText().toString().trim());
                map.put("havePendingCase", edtextPendingCase.getText().toString().trim());
                map.put("name", edtextName.getText().toString().trim());
                map.put("gender", edtextGender.getText().toString().trim());
                map.put("birthdate", edtextBdate.getText().toString().trim());
                map.put("p_birth", edtextPlaceBirth.getText().toString().trim());
                map.put("nationality", edtextNationality.getText().toString().trim());
                map.put("contact_num", edtextContactNo.getText().toString().trim());
                map.put("addressNo", edtextAddressNo.getText().toString().trim());
                map.put("street", edtextAddressStreet.getText().toString().trim());
                map.put("addressZone", edtextAddressZone.getText().toString().trim());
                map.put("provincialAddress", edtextProAddress.getText().toString().trim());
                map.put("yearLiving", edtextYrLiving.getText().toString().trim());
                map.put("areYouSure", cboxAreyousure.getText().toString().trim());
                map.put("typeofrequest_id", typeofrequest_id);
                map.put("typeofdoc", typeofdoc);
                map.put("price", price);
//                map.put("photo", bitmapToString(bitmap));
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

    }

    private void submit6(String typeofrequest_id, String typeofdoc, String price) {
        StringRequest request = new StringRequest
                (Request.Method.POST, Constant.ADD_CERTIFICATE, response -> {
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
                map.put("purpose", edtextPurpose.getText().toString().trim());
                map.put("isRegistered", edtextVoter.getText().toString().trim());
                map.put("havePendingCase", edtextPendingCase.getText().toString().trim());
                map.put("name", edtextName.getText().toString().trim());
                map.put("gender", edtextGender.getText().toString().trim());
                map.put("birthdate", edtextBdate.getText().toString().trim());
                map.put("p_birth", edtextPlaceBirth.getText().toString().trim());
                map.put("nationality", edtextNationality.getText().toString().trim());
                map.put("contact_num", edtextContactNo.getText().toString().trim());
                map.put("addressNo", edtextAddressNo.getText().toString().trim());
                map.put("street", edtextAddressStreet.getText().toString().trim());
                map.put("addressZone", edtextAddressZone.getText().toString().trim());
                map.put("provincialAddress", edtextProAddress.getText().toString().trim());
                map.put("yearLiving", edtextYrLiving.getText().toString().trim());
                map.put("areYouSure", cboxAreyousure.getText().toString().trim());
                map.put("typeofrequest_id", typeofrequest_id);
                map.put("typeofdoc", typeofdoc);
                map.put("price", price);
//                map.put("photo", bitmapToString(bitmap));
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

    }

    private void submit7(String typeofrequest_id, String typeofdoc, String price) {
        StringRequest request = new StringRequest
                (Request.Method.POST, Constant.ADD_CERTIFICATE, response -> {
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
                map.put("purpose", edtextPurpose.getText().toString().trim());
                map.put("isRegistered", edtextVoter.getText().toString().trim());
                map.put("havePendingCase", edtextPendingCase.getText().toString().trim());
                map.put("name", edtextName.getText().toString().trim());
                map.put("gender", edtextGender.getText().toString().trim());
                map.put("birthdate", edtextBdate.getText().toString().trim());
                map.put("p_birth", edtextPlaceBirth.getText().toString().trim());
                map.put("nationality", edtextNationality.getText().toString().trim());
                map.put("contact_num", edtextContactNo.getText().toString().trim());
                map.put("addressNo", edtextAddressNo.getText().toString().trim());
                map.put("street", edtextAddressStreet.getText().toString().trim());
                map.put("addressZone", edtextAddressZone.getText().toString().trim());
                map.put("provincialAddress", edtextProAddress.getText().toString().trim());
                map.put("yearLiving", edtextYrLiving.getText().toString().trim());
                map.put("areYouSure", cboxAreyousure.getText().toString().trim());
                map.put("typeofrequest_id", typeofrequest_id);
                map.put("typeofdoc", typeofdoc);
                map.put("price", price);
//                map.put("photo", bitmapToString(bitmap));
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

    }

    private void submit8(String typeofrequest_id, String typeofdoc, String price) {
        StringRequest request = new StringRequest
                (Request.Method.POST, Constant.ADD_CERTIFICATE, response -> {
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
                map.put("purpose", edtextPurpose.getText().toString().trim());
                map.put("isRegistered", edtextVoter.getText().toString().trim());
                map.put("havePendingCase", edtextPendingCase.getText().toString().trim());
                map.put("name", edtextName.getText().toString().trim());
                map.put("gender", edtextGender.getText().toString().trim());
                map.put("birthdate", edtextBdate.getText().toString().trim());
                map.put("p_birth", edtextPlaceBirth.getText().toString().trim());
                map.put("nationality", edtextNationality.getText().toString().trim());
                map.put("contact_num", edtextContactNo.getText().toString().trim());
                map.put("addressNo", edtextAddressNo.getText().toString().trim());
                map.put("street", edtextAddressStreet.getText().toString().trim());
                map.put("addressZone", edtextAddressZone.getText().toString().trim());
                map.put("provincialAddress", edtextProAddress.getText().toString().trim());
                map.put("yearLiving", edtextYrLiving.getText().toString().trim());
                map.put("areYouSure", cboxAreyousure.getText().toString().trim());
                map.put("typeofrequest_id", typeofrequest_id);
                map.put("typeofdoc", typeofdoc);
                map.put("price", price);
//                map.put("photo", bitmapToString(bitmap));
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

    }
}
