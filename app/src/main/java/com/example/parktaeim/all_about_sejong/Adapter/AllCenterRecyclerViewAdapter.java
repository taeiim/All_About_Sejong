package com.example.parktaeim.all_about_sejong.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parktaeim.all_about_sejong.Model.DayCareCenterItem;
import com.example.parktaeim.all_about_sejong.R;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 2. 2..
 */

public class AllCenterRecyclerViewAdapter extends RecyclerView.Adapter<AllCenterRecyclerViewAdapter.ViewHolder>{

    private ArrayList<DayCareCenterItem> centerItems = new ArrayList<>();
    private ArrayList<DayCareCenterItem> itemsCopy = new ArrayList<>();

    public AllCenterRecyclerViewAdapter(ArrayList<DayCareCenterItem> centerItems) {
        this.centerItems = centerItems;
        itemsCopy.addAll(centerItems);
    }

    @Override
    public AllCenterRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_center_cardview,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AllCenterRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.centerNameTv.setText(centerItems.get(position).getName());
        holder.centerAddressTv.setText(centerItems.get(position).getAddress());
        if(centerItems.get(position).getCar()){
            holder.centerBusImageView.setImageResource(R.drawable.icon_school_bus);
        }else{
            holder.centerBusImageView.setImageResource(R.drawable.icon_nobus);
        }
    }

    @Override
    public int getItemCount() {
        return centerItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView centerNameTv;
        TextView centerAddressTv;
        ImageView centerBusImageView;
        ImageView centerTypeImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            centerNameTv = (TextView) itemView.findViewById(R.id.center_nameTv);
            centerAddressTv = (TextView) itemView.findViewById(R.id.center_addressTv);
            centerBusImageView = (ImageView) itemView.findViewById(R.id.center_bus_imageView);
            centerTypeImageView = (ImageView) itemView.findViewById(R.id.center_type_imageView);

        }
    }

    public void filter(String text) {
        centerItems.clear();
        if(text.isEmpty()){
            centerItems.addAll(itemsCopy);

        } else{
            text = text.toLowerCase();
            for(DayCareCenterItem item: itemsCopy){
                if(item.name.toLowerCase().contains(text) || item.address.toLowerCase().contains(text)){
                    centerItems.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
