//package com.example.tech_service.Adapters;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.tech_service.Model.Announcement;
//import com.example.tech_service.R;
//
//import java.util.ArrayList;
//
//public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.AnnouncementHolder>{
//
//    private Context context;
//    private ArrayList<Announcement> list;
//    private ArrayList<Announcement> listAll;
//    private SharedPreferences preferences;
//
//    public AnnouncementAdapter(Context context, ArrayList<Announcement> list) {
//        this.context = context;
//        this.list = list;
//        this.listAll = new ArrayList<>(list);
//        preferences = context.getApplicationContext().getSharedPreferences("user",Context.MODE_PRIVATE);
//    }
//
//    @NonNull
//    @Override
//    public AnnouncementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_about_barangay,parent,false);
//        return new AnnouncementHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull AnnouncementAdapter.AnnouncementHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//}
