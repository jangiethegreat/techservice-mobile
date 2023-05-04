package com.example.tech_service.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.tech_service.AuthActivity;
import com.example.tech_service.Constant;
import com.example.tech_service.EditProfileActivity;
import com.example.tech_service.MyRequestFragment;
import com.example.tech_service.R;
import com.example.tech_service.UserActivity;
import com.google.android.material.appbar.MaterialToolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {

    private View view;
    private MaterialToolbar toolbar;
    private CircleImageView imgProfile;
    private TextView txtName,txtPostsCount ,txtrequest;
    private Button btnEditAccount;
    private RecyclerView recyclerView;
//    private ArrayList<announcement> arrayList;
    private SharedPreferences preferences;
//    private AccountPostAdapter adapter;
    private String imgUrl = "";

    String fullname,gender,age,birthdate,contactnumber,street,addressnumber,zone,valid_id;
    TextView txtviewProfileName,txtviewProfileGender,txtviewProfileAge,txtviewProfileBirthdate,txtviewProfileContactNum,txtviewProfileAddNo,txtviewProfileAddStreet,txtviewProfileAddZone;
    ImageView imgUserInfo;
    Button edit_profile;
    public ProfileFragment(){}
//
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile,container,false);

        init();



        return view;

    }



    private void init() {
        edit_profile = view.findViewById(R.id.edit_profile);
        edit_profile.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EditProfileActivity.class);
            startActivity(intent);
        });
        preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        Log.e("tanginamoJV", preferences.getAll().toString());
        fullname = preferences.getString("name", "");
        gender = preferences.getString("gender", "");
        age = preferences.getString("age", "");
        birthdate = preferences.getString("birthdate", "");
        contactnumber = preferences.getString("phoneNum", "");
        street = preferences.getString("street", "");
        addressnumber = preferences.getString("addressNo", "");
        zone = preferences.getString("addressZone", "");
        valid_id = preferences.getString("valid_id", "");

        txtviewProfileName = view.findViewById(R.id.txtviewProfileName);
        txtviewProfileGender = view.findViewById(R.id.txtviewProfileGender);
        txtviewProfileAge = view.findViewById(R.id.txtviewProfileAge);
        txtviewProfileBirthdate = view.findViewById(R.id.txtviewProfileBirthdate);
        txtviewProfileContactNum = view.findViewById(R.id.txtviewProfileContactNum);
        txtviewProfileAddNo = view.findViewById(R.id.txtviewProfileAddNo);
        txtviewProfileAddStreet = view.findViewById(R.id.txtviewProfileAddStreet);
        txtviewProfileAddZone = view.findViewById(R.id.txtviewProfileAddZone);
        txtrequest = view.findViewById(R.id.txtviewProfileRequest);
        imgUserInfo = view.findViewById(R.id.imgUserInfo);

        txtviewProfileName.setText("Name: "+fullname);
        txtviewProfileGender.setText("Gender: "+gender);
        txtviewProfileAge.setText("Age: "+age);
        txtviewProfileBirthdate.setText("Birthdate: "+birthdate);
        txtviewProfileContactNum.setText("Contact No.: "+contactnumber);
        txtviewProfileAddNo.setText("Street: "+street);
        txtviewProfileAddStreet.setText("Address No.: "+addressnumber);
        txtviewProfileAddZone.setText("Zone: "+zone);

        Glide.with(getContext())
                .load("https://northsignalvillage.com/storage/users/"+ valid_id)
                .into(imgUserInfo);
//        android:id="@+id/txtviewProfileRequest"

        txtrequest.setOnClickListener(v->{
            //change fragments
//            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameAuthContainer,new MyRequestFragment()).commit();
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.profileContainer, new MyRequestFragment());
            fragmentTransaction.commit();
        });




    }

@Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.navigation_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuLogout: {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Do you want to logout?");
                builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout(){
        StringRequest request = new StringRequest(Request.Method.GET,Constant.LOGOUT,res->{

            try {
                JSONObject object = new JSONObject(res);
                if (object.getBoolean("success")){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.apply();
                    startActivity(new Intent(((UserActivity)getContext()), AuthActivity.class));
                    ((UserActivity)getContext()).finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        },error -> {
            error.printStackTrace();
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = preferences.getString("token","");
                HashMap<String,String> map = new HashMap<>();
                map.put("Authorization","Bearer "+token);
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//
//        if (!hidden){
//            getData();
//        }
//
//        super.onHiddenChanged(hidden);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        getData();
//    }
}
