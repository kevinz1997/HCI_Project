package com.example.locnt.app_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
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

    RatingBar smallRatingBar, smallRatingBar1, smallRatingBar2;
    ImageView imgDetail;
    TextView txtNameDetail, txtPhoneDetail, txtAddrDetail, txtIntroDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        createView();
        smallRatingBar = findViewById(R.id.smallRatingBar);
        LayerDrawable star = (LayerDrawable) smallRatingBar.getProgressDrawable();
        star.getDrawable(2).setColorFilter(ContextCompat.getColor(this, R.color.lightGrey), PorterDuff.Mode.SRC_ATOP);
        smallRatingBar1 = findViewById(R.id.smallRatingBar1);
        LayerDrawable star1 = (LayerDrawable) smallRatingBar1.getProgressDrawable();
        star1.getDrawable(2).setColorFilter(ContextCompat.getColor(this, R.color.lightGrey), PorterDuff.Mode.SRC_ATOP);
        smallRatingBar2 = findViewById(R.id.smallRatingBar2);
        LayerDrawable star2 = (LayerDrawable) smallRatingBar2.getProgressDrawable();
        star2.getDrawable(2).setColorFilter(ContextCompat.getColor(this, R.color.lightGrey), PorterDuff.Mode.SRC_ATOP);

        TextView back = findViewById(R.id.txtDetailBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }



    private void createView() {
        imgDetail = findViewById(R.id.imgDetail);
        txtNameDetail = findViewById(R.id.txtNameDetail);
        txtPhoneDetail = findViewById(R.id.txtPhoneDetail);
        txtAddrDetail = findViewById(R.id.txtAddrDetail);
        txtIntroDetail = findViewById(R.id.txtIntroDetail);
        String introduce = "Trung tâm gồm 2 sân đa năng, 1 nhà thi đấu đa năng, 1 hồ bơi cao cấp, và 9 sân bóng đá mini (gồm 8 sân bóng 5 người và 1 sân bóng 7 người)." + "\n" + "\n" + "Sân bóng đá mini nhân tạo được trang bị mái che. Ngoài ra hệ thống đèn chiếu sáng đều đạt tiêu chuẩn hiện đại. Đảm bảo cho các cầu thủ, vận động viên thoải mái nhất và thí đấu tốt nhất, trong điều kiện tốt nhất.";
        Intent intent = this.getIntent();
//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String name = intent.getStringExtra("name");
//        Toast.makeText(this, name + "\n" + phone + "\n" + addr, Toast.LENGTH_LONG).show();
        if (name.contains("Trung tâm thể thao A2")) {
            imgDetail.setBackgroundResource(R.drawable.a2);
            txtNameDetail.setText(name);
            txtPhoneDetail.setText("0120 304 0506");
            txtAddrDetail.setText("Phan Thúc Duyện, Phường 4, Tân Bình, Hồ Chí Minh");
        } else if (name.contains("Sân bóng đá cỏ nhân tạo Đạt Đức")) {
            imgDetail.setBackgroundResource(R.drawable.datduc);
            txtNameDetail.setText(name);
            txtPhoneDetail.setText("08 3589 5720");
            txtAddrDetail.setText("5A Nguyễn Văn Lượng, P16, Gò Vấp, TP HCM");
        } else if (name.contains("Sân bóng đá cỏ nhân tạo Phương Nam")) {
            imgDetail.setBackgroundResource(R.drawable.phuongnam);
            txtNameDetail.setText(name);
            txtPhoneDetail.setText("08 2214 9048");
            txtAddrDetail.setText("44/5 Phạm Văn Chiêu, P.9, Gò Vấp, TP HCM");
        } else if (name.contains("Sân Bóng Trần Hưng Đạo")) {
            imgDetail.setBackgroundResource(R.drawable.thd);
            txtNameDetail.setText(name);
            txtPhoneDetail.setText("08 3984 9476");
            txtAddrDetail.setText("88/995E Lê Đức Thọ, Phường 6, quận Gò Vấp, TP Hồ Chí Minh");
        } else if (name.contains("Sân bóng đá mini đường Cây Trâm")) {
            imgDetail.setBackgroundResource(R.drawable.caytram);
            txtNameDetail.setText(name);
            txtPhoneDetail.setText("08 6167 4774");
            txtAddrDetail.setText("17/1D Cây Trâm, phường 9, quận Gò Vấp, Hồ Chí Minh");
        } else {
            imgDetail.setBackgroundResource(R.drawable.caytram);
            txtNameDetail.setText(name);
            txtPhoneDetail.setText("08 6167 4774");
            txtAddrDetail.setText("17/1D Cây Trâm, phường 9, quận Gò Vấp, Hồ Chí Minh");
        }

        txtIntroDetail.setText(introduce);
    }

    public void clickToDetailBook(View view) {
        Intent intent = new Intent(this, BookActivity.class);
        intent.putExtra("name", txtNameDetail.getText().toString());
        startActivity(intent);
    }
}
