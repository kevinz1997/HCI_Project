package com.example.locnt.app_project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class HistoryAdapter extends BaseAdapter {
    private List<History> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public HistoryAdapter(List<History> listData, Context context) {
        this.listData = listData;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_list, null);
            holder = new ViewHolder();
            holder.txtName = view.findViewById(R.id.txtNameList);
            holder.txtAddr = view.findViewById(R.id.txtAddrList);
            holder.txtDate = view.findViewById(R.id.txtDateList);
            holder.txtTime = view.findViewById(R.id.txtTimeList);
            holder.txtPrice = view.findViewById(R.id.txtPriceList);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        History history = this.listData.get(i);
        holder.txtName.setText("Tên sân: " + history.getName());
        holder.txtAddr.setText("Địa chỉ: " + history.getAddr());
        holder.txtDate.setText("Giờ đặt sân: " + history.getDate());
        holder.txtTime.setText("Ngày đặt sân: " + history.getTime());
        holder.txtPrice.setText("Giá: " + history.getPrice());

        return view;
    }

    static class ViewHolder {
        TextView txtName,txtAddr,txtDate,txtTime,txtPrice;
    }
}
