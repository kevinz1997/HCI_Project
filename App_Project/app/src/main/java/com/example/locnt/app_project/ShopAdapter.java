package com.example.locnt.app_project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.viewHolder> {

    ArrayList<DataShop> arrayList;
    Context context;

    public ShopAdapter(ArrayList<DataShop> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.item,parent,false);
        return new viewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        holder.tvName.setText(arrayList.get(position).getName());
        holder.imgView.setImageResource(arrayList.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void removeItem(int position){
        arrayList.remove(position);
        notifyItemRemoved(position);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imgView;
        public viewHolder(final View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_Text);
            imgView = (ImageView) itemView.findViewById(R.id.img_View);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(getAdapterPosition());
                    Toast.makeText(itemView.getContext(),"remove"+tvName.getText(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
