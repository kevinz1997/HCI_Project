package com.example.locnt.app_project;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

public class CustomDialog extends Dialog implements DialogInterface.OnClickListener {

    public Dialog dialog;
    public Button yes, no;
    public Context mContext;

    public CustomDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_dialog);
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
}
