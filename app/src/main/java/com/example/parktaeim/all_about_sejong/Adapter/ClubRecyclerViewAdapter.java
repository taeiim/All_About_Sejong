package com.example.parktaeim.all_about_sejong.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.parktaeim.all_about_sejong.Model.ClubItem;
import com.example.parktaeim.all_about_sejong.R;

import java.util.ArrayList;

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

    int selectPos = 0;

    @Override
    public void onBindViewHolder(ClubRecyclerViewAdapter.ViewHolder holder, int position) {
        String clubName = clubItemArrayList.get(position).getName();
        String clubContent = clubItemArrayList.get(position).getContent();
        String clubMember = clubItemArrayList.get(position).getMemberCnt();

        String[] clubArr = {clubName,clubContent,clubMember};
        TextView[] clubTv = {holder.club_nameTv, holder.club_contentTv, holder.club_memberCntTv};

        for(int i=0; i<clubArr.length;i++){
            if(clubArr[i].equals("null")){
                clubTv[i].setVisibility(View.INVISIBLE);
            }else {
                clubTv[i].setText(clubArr[i]);
            }
        }

        setUpDetailView(holder,position);
    }

    @Override
    public int getItemCount() {
        return clubItemArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView club_nameTv;
        private TextView club_contentTv;
        private TextView club_memberCntTv;
        private LinearLayout detailInfoLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            club_nameTv = (TextView) itemView.findViewById(R.id.club_cardview_nameTv);
            club_contentTv = (TextView) itemView.findViewById(R.id.club_cardview_contentTv);
            club_memberCntTv = (TextView) itemView.findViewById(R.id.club_cardview_memberCntTv);
            detailInfoLayout = (LinearLayout) itemView.findViewById(R.id.club_detailContentLayout);
        }
    }

    private void setUpDetailView(ClubRecyclerViewAdapter.ViewHolder holder,int position){
        holder.itemView.setOnClickListener(v->{
            selectPos = holder.getAdapterPosition();
            System.out.println("SelectPos == "+selectPos);
            System.out.println("Position == "+position);

            if(position == selectPos){
                if(holder.detailInfoLayout.getVisibility() == View.VISIBLE){
                    holder.detailInfoLayout.setVisibility(View.GONE);
                }else {
                    holder.detailInfoLayout.setVisibility(View.VISIBLE);
                }
            }else {
                Log.d("Position not"," SelectPos");
            }
//            if(holder.detailInfoLayout.getVisibility() == View.VISIBLE){
//                v.setVisibility(View.GONE);
//            }else {
//                v.setVisibility(View.VISIBLE);
//            }

        });


    }
}
