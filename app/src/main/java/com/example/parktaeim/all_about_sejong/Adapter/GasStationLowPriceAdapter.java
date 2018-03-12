package com.example.parktaeim.all_about_sejong.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.parktaeim.all_about_sejong.Model.GasStationItem;
import com.example.parktaeim.all_about_sejong.R;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 3. 11..
 */

public class GasStationLowPriceAdapter extends RecyclerView.Adapter<GasStationLowPriceAdapter.ViewHolder> {
    ArrayList<GasStationItem> gasStationItems = new ArrayList<>();

    public GasStationLowPriceAdapter(ArrayList<GasStationItem> gasStationItems) {
        this.gasStationItems = gasStationItems;
    }

    @Override
    public GasStationLowPriceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gasstation_cardview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GasStationLowPriceAdapter.ViewHolder holder, int position) {
        holder.rankTv.setText(String.valueOf(gasStationItems.get(position).getRank()));
        holder.priceTv.setText(String.valueOf(gasStationItems.get(position).getPrice())+"원");
        holder.nameTv.setText(gasStationItems.get(position).getName());
        holder.addressTv.setText(gasStationItems.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return gasStationItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView rankTv;
        private TextView nameTv;
        private TextView addressTv;
        private TextView priceTv;

        public ViewHolder(View itemView) {
            super(itemView);
            rankTv = (TextView) itemView.findViewById(R.id.gas_rankTv);
            nameTv = (TextView) itemView.findViewById(R.id.gas_nameTv);
            addressTv = (TextView) itemView.findViewById(R.id.gas_addressTv);
            priceTv = (TextView) itemView.findViewById(R.id.gas_priceTv);
        }
    }
}
