package com.example.tech_service;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.tech_service.Model.Complaint;
import com.example.tech_service.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**

 */
public class ReportsFragment extends Fragment {

    private Button btnComplaintSubmit;
    private EditText edtxtComplainantName,
            edtxtCAddressno,
            edtxtCStreet,
            edtxtCAddresszone,
            edtxtRespondentName,
            edtxtRespondentAge,
            edtxtRAddressno,
            edtxtRStreet,
            edtxtRAddresszone,
            edtxtComplaintDesc,
            edtxtLocationAddressno,
            edtxtLocationStreet,
            edtxtComplaintDate,
            edtxtComplaintWhy,
            edtxtComplainantPrayer;
    private CheckBox cboxComplainantAgrees;
    private Bitmap bitmap = null;
    private ProgressDialog dialog;
    private SharedPreferences preferences;
    private View view;
    String fullname,gender,age,birthdate,contactnumber,street,addressnumber,zone,userID;

    public ReportsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_reports, container, false);
        init();
        return view;
    }

    private void init() {
        preferences = getActivity().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
//        Log.e("tanginamoJV", preferences.getAll().toString());
        userID = String.valueOf(preferences.getInt("id", 0));
//        preferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        fullname = preferences.getString("name", "");
        contactnumber = preferences.getString("phoneNum", "");
        street = preferences.getString("street", "");
        addressnumber = preferences.getString("addressNo", "");
        zone = preferences.getString("addressZone", "");

        btnComplaintSubmit = view.findViewById(R.id.btnComplaintSubmit);

        edtxtComplainantName =view. findViewById(R.id.edtxtComplainantName);
        edtxtCAddressno = view.findViewById(R.id.edtxtCAddressno);
        edtxtCStreet = view.findViewById(R.id.edtxtCStreet);
        edtxtCAddresszone = view.findViewById(R.id.edtxtCAddresszone);

        edtxtComplainantName.setText(fullname);
        edtxtComplainantName.setEnabled(false);

        edtxtCAddressno.setText(addressnumber);
        edtxtCAddressno.setEnabled(false);


        edtxtCStreet.setText(street);
        edtxtCStreet.setEnabled(false);

        edtxtCAddresszone.setText(zone);
        edtxtCAddresszone.setEnabled(false);

        edtxtRespondentName = view.findViewById(R.id.edtxtRespondentName);
        edtxtRespondentAge = view.findViewById(R.id.edtxtRespondentAge);
        edtxtRAddressno = view.findViewById(R.id.edtxtRAddressno);
        edtxtRStreet = view.findViewById(R.id.edtxtRStreet);
        edtxtRAddresszone = view.findViewById(R.id.edtxtRAddresszone);
        edtxtComplaintDesc = view.findViewById(R.id.edtxtComplaintDesc);
        edtxtLocationAddressno = view.findViewById(R.id.edtxtLocationAddressno);
        edtxtLocationStreet = view.findViewById(R.id.edtxtLocationStreet);
        edtxtComplaintDate = view.findViewById(R.id.edtxtComplaintDate);
        edtxtComplaintWhy = view.findViewById(R.id.edtxtComplaintWhy);
        edtxtComplainantPrayer = view.findViewById(R.id.edtxtComplainantPrayer);
        cboxComplainantAgrees = view.findViewById(R.id.cboxComplainantAgrees);

        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);

        btnComplaintSubmit.setOnClickListener(v -> {
            if (edtxtComplainantName.getText().toString().isEmpty()
                    ||edtxtCAddressno.getText().toString().isEmpty()
                    ||edtxtCStreet.getText().toString().isEmpty()
                    ||edtxtCAddresszone.getText().toString().isEmpty()
                    ||edtxtRespondentName.getText().toString().isEmpty()
                    ||edtxtRespondentAge.getText().toString().isEmpty()
                    ||edtxtRAddressno.getText().toString().isEmpty()
                    ||edtxtRStreet.getText().toString().isEmpty()
                    ||edtxtRAddresszone.getText().toString().isEmpty()
                    ||edtxtComplaintDesc.getText().toString().isEmpty()

                    ||edtxtLocationAddressno.getText().toString().isEmpty()
                    ||edtxtLocationStreet.getText().toString().isEmpty()
                    ||edtxtComplaintDate.getText().toString().isEmpty()
                    ||edtxtComplaintWhy.getText().toString().isEmpty()
                    ||edtxtComplainantPrayer.getText().toString().isEmpty()
                    ||cboxComplainantAgrees.getText().toString().isEmpty()
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
                (Request.Method.POST, Constant.ADD_COMPLAINT, response -> {

//                    try {
//                        JSONObject object = new JSONObject(response);
//                        if (object.getBoolean("success")) {
//                            JSONObject complaintObject = object.getJSONObject("complaint");
//                            JSONObject userObject = complaintObject.getJSONObject("user");
//
//                            User user = new User();
//                            user.setId(userObject.getInt("id"));
//                            user.setName(userObject.getString("name"));
//
//
//                            Complaint post = new Complaint();
//                            post.setUser(user);
//                            post.setId(complaintObject.getInt("user_id"));
//                            post.setComplainantName(complaintObject.getString("complainantName"));
//                            post.setcAddressno(complaintObject.getString("cAddressno"));
//                            post.setcStreet(complaintObject.getString("cStreet"));
//                            post.setcAddresszone(complaintObject.getString("cAddresszone"));
//                            post.setrespondentName(complaintObject.getString("respondentName"));
//                            post.setrespondentAge(complaintObject.getString("respondentAge"));
//                            post.setrAddressno(complaintObject.getString("rAddressno"));
//                            post.setrStreet(complaintObject.getString("rStreet"));
//                            post.setrAddresszone(complaintObject.getString("rAddresszone"));
//                            post.setcomplaintDesc(complaintObject.getString("complaintDesc"));
//                            post.setlocationAddressno(complaintObject.getString("locationAddressno"));
//                            post.setlocationStreet(complaintObject.getString("locationStreet"));
//                            post.setcomplaintWhy(complaintObject.getString("complaintWhy"));
//                            post.setcomplainantPrayer(complaintObject.getString("complainantPrayer"));
//                            post.setcomplaintDate(complaintObject.getString("complaintDate"));
//                            post.setcomplainantAgrees(complaintObject.getString("complainantAgrees"));
////                          post.setDate(barangayidObject.getString("created_at"));
////                          HomeFragment.arrayList.add(0, post);
////                          HomeFragment.recyclerView.getAdapter().notifyItemInserted(0);
////                          HomeFragment.recyclerView.getAdapter().notifyDataSetChanged();
//                            Toast.makeText(getContext(), "Posted", Toast.LENGTH_SHORT).show();
//                            ((AuthActivity) getContext()).finish();
//
//                        }
//                    }catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                    dialog.dismiss();
                },error -> {
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
                map.put("complainantName", edtxtComplainantName.getText().toString().trim());
                map.put("cAddressno", edtxtCAddressno.getText().toString().trim());
                map.put("cStreet", edtxtCStreet.getText().toString().trim());
                map.put("cAddresszone", edtxtCAddresszone.getText().toString().trim());
                map.put("respondentName", edtxtRespondentName.getText().toString().trim());
                map.put("respondentAge", edtxtRespondentAge.getText().toString().trim());
                map.put("rAddressno", edtxtRAddressno.getText().toString().trim());
                map.put("rStreet", edtxtRStreet.getText().toString().trim());
                map.put("rAddresszone", edtxtRAddresszone.getText().toString().trim());
                map.put("complaintDesc", edtxtComplaintDesc.getText().toString().trim());
                map.put("locationAddressno", edtxtLocationAddressno.getText().toString().trim());
                map.put("locationStreet", edtxtLocationStreet.getText().toString().trim());
                map.put("complaintDate", edtxtComplaintDate.getText().toString().trim());
                map.put("complaintWhy", edtxtComplaintWhy.getText().toString().trim());
                map.put("complainantPrayer", edtxtComplainantPrayer.getText().toString().trim());
                map.put("complainantAgrees", cboxComplainantAgrees.getText().toString().trim());
//                map.put("photo", bitmapToString(bitmap));
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }

} //end


