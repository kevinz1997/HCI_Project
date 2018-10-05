package com.example.locnt.app_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        createView();
    }

    private void createView() {
        Intent intent = this.getIntent();
        int id = intent.getIntExtra("item",0);
        Toast.makeText(this, "item = " + id, Toast.LENGTH_SHORT).show();
    }
}
