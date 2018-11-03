package com.example.locnt.app_project;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class CustomBillDialog extends Dialog {
    String name, date, start, end;
    boolean bet;
    int type, total;
    Context mContext;

    TextView txtBillName, txtBillDate, txtBillStart, txtBillEnd, txtBillType, txtBillTotal, btnOK, btnNO;
    CheckBox checkBoxBet;

    public CustomBillDialog(Context context, String name, String date, String start, String end, boolean bet, int type, int total) {
        super(context);
        this.mContext = context;
        this.name = name;
        this.date = date;
        this.start = start;
        this.end = end;
        this.bet = bet;
        this.type = type;
        this.total = total;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_bill_dialog);
        txtBillName = findViewById(R.id.txtBillName);
        txtBillDate = findViewById(R.id.txtBillDate);
        txtBillStart = findViewById(R.id.txtBillStart);
        txtBillEnd = findViewById(R.id.txtBillEnd);
        txtBillType = findViewById(R.id.txtBillType);
        checkBoxBet = findViewById(R.id.checkboxBet);
        txtBillTotal =  findViewById(R.id.txtBillTotal);

        txtBillName.setText(name);
        txtBillDate.setText(date);
        txtBillStart.setText(start);
        txtBillEnd.setText(end);
        txtBillType.setText(type+"");
        checkBoxBet.setChecked(bet);
        txtBillTotal.setText(total/1000 + ".000 VNĐ");
        btnOK = findViewById(R.id.btnOK);

        btnNO = findViewById(R.id.btnNO);
        btnNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


}
