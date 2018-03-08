package com.example.parktaeim.all_about_sejong.Adapter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.parktaeim.all_about_sejong.Model.ClubItem;
import com.example.parktaeim.all_about_sejong.R;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by parktaeim on 2018. 3. 6..
 */

public class ClubRecyclerViewAdapter extends RecyclerView.Adapter<ClubRecyclerViewAdapter.ViewHolder> {
    ArrayList<ClubItem> clubItemArrayList = new ArrayList<>();
    ArrayList<ClubItem> flipList = new ArrayList<>();

    public ClubRecyclerViewAdapter(ArrayList<ClubItem> clubItemArrayList,ArrayList<ClubItem> flipList) {
        this.clubItemArrayList = clubItemArrayList;
        this.flipList = flipList;

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
        holder.club_content.setText(String.valueOf(clubItemArrayList.get(position).getContent()));

        if (holder.flipView.getCurrentFlipState() == EasyFlipView.FlipState.FRONT_SIDE && flipList.get(
                position).isFlipped) {
            holder.flipView.setFlipDuration(0);
            holder.flipView.flipTheView();
        } else if (holder.flipView.getCurrentFlipState() == EasyFlipView.FlipState.BACK_SIDE
                && !flipList.get(position).isFlipped) {
            holder.flipView.setFlipDuration(0);
            holder.flipView.flipTheView();
        }
        holder.flipView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flipList.get(position).isFlipped) {
                    flipList.get(position).isFlipped = false;
                } else {
                    flipList.get(position).isFlipped = true;
                }
                holder.flipView.setFlipDuration(700);
                holder.flipView.flipTheView();
            }
        });

    }

    @Override
    public int getItemCount() {
        return clubItemArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView club_name;
        private TextView club_content;
        private CardView cardView;
        private EasyFlipView flipView;

        public ViewHolder(View itemView) {
            super(itemView);
            club_name = (TextView) itemView.findViewById(R.id.club_cardview_name);
            club_content = (TextView) itemView.findViewById(R.id.club_cardview_content);
            cardView = (CardView) itemView.findViewById(R.id.club_cardview);
            flipView = (EasyFlipView) itemView.findViewById(R.id.flipView);
        }
    }
}
