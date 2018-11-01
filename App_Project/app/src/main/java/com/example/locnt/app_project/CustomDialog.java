package com.example.locnt.app_project;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomDialog extends Dialog {

    public Dialog dialog;
    public Button yes,no;
    public Context mContext;
    Spinner startHourDialog, endHourDialog, numberPitchDialog, bitCapkeoDialog;
    TextView txtDateDialog;

    public CustomDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);

        startHourDialog = findViewById(R.id.startHourDialog);
        endHourDialog = findViewById(R.id.endHourDialog);
        numberPitchDialog = findViewById(R.id.numberPitchDialog);
        bitCapkeoDialog = findViewById(R.id.bitCapkeoDialog);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = Calendar.getInstance().getTime();
        String formattedDate = sdf.format(date);
        txtDateDialog = findViewById(R.id.txtDateDialog);
        txtDateDialog.setText(formattedDate);
        txtDateDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDate();
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
        String[] data2 = {"5", "7", "11"};
        String[] data3 = {"không", "có"};
//        if(!pitchName.contains("A2")) {
//            data2  = new String[]{"1", "2", "3", "4", "5"};
//        } else {
//            data2  = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
//        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.item_spinner, data){
            @Override
            public boolean isEnabled(int position) {
                if(position == 1 || position == 2 || position == 3)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position==1 || position == 2 || position == 3) {
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), R.layout.item_spinner, data1){
            @Override
            public boolean isEnabled(int position) {
                if(position == 0 || position == 1 )
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position==0 || position == 1) {
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), R.layout.item_spinner, data2){
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                tv.setTextColor(Color.BLACK);

                return view;
            }
        };
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getContext(), R.layout.item_spinner, data3){
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                tv.setTextColor(Color.BLACK);

                return view;
            }
        };
        startHourDialog.setAdapter(adapter);
        popup(startHourDialog);
        endHourDialog.setAdapter(adapter1);
        popup(endHourDialog);
        numberPitchDialog.setAdapter(adapter2);
        bitCapkeoDialog.setAdapter(adapter3);
        adapter.notifyDataSetChanged();
        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
        adapter3.notifyDataSetChanged();

        yes = findViewById(R.id.btnUpdateDialog);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
                dismiss();
            }
        });

        no = findViewById(R.id.btnCancelDialog);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void popup(Spinner s) {
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(s);
            popupWindow.setHeight(500);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void chooseDate() {
        final Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                txtDateDialog.setText(sdf.format(calendar.getTime()));
//                dateBook = sdf.format(calendar.getTime());
            }
        }, year, month, date);
        datePickerDialog.setCancelable(false);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void showDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("XÁC NHẬN");
        alert.setMessage("Cập nhật thành công!");
        alert.setCancelable(false);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        alert.show();
    }
}
