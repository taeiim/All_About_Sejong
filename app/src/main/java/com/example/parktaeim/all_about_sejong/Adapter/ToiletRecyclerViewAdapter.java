package com.example.parktaeim.all_about_sejong.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.parktaeim.all_about_sejong.Model.DayCareCenterItem;
import com.example.parktaeim.all_about_sejong.Model.ToiletItem;
import com.example.parktaeim.all_about_sejong.R;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 3. 9..
 */

public class ToiletRecyclerViewAdapter extends RecyclerView.Adapter<ToiletRecyclerViewAdapter.ViewHolder> {
    private ArrayList<ToiletItem> toiletItems = new ArrayList<>();
    private ArrayList<ToiletItem> copyItems = new ArrayList<>();

    public ToiletRecyclerViewAdapter(ArrayList<ToiletItem> toiletItems) {
        this.toiletItems = toiletItems;
        copyItems.addAll(toiletItems);
    }

    @Override
    public ToiletRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_toilet,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ToiletRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.nameTv.setText(toiletItems.get(position).getToilet_name());
        holder.addressTv.setText(toiletItems.get(position).getToilet_address());
    }

    @Override
    public int getItemCount() {
        return toiletItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private TextView distanceTv;
        private TextView addressTv;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.toilet_nameTv);
            distanceTv = (TextView) itemView.findViewById(R.id.toilet_distanceTv);
            addressTv = (TextView) itemView.findViewById(R.id.toilet_addressTv);
        }
    }

    public void filter(String text) {
        toiletItems.clear();
        if(text.isEmpty()){
            toiletItems.addAll(copyItems);

        } else{
            text = text.toLowerCase();
            for(ToiletItem item: copyItems){
                if(item.getToilet_name().toLowerCase().contains(text) || item.getToilet_address().toLowerCase().contains(text)){
                    toiletItems.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
