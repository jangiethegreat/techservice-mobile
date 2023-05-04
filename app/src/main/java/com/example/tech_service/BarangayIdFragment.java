package com.example.tech_service;


import static androidx.fragment.app.FragmentManager.TAG;
import static com.example.tech_service.Constant.SHOW_BRGYID;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech_service.Model.BrgyId;
import com.example.tech_service.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BarangayIdFragment# factory method to
 * create an instance of this fragment.
 */
public class BarangayIdFragment extends Fragment {

    private int position = 0, id = 1;
    private Button btnBrgyIdSubmit;
    private EditText
            editTextAddPrecintNo,
            editTextEContactName,
            editTextEContactNo,
            editTextEContactAdd,
        editTextFullName,
            editTextBirthday,
            editTextContact,
            editTextAddNo,
            editTextAddStreet,
            editTextAddZone;
    private View view;
    private Bitmap bitmap = null;
    private ProgressDialog dialog;
    private SharedPreferences preferences, userPref;
    public static final String TAG = "FragmentManager";
    private int typeofrequest_id =1 ;
    String fullname,gender,age,birthdate,contactnumber,street,addressnumber,zone,userID;

    public BarangayIdFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_barangay_id, container, false);
        init();
        return view;

    }


    private void init() {
//        SharedPreferences userPref = getActivity().getApplicationContext().getSharedPreferences("user",getContext().MODE_PRIVATE);
//        userPref = getActivity().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        preferences = getActivity().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        Log.e("tanginamoJV", preferences.getAll().toString());
        userID = String.valueOf(preferences.getInt("id", 0));

        fullname = preferences.getString("name", "");
        birthdate = preferences.getString("birthdate", "");
        contactnumber = preferences.getString("phoneNum", "");
        street = preferences.getString("street", "");
        addressnumber = preferences.getString("addressNo", "");
        zone = preferences.getString("addressZone", "");

        btnBrgyIdSubmit = view.findViewById(R.id.btnBrgyIdSubmit);

        editTextFullName = view.findViewById(R.id.editTextFullName);
        editTextBirthday = view.findViewById(R.id.editTextBirthday);
        editTextContact = view.findViewById(R.id.editTextContact);
        editTextAddNo = view.findViewById(R.id.editTextAddNo);
        editTextAddStreet = view.findViewById(R.id.editTextAddStreet);
        editTextAddZone = view.findViewById(R.id.editTextAddZone);

        editTextAddPrecintNo = view.findViewById(R.id.editTextAddPrecintNo);
        editTextEContactName = view.findViewById(R.id.editTextEContactName);
        editTextEContactNo = view.findViewById(R.id.editTextEContactNo);
        editTextEContactAdd = view.findViewById(R.id.editTextEContactAdd);

        editTextFullName.setText(fullname);
        editTextFullName.setEnabled(false);

        editTextBirthday.setText(birthdate);
        editTextBirthday.setEnabled(false);

        editTextContact.setText(contactnumber);
        editTextContact.setEnabled(false);

        editTextAddStreet.setText(street);
        editTextAddStreet.setEnabled(false);

        editTextAddNo.setText(addressnumber);
        editTextAddNo.setEnabled(false);

        editTextAddZone.setText(zone);
        editTextAddZone.setEnabled(false);




        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);

        btnBrgyIdSubmit.setOnClickListener(v -> {
            if (editTextFullName.getText().toString().isEmpty()
                    ||editTextBirthday.getText().toString().isEmpty()
                    ||editTextContact.getText().toString().isEmpty()
                    ||editTextAddNo.getText().toString().isEmpty()
                    ||editTextAddStreet.getText().toString().isEmpty()
                    ||editTextAddZone.getText().toString().isEmpty()
                    ||editTextAddPrecintNo.getText().toString().isEmpty()
                    ||editTextEContactName.getText().toString().isEmpty()
                    ||editTextEContactNo.getText().toString().isEmpty()
                    ||editTextEContactAdd.getText().toString().isEmpty()
            ){
                Toast.makeText(getContext(), "Required", Toast.LENGTH_SHORT).show();
                Log.e("tanginamoJV", "tanginamo JV kulang input mo");
            } else {
                submit();
            }
        });
    }




    private void submit() {


        dialog.setMessage("Posting");
        dialog.show();
        StringRequest request = new StringRequest
                (Request.Method.POST, Constant.ADD_BRGYID, response -> {

//                    try {
//                        JSONObject object = new JSONObject(response);
//                        if (object. getBoolean("success")) {
//                            JSONObject barangayidObject = object.getJSONObject("barangayid");
//                            JSONObject userObject = barangayidObject.getJSONObject("user");
//
//                            User user = new User();
//                            user.setId(userObject.getInt("id"));
//                            user.setName(userObject.getString("name"));
//                            user.setDate(userObject.getString("birthdate"));
//
//                            BrgyId barangayid = new BrgyId();
//                            barangayid.setUser(user);
//                            barangayid.setId(barangayidObject.getInt("user_id"));
//                            barangayid.setFullname(barangayidObject.getString("fullname"));
//                            barangayid.setDate(barangayidObject.getString("birthdate"));
//                            barangayid.setContactno(barangayidObject.getString("contactno"));
//                            barangayid.setAddressNo(barangayidObject.getInt("addressNo"));
//                            barangayid.setStreet(barangayidObject.getString("street"));
//                            barangayid.setAddressZone(barangayidObject.getInt("addressZone"));
//
//                            barangayid.setPrecintNo(barangayidObject.getString("precintNo"));
//                            barangayid.setContactperson(barangayidObject.getString("contactperson"));
//                            barangayid.setGuardianContact(barangayidObject.getString("guardianContact"));
//                            barangayid.setGuardianAddress(barangayidObject.getString("guardianAddress"));
////                            barangayid.setTypeofrequest_id(barangayidObject.getInt("typeofrequest_id"));
////                            barangayid.setPrice(barangayidObject.getInt("price"));
////                            barangayid.setPrice(typeofrequest_id.getPrice());
//
//
////                    post.setDate(barangayidObject.getString("created_at"));
//
////                    HomeFragment.arrayList.add(0, post);
////                    HomeFragment.recyclerView.getAdapter().notifyItemInserted(0);
////                    HomeFragment.recyclerView.getAdapter().notifyDataSetChanged();
//                            Toast.makeText(getContext(), "Posted", Toast.LENGTH_SHORT).show();
//                            ((AuthActivity) getContext()).finish();
//
//                        }
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
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
                map.put("fullname", editTextFullName.getText().toString().trim());
                map.put("birthdate", editTextBirthday.getText().toString().trim());
                map.put("contactno", editTextContact.getText().toString().trim());
                map.put("addressNo", editTextAddNo.getText().toString().trim());
                map.put("street", editTextAddStreet.getText().toString().trim());
                map.put("addressZone", editTextAddZone.getText().toString().trim());
                map.put("precintNo", editTextAddPrecintNo.getText().toString().trim());
                map.put("contactperson", editTextEContactName.getText().toString().trim());
                map.put("guardianContact", editTextEContactNo.getText().toString().trim());
                map.put("guardianAddress", editTextEContactAdd.getText().toString().trim());
//                map.put("photo", bitmapToString(bitmap));
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

    }
}












//                            SharedPreferences.Editor editor = userPref.edit();
//                            editor.putString("name",editTextFullName.getText().toString().trim());
//                            editor.putString("birthdate",editTextBirthday.getText().toString().trim());
//                            editor.putString("contactno",editTextContact.getText().toString().trim());
//                            editor.putString("addressNo",editTextAddNo.ge tText().toString().trim());
//                            editor.putString("street",editTextAddStreet.getText().toString().trim());
//                            editor.putString("addressZone",editTextAddZone.getText().toString().trim());

//
//    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
//            Constant.ADD_BRGYID, response -> {
//            // Do something with response
//            //mTextView.setText(response.toString());
//
//            // Process the JSON
//            try {
//
//                String fullname = response.getString("fullname");
//                String birthdate = response.getString("birthdate");
//                String contactno = response.getString("contactno");
//                String addressNo = response.getString("addressNo");
//                String street = response.getString("street");
//                String addressZone = response.getString("addressZone");
//                // String item_no = response.getString("item_id");
//
//                // Display the formatted json data in text view
//                editTextFullName.setText(fullname);
//                editTextBirthday.setText(birthdate);
//                editTextContact.setText(contactno);
//                editTextAddNo.setText(addressNo);
//                editTextAddStreet.setText(street);
//                editTextAddZone.setText(addressZone);
//                //search.setText(String.valueOf(item_no));
//                //  }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        });

