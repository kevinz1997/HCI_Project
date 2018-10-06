package com.example.locnt.app_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    public void clickToSaveForRegister(View view) {
        Toast.makeText(this,"Đăng Kí Thành Công", Toast.LENGTH_LONG).show();
    }

    public void clickToBack(View view) {
        onBackPressed();
        finish();
    }
}
