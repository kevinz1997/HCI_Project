package com.example.locnt.app_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    List<History> list = new ArrayList<History>();
    Date currentTime = Calendar.getInstance().getTime();

    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    String formattedDate = df.format(currentTime);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        TextView back = findViewById(R.id.txtHisBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        data();
    }

    private void data() {
        List<History> listHistory = getListData();
        final ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new HistoryAdapter(listHistory,this));
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = listView.getItemAtPosition(i);
                History history = (History) o;
                String date = ((History) o).getDate();
                try {
                    Date date1 = df.parse(date);
                    Date date2 = df.parse(formattedDate);
                    if (date1.after(date2)) {
                        removeItemFromList(i,listView);
                    } else {
                        Toast.makeText(HistoryActivity.this, "past date: " + "current" + date1 +"<"+ date2, Toast.LENGTH_SHORT).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

//                Toast.makeText(HistoryActivity.this, "" + formattedDate, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    protected void removeItemFromList(int position,ListView listView) {
        final Object o = listView.getItemAtPosition(position);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("HỦY ĐẶT SÂN");
        alert.setMessage("Bạn chắc chắn hủy đặt sân?");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    list.remove(o);
            }
        });
        alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();

    }

    private  List<History> getListData() {
        History dd = new History("Sân bóng đá cỏ nhân tạo Đạt Đức","5A Nguyễn Văn Lượng, P16, Gò Vấp, TP HCM","8:00 - 10:00", 400000,"12/10/2018");
        History ct = new History("Sân bóng đá mini đường Cây Trâm","17/1D Cây Trâm, phường 9, quận Gò Vấp, Hồ Chí Minh","8:00 - 10:00", 400000,"12/10/2018");
        History a2 = new History("Trung tâm thể thao A2","Phan Thúc Duyện, Phường 4, Tân Bình, Hồ Chí Minh","6:00 - 8:00", 400000,"10/10/2018");

        list.add(dd);
        list.add(ct);
        list.add(a2);

        return list;
    }
}
