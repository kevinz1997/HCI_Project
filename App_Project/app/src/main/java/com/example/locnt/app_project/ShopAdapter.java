package com.example.locnt.app_project;

import android.content.Context;
import android.content.Intent;
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
    LayoutInflater inflater;
    private class VIEW_TYPES{
        public static final int Footer = 1;
        public static final int Normal = 2;
    }

    boolean isFooter = false;

    public ShopAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public ShopAdapter(ArrayList<DataShop> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View itemview = layoutInflater.inflate(R.layout.item,parent,false);
//        return new viewHolder(itemview);
        View itemview;
        switch (viewType) {
            case VIEW_TYPES.Normal:
                isFooter = false;
                itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
                break;
            case VIEW_TYPES.Footer:
                isFooter = true;
                itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer,parent,false);
                break;
            default:
                isFooter = false;
                itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        }
        return new viewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        if (!isFooter) {
            holder.tvName.setText(arrayList.get(position).getName());
            holder.imgView.setImageResource(arrayList.get(position).getImg());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
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
                    Intent intent = new Intent(context,DetailActivity.class);
                    intent.putExtra("item",arrayList.get(getAdapterPosition()).getName());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (arrayList.get(position).isFooter()) {
            return VIEW_TYPES.Footer;
        }
        return super.getItemViewType(position);
    }
}
