package com.example.gem.tvofduanvh;

import android.support.v17.leanback.widget.HeaderItem;

public class IconMenuItem extends HeaderItem {

    private int image;

    public IconMenuItem(long id, String name, int image) {
        super(id, name);
        this.image = image;
    }

    public IconMenuItem(String name, int image) {
        super(name);
        this.image = image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }
}
