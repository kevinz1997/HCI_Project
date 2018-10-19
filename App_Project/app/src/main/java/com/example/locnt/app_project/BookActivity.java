package com.example.locnt.app_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class BookActivity extends AppCompatActivity {
    Spinner startHourSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        startHourSpinner = findViewById(R.id.startHour);
        String[] data= {"6:00","7:00","8:00"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,data);
        startHourSpinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        change();
    }

    public void change() {
        startHourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(BookActivity.this, "Start Hour: " + adapterView.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void showPriceDetail(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        String s = "Khung giờ:" + "\n" + "06:00 - 16:00: 100.000/h"+ "\n" + "16:00 - 17:00: 180.000/h"+ "\n" + "17:00 - 24:00: 220.000/h"+ "\n" + "Không cần đặt cọc."+ "\n" + "Bóng dùng miễn phí."+ "\n" + "Nước uống miễn phí.";

        alert.setTitle("Bảng giá");
        alert.setMessage(s);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();
    }
}
