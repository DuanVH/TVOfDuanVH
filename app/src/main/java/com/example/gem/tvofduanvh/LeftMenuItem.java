package com.example.gem.tvofduanvh;

import android.support.v17.leanback.widget.HeaderItem;

/**
 * Created by gem on 6/26/18.
 */

public class LeftMenuItem extends HeaderItem {

  public static final boolean IS_CHOSEN = false;
  public boolean mIsChosen = IS_CHOSEN;

  public LeftMenuItem(long id, String name, boolean isChosen) {
    super(id, name);
    mIsChosen = isChosen;
  }

  public LeftMenuItem(long id, String name) {
    this(id, name, IS_CHOSEN);
  }

  public LeftMenuItem(String name) {
    super(name);
  }

  public boolean getIsChosen() {
    return mIsChosen;
  }

  public void setIsChosen(boolean isChosen) {
    this.mIsChosen = isChosen;
  }

}
