package com.example.locnt.app_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.viewHolder>  {
    private ArrayList<History> listData;
    private Context context;

    public BookingAdapter(Context context) {
        this.context = context;
    }

    public BookingAdapter(ArrayList<History> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }
    @NonNull
    @Override
    public BookingAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_list, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder viewHolder, int position) {
        String s = "Tên sân: " + listData.get(position).getName() + "\n" + "Địa chỉ: " + listData.get(position).getAddr()
                + "\n" + "Ngày đặt sân: " + listData.get(position).getDate() + "\n" + "Giờ đặt sân: " + listData.get(position).getTime()
                + "\n" + "Giá: " + listData.get(position).getPrice();

        viewHolder.tvName.setText(s);
        viewHolder.imgView.setImageResource(listData.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvClear;
        ImageView imgView;

        public viewHolder(final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.txtNameHistory);
            imgView = itemView.findViewById(R.id.img_history);
            tvClear = itemView.findViewById(R.id.txtClear);
            tvClear.setVisibility(View.VISIBLE);
            tvClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeItemFromList(getAdapterPosition());
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("position",listData.get(getAdapterPosition()).getName());
                    editor.commit();
                }
            });

//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    removeItemFromList(getAdapterPosition());
//                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
//                    SharedPreferences.Editor editor = pref.edit();
//                    editor.putString("position",listData.get(getAdapterPosition()).getName());
//                    editor.commit();
//                    return false;
//                }
//            });
        }
    }

    protected void removeItemFromList(final int position) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);

        alert.setTitle("HỦY ĐẶT SÂN");
        alert.setMessage("Bạn chắc chắn hủy đặt sân?");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                removeAt(position);
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

    public void removeAt(int position) {
        listData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listData.size());
    }
}
