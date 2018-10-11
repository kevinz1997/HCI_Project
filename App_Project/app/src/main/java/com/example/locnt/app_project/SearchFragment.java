package com.example.locnt.app_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchFragment extends Fragment{
    private View view;
    private TextView txtResult;
    public SearchFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        final SearchView simpleSearchView= view.findViewById(R.id.simpleSearchView);
        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String name = simpleSearchView.getQuery().toString();
                txtResult = view.findViewById(R.id.txtResult);
                initView(name);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return view;
    }

    private void initView(String name) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_search);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<DataShop> shopList = new ArrayList<>();
        String s = "Tên Sân: ABCXYZ."+"\n"+"Địa điểm: ABCXYZ" + "\n" + "SĐT: 0987654321" + "\n" + "Thời Gian Hoạt Động: 8:00-22:00" + "\n" + "Giá: 200.000đ/h";
        shopList.add(new DataShop("Trung tâm thể thao A2","Phan Thúc Duyện, Phường 4, Tân Bình, Hồ Chí Minh","0120 304 0506","8:00-24:00",R.drawable.a2,300000));
        shopList.add(new DataShop("Sân bóng đá cỏ nhân tạo Đạt Đức","5A Nguyễn Văn Lượng, P16, Gò Vấp, TP HCM","08 3589 5720","8:00-24:00",R.drawable.datduc,400000));
        shopList.add(new DataShop("Sân bóng đá cỏ nhân tạo Phương Nam","44/5 Phạm Văn Chiêu, P.9, Gò Vấp, TP HCM","08 2214 9048","8:00-24:00",R.drawable.phuongnam,200000));
        shopList.add(new DataShop("Sân Bóng Trần Hưng Đạo","88/995E Lê Đức Thọ, Phường 6, quận Gò Vấp, TP Hồ Chí Minh","08 3984 9476","6:00-22:00",R.drawable.thd,300000));
        shopList.add(new DataShop("Sân bóng đá mini đường Cây Trâm","17/1D Cây Trâm, phường 9, quận Gò Vấp, Hồ Chí Minh","08 6167 4774","8:00-24:00",R.drawable.caytram,300000));
        ArrayList<DataShop> newList = new ArrayList<>();
        for (int i = 0; i < shopList.size(); i++) {
            String names = shopList.get(i).getName();
            if (names.toLowerCase().contains(name.toLowerCase())) {
                newList.add(shopList.get(i));
            } else {
                txtResult.setVisibility(View.VISIBLE);
            }
        }
        ShopAdapter shopAdapter = new ShopAdapter(newList,getContext());
        recyclerView.setAdapter(shopAdapter);
    }
}
