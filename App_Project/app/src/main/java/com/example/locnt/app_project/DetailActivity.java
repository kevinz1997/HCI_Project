package com.example.locnt.app_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText mFeedback;
    Button mSendFeedback;
    ImageView imgDetail;
    TextView txtNameDetail,txtPhoneDetail,txtAddrDetail,txtIntroDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        createView();
        rate();
    }

    private void rate() {
        mRatingBar = findViewById(R.id.ratingBar);
        mRatingScale = findViewById(R.id.txtRating);
        mFeedback = findViewById(R.id.edt);
        mSendFeedback = findViewById(R.id.btnSubmit);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Ghét");
                        break;
                    case 2:
                        mRatingScale.setText("Không thích");
                        break;
                    case 3:
                        mRatingScale.setText("OK");
                        break;
                    case 4:
                        mRatingScale.setText("Thích");
                        break;
                    case 5:
                        mRatingScale.setText("Rất thích");
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });

        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFeedback.getText().toString().isEmpty()) {
                    mSendFeedback.setEnabled(false);
                } else {
                    SharedPreferences pre = getSharedPreferences("feedback", MODE_PRIVATE);
                    SharedPreferences.Editor edit = pre.edit();
                    edit.putString("datafeedback",mFeedback.getText().toString());
                    edit.putString("ratefeedback",mRatingScale.getText().toString());
                    edit.commit();
                    mFeedback.setText("");
                    mRatingBar.setRating(0);
                    Toast.makeText(DetailActivity.this, "Cảm ơn bạn đã đánh giá.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createView() {
        imgDetail = findViewById(R.id.imgDetail);
        txtNameDetail = findViewById(R.id.txtNameDetail);
        txtPhoneDetail = findViewById(R.id.txtPhoneDetail);
        txtAddrDetail = findViewById(R.id.txtAddrDetail);
        txtIntroDetail = findViewById(R.id.txtIntroDetail);
        Intent intent = this.getIntent();
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String addr = intent.getStringExtra("addr");
//        Toast.makeText(this, name + "\n" + phone + "\n" + addr, Toast.LENGTH_LONG).show();
        if(name.contains("Trung tâm thể thao A2")) {
            imgDetail.setBackgroundResource(R.drawable.a2);
            txtNameDetail.setText(name);
            txtPhoneDetail.setText(phone);
            txtAddrDetail.setText(addr);
            txtIntroDetail.setText("");
        } else if(name.contains("Sân bóng đá cỏ nhân tạo Đạt Đức")) {
            imgDetail.setBackgroundResource(R.drawable.datduc);
            txtNameDetail.setText(name);
            txtPhoneDetail.setText(phone);
            txtAddrDetail.setText(addr);
            txtIntroDetail.setText("");
        } else if(name.contains("Sân bóng đá cỏ nhân tạo Phương Nam")) {
            imgDetail.setBackgroundResource(R.drawable.phuongnam);
            txtNameDetail.setText(name);
            txtPhoneDetail.setText(phone);
            txtAddrDetail.setText(addr);
            txtIntroDetail.setText("BITI'S HUNTER X MIDNIGHT BLACK II - EXPERIENCE MIDNIGHT FREEDOM" +
                    "CHIẾN BINH BÓNG BÓNG ĐÊM TRỞ LẠI - GIVEAWAY 3 ĐÔI GIÀY ĐẦU TIÊN CHO 3 FAN MAY MẮN" +
                    "Sau sự kiện “cháy hàng” trong vòng chỉ 1 tuần ra mắt của thế hệ đầu, cơn sốt Midnight Black chính thức trở lại với thiết kế cá tính đầy khác biệt cùng đại sứ Thương hiệu Sơn Tùng M-TP, sẵn sàng cùng bạn trải nghiệm sự tự do phóng khoáng");
        } else if(name.contains("Sân Bóng Trần Hưng Đạo")) {
            imgDetail.setBackgroundResource(R.drawable.thd);
            txtNameDetail.setText(name);
            txtPhoneDetail.setText(phone);
            txtAddrDetail.setText(addr);
            txtIntroDetail.setText("");
        } else if(name.contains("Sân bóng đá mini đường Cây Trâm")) {
            imgDetail.setBackgroundResource(R.drawable.caytram);
            txtNameDetail.setText(name);
            txtPhoneDetail.setText(phone);
            txtAddrDetail.setText(addr);
            txtIntroDetail.setText("");
        }
    }
}
