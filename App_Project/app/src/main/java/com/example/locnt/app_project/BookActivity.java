package com.example.locnt.app_project;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class BookActivity extends AppCompatActivity {
    Spinner startHourSpinner, endHourSpinner, numberPitch;
    TextView txtDetail, txtDate, txtPitchName;
    Button btnBook;

    int start, end, number;
    String dateBook, startDate, endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        startHourSpinner = findViewById(R.id.startHour);
        endHourSpinner = findViewById(R.id.endHour);
        numberPitch = findViewById(R.id.numberPitch);
        //txtDetail = findViewById(R.id.txtDetail);
        txtDate = findViewById(R.id.txtDate);
        txtPitchName = findViewById(R.id.txtPitchName);

//        txtDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(BookActivity.this, DetailActivity.class);
//                startActivity(intent);
////                finish();
//            }
//        });
        txtDate.setText("Chọn ngày");
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDate();
            }
        });
        btnBook = findViewById(R.id.btnBook);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (start >= end) {
                    Toast.makeText(BookActivity.this, "Start > end", Toast.LENGTH_SHORT).show();
                } else if (dateBook == null) {
                    Toast.makeText(BookActivity.this, "Vui lòng chọn ngày.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = BookActivity.this.getIntent();
                    String name = intent.getStringExtra("name");
                    show(name, dateBook, startDate, endDate, number);
                    txtPitchName.setText(name);
                }
            }
        });

        String[] data = {"6:00", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30",
                "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
                "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30",
                "21:00", "21:30", "22:00"};

        String[] data1 = {"7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30",
                "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
                "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30",
                "21:00", "21:30", "22:00", "22:30", "23:00"};

        String[] data2 = {"1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data2);
        startHourSpinner.setAdapter(adapter);
        endHourSpinner.setAdapter(adapter1);
        numberPitch.setAdapter(adapter2);
        adapter.notifyDataSetChanged();
        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
        change();
    }

    public void change() {

        startHourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                start = Integer.parseInt(adapterView.getItemAtPosition(position).toString().substring(0, 1));
//                Toast.makeText(BookActivity.this, "" + start, Toast.LENGTH_SHORT).show();
                startDate = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        endHourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                end = Integer.parseInt(adapterView.getItemAtPosition(position).toString().substring(0, 1));
//                Toast.makeText(BookActivity.this, "" + end, Toast.LENGTH_SHORT).show();
                endDate = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        numberPitch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                number = Integer.parseInt(adapterView.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void showPriceDetail(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        String s = "Khung giờ:" + "\n" + "06:00 - 16:00: 100.000/h" + "\n" + "16:00 - 17:00: 180.000/h" + "\n" + "17:00 - 24:00: 220.000/h" + "\n" + "Không cần đặt cọc." + "\n" + "Bóng dùng miễn phí." + "\n" + "Nước uống miễn phí.";

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

    private void show(String name, String date, String start, String end, int number) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Thông tin đặt sân");
        alert.setMessage("Tên sân: " + name + "\n" + "Ngày: " + date + "\n" + "Giờ bắt đầu: " + start + "\n"
                + "Giờ kết thúc: " + end + "\n" + "Sân số: " + number);
        alert.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(BookActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();
    }

    private void chooseDate() {
        final Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                txtDate.setText(sdf.format(calendar.getTime()));
                dateBook = sdf.format(calendar.getTime());
            }
        }, year, month, date);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
}
