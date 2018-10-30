package com.example.locnt.app_project;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    TextView txtDistance, txtNameBottom, txtPriceBottom, txtAddrBottom, txtPhoneBottom, txtDetailBottom;
    Button btnBottom;

    public BottomSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        txtDistance = view.findViewById(R.id.distance_in_bottom);
        txtNameBottom = view.findViewById(R.id.bottom_name);
        txtAddrBottom = view.findViewById(R.id.address_bottom);
        txtPriceBottom = view.findViewById(R.id.price_bottom);
        txtPhoneBottom = view.findViewById(R.id.phone_bottom);
        btnBottom = view.findViewById(R.id.btnBookBottom);
        txtDetailBottom = view.findViewById(R.id.detail_bottom);

        Bundle bundle = getArguments();
        int distance = (int) bundle.getDouble("distance");
        final String name = bundle.getString("name");
        txtNameBottom.setText(name);

        txtDetailBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

        if (name.contains("Sân Bóng Sài Gòn FC Quận 12")) {
            txtAddrBottom.setText("12/ Tổ 12 Kp1, Tân Hưng Thuận, Quận 12, Hồ Chí Minh");
            txtPhoneBottom.setText("096 269 0274");
        } else if (name.contains("Sân bóng đá cỏ nhân tạo Đạt Đức")) {
            txtAddrBottom.setText("Unnamed Road, Tân Hưng Thuận, Quận 12, Hồ Chí Minh, Việt Nam");
            txtPhoneBottom.setText("08 3589 5720");
            btnBottom.setEnabled(false);
        } else if (name.contains("Sân bóng đá cỏ nhân tạo Phương Nam")) {
            txtAddrBottom.setText("Đông Hưng Thuận, Quận 12, Hồ Chí Minh, Việt Nam");
            txtPhoneBottom.setText("08 2214 9048");
        } else {
            txtAddrBottom.setText("Unnamed Road, Trung Mỹ Tây, Quận 12, Hồ Chí Minh, Việt Nam");
            txtPhoneBottom.setText("08 3984 9476");
            btnBottom.setEnabled(false);
        }
        txtPriceBottom.setText("200.000 VNĐ/h");
        if (distance*0.001 >= 1) {
            txtDistance.setText(distance * 0.001 + " km");
        } else {
            txtDistance.setText(distance + " m");
        }
        // Inflate the layout for this fragment
        return view;
    }

}
