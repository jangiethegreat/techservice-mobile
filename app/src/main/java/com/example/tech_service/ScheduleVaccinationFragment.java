package com.example.tech_service;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech_service.Model.BrgyId;
import com.example.tech_service.Model.Scheduling;
import com.example.tech_service.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScheduleVaccinationFragment#} factory method to
 * create an instance of this fragment.
 */
public class ScheduleVaccinationFragment extends Fragment {

    private Button btnScheduleSubmit;
    private EditText edtxtFullName, edtxtPhoneNo, edtxtEmail,
            edtxtType, edtxtRDate, edtxtRTime,
            edtxtVAddressno, edtxtVAddressStreet;
    private Bitmap bitmap = null;
    private ProgressDialog dialog;
    private SharedPreferences preferences;
    private View view;
    Button datepicker,timepicker;
    String userID, username, useremail,usernumber;

    private Spinner spinner;
    private TextView selectedItemTextView;

    public ScheduleVaccinationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_schedule_vaccination, container, false);
        init();
        return view;
    }

    private void init() {
//        SharedPreferences userPref = getActivity().getApplicationContext().getSharedPreferences("user",getContext().MODE_PRIVATE);

        preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        Log.e("tanginamoJV", preferences.getAll().toString());
        userID = String.valueOf(preferences.getInt("id", 0));
        username = preferences.getString("name", "");
        useremail = preferences.getString("email", "");
        usernumber = preferences.getString("phoneNum", "");

        btnScheduleSubmit = view.findViewById(R.id.btnScheduleSubmit);
        edtxtFullName = view.findViewById(R.id.edtxtFullName);
        edtxtFullName .setText(username);
        edtxtFullName.setEnabled(false);

        edtxtPhoneNo = view.findViewById(R.id.edtxtPhoneNo);
        edtxtPhoneNo.setText(usernumber);
        edtxtPhoneNo.setEnabled(false);

        edtxtEmail = view.findViewById(R.id.edtxtEmail);
        edtxtEmail.setText(useremail);
        edtxtEmail.setEnabled(false);

        edtxtType = view.findViewById(R.id.edtxtType);
        edtxtType.setEnabled(false);
        edtxtRDate = view.findViewById(R.id.edtxtRDate);
        edtxtRDate.setEnabled(false);
        edtxtRTime = view.findViewById(R.id.edtxtRTime);
        edtxtRTime.setEnabled(false);
        edtxtVAddressno = view.findViewById(R.id.edtxtVAddressno);
        edtxtVAddressStreet = view.findViewById(R.id.edtxtVAddressStreet);
        timepicker = view.findViewById(R.id.timepicker);
        datepicker = view.findViewById(R.id.datepicker);
        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);

        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String date = String.format(Locale.getDefault(), "%02d-%02d-%04d", dayOfMonth, month + 1, year);
                                edtxtRDate.setText(date);
                            }
                        }, year, month, day);

                // Restrict past dates from being selectable
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                datePickerDialog.show();
            }
        });

        spinner = view.findViewById(R.id.spinner);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //selectedItemTextView.setText("Selected item: " + parent.getItemAtPosition(position));
                edtxtType.setText((CharSequence) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //selectedItemTextView.setText("No item selected");
            }
        });

        timepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hourOfDay, minute, second);
                                edtxtRTime.setText(String.format("%tT", new Time(hourOfDay, minute, second)));
                            }
                        }, hour, minute, true);
                timePickerDialog.show();
            }
        });

        btnScheduleSubmit.setOnClickListener(v -> {
            if (edtxtFullName.getText().toString().isEmpty()
                    ||edtxtPhoneNo.getText().toString().isEmpty()
                    ||edtxtEmail.getText().toString().isEmpty()
                    ||edtxtType.getText().toString().isEmpty()
                    ||edtxtRDate.getText().toString().isEmpty()
                    ||edtxtRTime.getText().toString().isEmpty()
                    ||edtxtVAddressno.getText().toString().isEmpty()
                    ||edtxtVAddressStreet.getText().toString().isEmpty()
            ){
                Toast.makeText(getContext(), "Required", Toast.LENGTH_SHORT).show();
                Log.e("tanginamoJV", "tanginamo JV kulang input mo");
            } else {
                submit();
            }
        });
    }





    private void submit() {
        dialog.setMessage("Success");
        dialog.show();

        StringRequest request = new StringRequest
                (Request.Method.POST, Constant.ADD_SCHEDULING, response -> {

//                    try {
//                        JSONObject object = new JSONObject(response);
//                        if (object.getBoolean("success")) {
//                            JSONObject scheduleObject = object.getJSONObject("scheduling");
//                            JSONObject userObject = scheduleObject.getJSONObject("user");
//
//                            User user = new User();
//                            user.setId(userObject.getInt("id"));
//                            user.setName(userObject.getString("name"));
//
//                            Scheduling post = new Scheduling();
//                            post.setUser(user);
//                            post.setId(scheduleObject.getInt("id"));
//                            post.setName(scheduleObject.getString("name"));
//                            post.setContact_num(scheduleObject.getString("contact_num"));
//                            post.setVaccine(scheduleObject.getString("vaccine"));
//                            post.setDate(scheduleObject.getString("date"));
//                            post.setTime(scheduleObject.getString("time"));
//                            post.setAddressNo(scheduleObject.getString("addressNo"));
//                            post.setStreet(scheduleObject.getString("street"));
//                            post.setEmail(scheduleObject.getString("email"));
////                    post.setDate(barangayidObject.getString("created_at"));
////                    HomeFragment.arrayList.add(0, post);
////                    HomeFragment.recyclerView.getAdapter().notifyItemInserted(0);
////                    HomeFragment.recyclerView.getAdapter().notifyDataSetChanged();
//                            Toast.makeText(getContext(), "Posted", Toast.LENGTH_SHORT).show();
//                            ((AuthActivity) getContext()).finish();
//
//
//                        }
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
                map.put("name", edtxtFullName.getText().toString().trim());
                map.put("contact_num", edtxtPhoneNo.getText().toString().trim());
                map.put("email", edtxtEmail.getText().toString().trim());
                map.put("vaccine", edtxtType.getText().toString().trim());
                map.put("date", edtxtRDate.getText().toString().trim());
                map.put("time", edtxtRTime.getText().toString().trim());
                map.put("addressNo", edtxtVAddressno.getText().toString().trim());
                map.put("street", edtxtVAddressStreet.getText().toString().trim());
//                map.put("photo", bitmapToString(bitmap));
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }
}