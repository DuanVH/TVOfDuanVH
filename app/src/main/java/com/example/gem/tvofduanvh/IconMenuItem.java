package com.example.gem.tvofduanvh;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v17.leanback.widget.HeaderItem;

public class IconMenuItem extends HeaderItem {

    private int image;
    private Uri uri;

    public IconMenuItem(long id, String name, int image) {
        super(id, name);
        this.image = image;
    }

    public IconMenuItem(long id, String name, int image, Uri uri) {
        super(id, name);
        this.image = image;
        this.uri = uri;
    }

    public IconMenuItem(String name, int image) {
        super(name);
        this.image = image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public int getImage() {
        return image;
    }

    public Uri getUri() {
        return uri;
    }
}
