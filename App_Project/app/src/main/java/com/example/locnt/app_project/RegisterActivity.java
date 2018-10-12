package com.example.locnt.app_project;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



public class RegisterActivity extends AppCompatActivity {
    private  View registerView;
    private  View progressView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         registerView = findViewById(R.id.register_form);
         progressView = findViewById(R.id.register_progess);
    }


    public void clickToSaveForRegister(View view) {
        new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                showProgress(true);
            }

            @Override
            public void onFinish() {
                showProgress(false);
            }
        }.start();
        Toast.makeText(this,"Đăng Kí Thành Công", Toast.LENGTH_LONG).show();
    }

    public void clickToBack(View view) {
        onBackPressed();
        finish();
    }
    private void showProgress(boolean param){
        progressView.setVisibility(param ? View.VISIBLE : View.GONE);
        registerView.setVisibility(param ? View.GONE : View.VISIBLE);
    }
}
