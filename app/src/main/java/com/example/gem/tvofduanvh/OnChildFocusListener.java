package com.example.gem.tvofduanvh;

import android.graphics.Rect;
import android.view.View;

/**
 * Created by gem on 6/29/18.
 */

public interface OnChildFocusListener {

  boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect);

  void onRequestChildFocus(View child, View focused);
}
