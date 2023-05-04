package com.example.tech_service.Fragment;//package com.example.tech_service.Fragment;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.tech_service.AuthActivity;
//import com.example.tech_service.Constant;
////import com.example.tech_service.EditUserInfoFragment;
//import com.example.tech_service.R;
//import com.example.tech_service.UserActivity;
//import com.google.android.material.appbar.MaterialToolbar;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.squareup.picasso.Picasso;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tech_service.Constant;
import com.example.tech_service.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AboutBarangayFragment extends Fragment {
    private View view;
    private FragmentManager fragmentManager;
    private FloatingActionButton fab;
    private BottomNavigationView navigationView;
    private static final int GALLERY_ADD_POST = 2;
    ;


    public AboutBarangayFragment() {
        // Required empty public constructor
    }

    //
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_about_barangay, container, false);
        init();
        return view;
    }

    private void init() {
    }

//        preferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);}}
//        toolbar = view.findViewById(R.id.toolbarAccount);
//        ((HomeActivity) getContext()).setSupportActionBar(toolbar);
//        setHasOptionsMenu(true);
//        imgProfile = view.findViewById(R.id.imgAccountProfile);
//        txtName = view.findViewById(R.id.txtAccountName);
//        txtPostsCount = view.findViewById(R.id.txtAccountPostCount);
//        recyclerView = view.findViewById(R.id.recyclerAccount);
//        btnEditAccount = view.findViewById(R.id.btnEditAccount);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
//
//
//        btnEditAccount.setOnClickListener(v -> {
//            Intent i = new Intent(((UserActivity) getContext()), EditUserInfoFragment.class);
//            i.putExtra("imgUrl", imgUrl);
//            startActivity(i);
//        });

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuLogout) {
//            // Send a logout request to the server
//            String url = "http://yourserver.com/logout";
//            StringRequest request = new StringRequest(Request.Method.GET, Constant.LOGOUT,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            // Clear user session data
//                            SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
//                            preferences.edit().clear().apply();
//                            // Navigate to login activity
                            Intent intent = new Intent(getActivity(), SignInFragment.class);
                            startActivity(intent);
//                            getActivity().finish();
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    // Handle error
//                }
//            });
//            RequestQueue queue = Volley.newRequestQueue(getContext());
//            queue.add(request);
        }
//
        return super.onOptionsItemSelected(item);
    }
}
//    private void getData() {
//        arrayList = new ArrayList<>();
//        StringRequest request = new StringRequest(Request.Method.GET, Constant.MY_POST, res -> {
//
//            try {
//                JSONObject object = new JSONObject(res);
//                if (object.getBoolean("success")) {
//                    JSONArray posts = object.getJSONArray("posts");
//                    for (int i = 0; i < posts.length(); i++) {
//                        JSONObject p = posts.getJSONObject(i);
//
//                        Post post = new Post();
//                        post.setPhoto(Constant.URL + "storage/posts/" + p.getString("photo"));
//                        arrayList.add(post);
//
//                    }
//                    JSONObject user = object.getJSONObject("user");
//                    txtName.setText(user.getString("name") + " " + user.getString("lastname"));
//                    txtPostsCount.setText(arrayList.size() + "");
//                    Picasso.get().load(Constant.URL + "storage/profiles/" + user.getString("photo")).into(imgProfile);
//                    adapter = new AccountPostAdapter(getContext(), arrayList);
//                    recyclerView.setAdapter(adapter);
//                    imgUrl = Constant.URL + "storage/profiles/" + user.getString("photo");
//                }
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }, error -> {
//            error.printStackTrace();
//        }) {
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                String token = preferences.getString("token", "");
//                HashMap<String, String> map = new HashMap<>();
//                map.put("Authorization", "Bearer " + token);
//                return map;
//            }
//        };
//
//        RequestQueue queue = Volley.newRequestQueue(getContext());
//        queue.add(request);
//    }

//
////    @Override
////    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
////        inflater.inflate(R.menu.navigation_menu, menu);
////        super.onCreateOptionsMenu(menu, inflater);
////    }
////
////    @Override
////    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
////
////        switch (item.getItemId()) {
////            case R.id.menuLogout: {
////                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
////                builder.setMessage("Do you want to logout?");
////                builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////                        logout();
////                    }
////                });
////                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////
////                    }
////                });
////                builder.show();
////            }
////        }
////
////        return super.onOptionsItemSelected(item);
////    }
////
////    private void logout() {
////        StringRequest request = new StringRequest(Request.Method.GET, Constant.LOGOUT, res -> {
////
////            try {
////                JSONObject object = new JSONObject(res);
////                if (object.getBoolean("success")) {
////                    SharedPreferences.Editor editor = preferences.edit();
////                    editor.clear();
////                    editor.apply();
////                    startActivity(new Intent(((UserActivity) getContext()), AuthActivity.class));
////                    ((UserActivity) getContext()).finish();
////                }
////            } catch (JSONException e) {
////                e.printStackTrace();
////            }
////
////
////        }, error -> {
////            error.printStackTrace();
////        }) {
////            @Override
////            public Map<String, String> getHeaders() throws AuthFailureError {
////                String token = preferences.getString("token", "");
////                HashMap<String, String> map = new HashMap<>();
////                map.put("Authorization", "Bearer " + token);
////                return map;
////            }
////        };
////
////        RequestQueue queue = Volley.newRequestQueue(getContext());
////        queue.add(request);
////    }
//
////    @Override
////    public void onHiddenChanged(boolean hidden) {
////
////        if (!hidden) {
////            getData();
////        }
////
////        super.onHiddenChanged(hidden);
////    }
////
////    @Override
////    public void onResume() {
////        super.onResume();
////        getData();
////    }
