package com.example.parktaeim.all_about_sejong.Model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by parktaeim on 2018. 3. 7..
 */

public class MainItem {
    private int img;
    private String title;
    private boolean isActiveMenu;

    public MainItem(int img, String title, boolean isActiveMenu) {
        this.img = img;
        this.title = title;
        this.isActiveMenu = isActiveMenu;
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

    public boolean isActiveMenu() {
        return isActiveMenu;
    }

    public void setActiveMenu(boolean activeMenu) {
        isActiveMenu = activeMenu;
    }
}
