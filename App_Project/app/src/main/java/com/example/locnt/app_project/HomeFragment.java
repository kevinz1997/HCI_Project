package com.example.locnt.app_project;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private View view;
    public HomeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        return view;
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<DataShop> shopList = new ArrayList<>();
        String s = "Tên Sân: ABCXYZ."+"\n"+"Địa điểm: ABCXYZ" + "\n" + "SĐT: 0987654321" + "\n" + "Thời Gian Hoạt Động: 8:00-22:00" + "\n" + "Giá: 200.000đ/h";
        shopList.add(new DataShop(s,R.drawable.a));
        shopList.add(new DataShop(s,R.drawable.a));
        shopList.add(new DataShop(s,R.drawable.a));
        shopList.add(new DataShop(s,R.drawable.a));
        shopList.add(new DataShop(s,R.drawable.a));
        shopList.add(new DataShop(s,R.drawable.a));
        ShopAdapter shopAdapter = new ShopAdapter(shopList,getContext());
        recyclerView.setAdapter(shopAdapter);

    }

}
