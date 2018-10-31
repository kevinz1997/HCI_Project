package com.example.locnt.app_project;


import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    TextView txtDistance, txtNameBottom, txtPriceBottom, txtAddrBottom, txtPhoneBottom, txtDetailBottom, txtDirection;
    Button btnBottom;
    RatingBar ratingBottom;

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
//        btnBottom = view.findViewById(R.id.btnBookBottom);
        txtDetailBottom = view.findViewById(R.id.detail_bottom);
        txtDirection = view.findViewById(R.id.direction_bottom);
        ratingBottom = view.findViewById(R.id.rating_bottom);
        LayerDrawable stars = (LayerDrawable) ratingBottom.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(getContext(), R.color.red), PorterDuff.Mode.SRC_ATOP);

        txtDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        Bundle bundle = getArguments();
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
//            btnBottom.setEnabled(false);
        } else if (name.contains("Sân bóng đá cỏ nhân tạo Phương Nam")) {
            txtAddrBottom.setText("Đông Hưng Thuận, Quận 12, Hồ Chí Minh, Việt Nam");
            txtPhoneBottom.setText("08 2214 9048");
        } else {
            txtAddrBottom.setText("Unnamed Road, Trung Mỹ Tây, Quận 12, Hồ Chí Minh, Việt Nam");
            txtPhoneBottom.setText("08 3984 9476");
//            btnBottom.setEnabled(false);
        }
        txtPriceBottom.setText("200.000 VNĐ/h");
        txtDistance.setText(5 + " km");
        // Inflate the layout for this fragment
        return view;
    }

}
