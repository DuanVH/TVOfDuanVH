package com.example.gem.tvofduanvh;

import android.support.v17.leanback.widget.Presenter;
import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by gem on 6/28/18.
 */

public abstract class AbstractPresenter extends Presenter {

  private Context mContext;
  private boolean mIsAlignFirstItem;

  public AbstractPresenter(Context context, boolean isAlignFirstItem) {
    mContext = context;
    mIsAlignFirstItem = isAlignFirstItem;
  }

  protected Context getContext() {
    return mContext;
  }


  protected void setViewPivot(final View view, final int position) {
    if (!mIsAlignFirstItem) return;
    if (view.getWidth() == 0) {
      view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
        @Override
        public boolean onPreDraw() {
          view.getViewTreeObserver().removeOnPreDrawListener(this);
          if (position == 0 && view.getPivotX() != 0) {
            view.setPivotX(0);
            view.setPivotY(view.getHeight() / 2);
          } else if (position != 0 && view.getPivotX() == 0) {
            view.setPivotX(view.getWidth() / 2);
            view.setPivotY(view.getHeight() / 2);
          }
          return false;
        }
      });
    } else {
      if (position == 0 && view.getPivotX() != 0) {
        view.setPivotX(0);
        view.setPivotY(view.getHeight() / 2);
      } else if (position != 0 && view.getPivotX() == 0) {
        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight() / 2);
      }
    }
  }
}
