package com.example.parktaeim.all_about_sejong.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parktaeim.all_about_sejong.Model.MainItem;
import com.example.parktaeim.all_about_sejong.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by parktaeim on 2018. 3. 7..
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    List<MainItem> mainItemList = new ArrayList<>();
    Context context;

    public MainRecyclerAdapter(List<MainItem> mainItemList, Context context) {
        this.mainItemList = mainItemList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_cardview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainRecyclerAdapter.ViewHolder holder, int position) {
        holder.cardview_imgView.setImageResource(mainItemList.get(position).getImg());
        holder.cardview_titleTv.setText(mainItemList.get(position).getTitle());
        if(mainItemList.get(position).isActiveMenu() == false){
            holder.cardview_titleTv.setTextColor(Color.GRAY);
        }

        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Animation anim = AnimationUtils.loadAnimation(context,R.anim.scale_main_img);
                        holder.cardview_imgView.startAnimation(anim);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mainItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cardview_imgView;
        private TextView cardview_titleTv;

        public ViewHolder(View itemView) {
            super(itemView);
            cardview_imgView = (ImageView) itemView.findViewById(R.id.main_cardviewImg);
            cardview_titleTv = (TextView) itemView.findViewById(R.id.main_cardviewTv);
        }
    }
}
