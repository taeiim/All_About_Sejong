package com.example.parktaeim.all_about_sejong.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.parktaeim.all_about_sejong.Model.ClubItem;
import com.example.parktaeim.all_about_sejong.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by parktaeim on 2018. 3. 6..
 */

public class ClubRecyclerViewAdapter extends RecyclerView.Adapter<ClubRecyclerViewAdapter.ViewHolder> {
    ArrayList<ClubItem> clubItemArrayList = new ArrayList<>();

    public ClubRecyclerViewAdapter(ArrayList<ClubItem> clubItemArrayList) {
        this.clubItemArrayList = clubItemArrayList;
    }

    @Override
    public ClubRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club_cardview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClubRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.club_name.setText(clubItemArrayList.get(position).getName());
        holder.club_peopleNum.setText(String.valueOf(clubItemArrayList.get(position).peopleNum));
    }

    @Override
    public int getItemCount() {
        return clubItemArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView club_name;
        private TextView club_peopleNum;

        public ViewHolder(View itemView) {
            super(itemView);
            club_name = (TextView) itemView.findViewById(R.id.club_cardview_name);
            club_peopleNum = (TextView) itemView.findViewById(R.id.club_cardview_peopleNum);

        }
    }
}
