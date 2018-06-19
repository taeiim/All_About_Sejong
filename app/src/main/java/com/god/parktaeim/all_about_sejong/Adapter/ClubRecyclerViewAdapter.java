package com.god.parktaeim.all_about_sejong.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.god.parktaeim.all_about_sejong.Model.ClubItem;
import com.god.parktaeim.all_about_sejong.R;

import java.util.ArrayList;

/**
 * Created by parktaeim on 2018. 3. 6..
 */

public class ClubRecyclerViewAdapter extends RecyclerView.Adapter<ClubRecyclerViewAdapter.ViewHolder> {
    ArrayList<ClubItem> clubItemArrayList = new ArrayList<>();
    private Context context;

    public ClubRecyclerViewAdapter(ArrayList<ClubItem> clubItemArrayList, Context context) {
        this.clubItemArrayList = clubItemArrayList;
        this.context = context;
    }

    @Override
    public ClubRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club_cardview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClubRecyclerViewAdapter.ViewHolder holder, int position) {
        String clubName = clubItemArrayList.get(position).getName();
        String clubContent = clubItemArrayList.get(position).getContent();
        String clubMember = clubItemArrayList.get(position).getMemberCnt();
        String clubBusinessName = clubItemArrayList.get(position).getBusinessName();
        String clubType = clubItemArrayList.get(position).getClubType();
        String clubRegularMeeting = clubItemArrayList.get(position).getRegularMeeting();
        String clubMembershipFee = clubItemArrayList.get(position).getMembershipFee();
        String clubLeader = clubItemArrayList.get(position).getLeader();
        String clubTellNum = clubItemArrayList.get(position).getTellNum();
        String clubCafeURL = clubItemArrayList.get(position).getCafeUrl();

        String[] clubArr = {clubName, clubContent, clubMember, clubBusinessName, clubType, clubRegularMeeting, clubMembershipFee, clubLeader, clubTellNum, clubCafeURL};
        TextView[] clubTv = {holder.club_nameTv, holder.club_contentTv, holder.club_memberCntTv, holder.club_businessNameTv, holder.club_clubTypeTv, holder.club_regularMeetingTv, holder.club_membershipFeeTv, holder.club_leaderTv, holder.club_tellNumTv, holder.club_cafeURLTv};
        LinearLayout[] clubSeperateLayout = {null, null, null, holder.businessNameLayout, holder.clubTypeLayout, holder.regularMeetingLayout, holder.membershipFeeLayout, holder.leaderLayout, holder.tellNumLayout, holder.cafeURLLayout};

        for (int i = 0; i < clubArr.length; i++) {
            if (clubArr[i].equals("null") || clubArr[i] == null || clubArr[i].length() == 0) {
                clubTv[i].setText("");
                if (clubSeperateLayout[i] != null) clubSeperateLayout[i].setVisibility(View.GONE);
            } else {
                clubTv[i].setText(clubArr[i]);
                if (clubTv[i] == holder.club_tellNumTv)
                    clubTv[i].setText(Html.fromHtml("<u>" + clubArr[i] + "</u>"));

            }
        }

        setUpDetailView(holder);

        holder.club_tellNumTv.setOnClickListener(v -> {
            context.startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + clubTellNum)));
        });
    }

    @Override
    public int getItemCount() {
        return clubItemArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView club_nameTv;
        private TextView club_contentTv;
        private TextView club_memberCntTv;
        private TextView club_businessNameTv;
        private TextView club_clubTypeTv;
        private TextView club_regularMeetingTv;
        private TextView club_membershipFeeTv;
        private TextView club_leaderTv;
        private TextView club_tellNumTv;
        private TextView club_cafeURLTv;

        private RelativeLayout mainInfoLayout;
        private LinearLayout detailInfoLayout;
        private LinearLayout businessNameLayout;
        private LinearLayout clubTypeLayout;
        private LinearLayout regularMeetingLayout;
        private LinearLayout membershipFeeLayout;
        private LinearLayout leaderLayout;
        private LinearLayout tellNumLayout;
        private LinearLayout cafeURLLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            club_nameTv = (TextView) itemView.findViewById(R.id.club_cardview_nameTv);
            club_contentTv = (TextView) itemView.findViewById(R.id.club_cardview_contentTv);
            club_memberCntTv = (TextView) itemView.findViewById(R.id.club_cardview_memberCntTv);
            club_businessNameTv = (TextView) itemView.findViewById(R.id.businessNameTv);
            club_clubTypeTv = (TextView) itemView.findViewById(R.id.clubTypeTv);
            club_regularMeetingTv = (TextView) itemView.findViewById(R.id.regularMeetingTv);
            club_membershipFeeTv = (TextView) itemView.findViewById(R.id.membershipFeeTv);
            club_leaderTv = (TextView) itemView.findViewById(R.id.leaderTv);
            club_tellNumTv = (TextView) itemView.findViewById(R.id.tellNumTv);
            club_cafeURLTv = (TextView) itemView.findViewById(R.id.cafeURLTv);

            mainInfoLayout = (RelativeLayout) itemView.findViewById(R.id.clubNameLayout);
            detailInfoLayout = (LinearLayout) itemView.findViewById(R.id.clubDetail_contentLayout);
            businessNameLayout = (LinearLayout) itemView.findViewById(R.id.businessNameLayout);
            clubTypeLayout = (LinearLayout) itemView.findViewById(R.id.clubTypeLayout);
            regularMeetingLayout = (LinearLayout) itemView.findViewById(R.id.regularMeetingLayout);
            membershipFeeLayout = (LinearLayout) itemView.findViewById(R.id.membershipFeeLayout);
            leaderLayout = (LinearLayout) itemView.findViewById(R.id.leaderLayout);
            tellNumLayout = (LinearLayout) itemView.findViewById(R.id.tellNumLayout);
            cafeURLLayout = (LinearLayout) itemView.findViewById(R.id.cafeURLLayout);

        }
    }

    private void setUpDetailView(ClubRecyclerViewAdapter.ViewHolder holder) {
        holder.mainInfoLayout.setOnClickListener(v -> {
            if (holder.detailInfoLayout.getVisibility() == View.VISIBLE) {
                holder.detailInfoLayout.setVisibility(View.GONE);
            } else {
                holder.detailInfoLayout.setVisibility(View.VISIBLE);
            }

        });
    }
}
