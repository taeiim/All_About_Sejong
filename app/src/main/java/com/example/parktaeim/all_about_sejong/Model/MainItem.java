package com.example.parktaeim.all_about_sejong.Model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by parktaeim on 2018. 3. 7..
 */

public class MainItem {
    public int img;
    public String title;

    public MainItem(int img, String title) {
        this.img = img;
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
