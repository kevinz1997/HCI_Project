package com.example.locnt.app_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class BookingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BookingAdapter mAdapter;
    private ArrayList<History> listHistory;
    TextView tvUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        TextView back = findViewById(R.id.txtBookingBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        tvUpdate = findViewById(R.id.txtUpdate);
        tvUpdate.setVisibility(View.VISIBLE);
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog cd = new CustomDialog(BookingActivity.this);
                cd.show();
            }
        });

        recyclerView = findViewById(R.id.booking_view);
        listHistory = new ArrayList<>();
        listHistory.add(new History("Sân bóng đá cỏ nhân tạo Đạt Đức","5A Nguyễn Văn Lượng, P16, Gò Vấp, TP HCM","15:30 - 18:00","11/11/2018",500000,R.drawable.datduc));
        listHistory.add(new History("Trung tâm thể thao A2","Phan Thúc Duyện, Phường 4, Tân Bình, Hồ Chí Minh","8:00 - 10:00","9/11/2018",400000,R.drawable.a2));
        listHistory.add(new History("Sân bóng đá cỏ nhân tạo Phương Nam","44/5 Phạm Văn Chiêu, P.9, Gò Vấp, TP HCM","20:00 - 22:00","1/10/2018",400000,R.drawable.phuongnam));

        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(this);
        String name = shared.getString("position","DEFAULT");
        for (int i = 0; i < listHistory.size(); i++) {
            String full = listHistory.get(i).getName();
            if(full.contains(name)) {
                listHistory.remove(i);
            }
//                shared.edit().clear().commit();
        }
        mAdapter = new BookingAdapter(listHistory,BookingActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }
}
