package com.example.tech_service;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MyRequestFragment extends Fragment {
    private View view;
    private ProgressDialog dialog;
    private ProgressDialog dialog1;
    private SharedPreferences preferences;
    Integer userID;
    Integer total3;
    Integer total4;
    Integer totalprice;
    String description;
    Button btnCartProceed;
    Integer deliveryId;
    String deliveryValue;
    Integer paymentId;
    String paymentValue;
    String barangayidsidstring;
    String certificatesidstring;
    public MyRequestFragment() {
        // Required empty public constructor
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_request,container,false);
//        init();
        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);
        dialog.setMessage("Loading");
        dialog.show();
//
        TextView pricee = view.findViewById(R.id.price);
        TextView descriptionn = view.findViewById(R.id.description);
        TextView total1 = view.findViewById(R.id.total1);
        TextView total2 = view.findViewById(R.id.total2);
//        Price divider5

        preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        userID = preferences.getInt("id", 0);

        String url = "https://northsignalvillage.com/api/mobile/createRequest/"+userID;
        StringRequest request = new StringRequest(Request.Method.GET, url,
                response -> {


                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject object = jsonObject.getJSONObject("values");

                        description = object.getString("description");
                        totalprice = object.getInt("totalprice");
                        barangayidsidstring = object.getString("barangayidsidstring");
                        certificatesidstring = object.getString("certificatesidstring");

                        total4 = totalprice;
                        pricee.setText(String.valueOf(totalprice));
                        descriptionn.setText(description);
                        total1.setText(String.valueOf(totalprice));

//                            progressDialog.dismiss();
                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                    dialog.dismiss();
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Errorr", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);

        RadioGroup radioGroupdelivery = view.findViewById(R.id.radioGroupdelivery);
        RadioGroup radioGrouppayment = view.findViewById(R.id.radioGrouppayment);
        RadioButton radioGroupdeliverydeliver = view.findViewById(R.id.radioGroupdeliverydeliver);
        RadioButton radioGroupdeliverypickup = view.findViewById(R.id.radioGroupdeliverypickup);
        RadioButton radioGrouppaymentcash = view.findViewById(R.id.radioGrouppaymentcash);
        RadioButton radioGrouppaymentgcash = view.findViewById(R.id.radioGrouppaymentgcash);
        radioGroupdeliverypickup.setChecked(true);
        radioGrouppaymentcash.setChecked(true);



        radioGroupdelivery.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.radioGroupdeliverydeliver) {
                    total3 = totalprice + 50;
                    total4 = total3;
                    total2.setText("+ 50 = " + String.valueOf(total3));
                } else if (checkedId == R.id.radioGroupdeliverypickup) {
                    total2.setText("");
                    total4 = totalprice;
                }
            }
        });

        btnCartProceed = view.findViewById(R.id.btnCartProceed);
        btnCartProceed.setOnClickListener(v->{
            dialog1 = new ProgressDialog(getContext());
            dialog1.setCancelable(false);
            dialog1.setMessage("Sending");
            dialog1.show();

            deliveryId = radioGroupdelivery.getCheckedRadioButtonId();
            if (deliveryId == R.id.radioGroupdeliverydeliver) {
                deliveryValue = radioGroupdeliverydeliver.getText().toString();
            } else if (deliveryId == R.id.radioGroupdeliverypickup) {
                deliveryValue = radioGroupdeliverypickup.getText().toString();
            } else {
                deliveryValue = "";
            }

            paymentId = radioGrouppayment.getCheckedRadioButtonId();
            if (paymentId == R.id.radioGrouppaymentcash) {
                paymentValue = radioGrouppaymentcash.getText().toString();
            } else if (paymentId == R.id.radioGrouppaymentgcash) {
                paymentValue = radioGrouppaymentgcash.getText().toString();
            } else {
                paymentValue = "";
            }


            String url2 = "https://northsignalvillage.com/api/mobile/createTransaction";
            StringRequest request1 = new StringRequest
                    (Request.Method.POST, url2, response -> {

                        dialog1.dismiss();

                    }, error -> {
                        error.printStackTrace();
                        dialog1.dismiss();
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
                    map.put("user_id", String.valueOf(userID));
                    map.put("total", String.valueOf(total4));
                    map.put("delivery", deliveryValue);
                    map.put("payment", paymentValue);
                    map.put("barangayidsidstring", barangayidsidstring);
                    map.put("certificatesidstring", certificatesidstring);

                    return map;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(getContext());
            queue.add(request1);




        });



        return view;
    }


    }
