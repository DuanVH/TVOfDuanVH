package com.example.gem.tvofduanvh;

import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.RowPresenter;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.BaseGridView;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.RowHeaderPresenter;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by gem on 6/28/18.
 */

public class CustomListRowPresenter extends ListRowPresenter {

  private int mHorizontalSpace;
  private int mVerticalSpace;
  private int mFirstRowHeight;
  private float mWindowAlignmentOffsetPercent;
  private HorizontalGridView mHorizontalGridView;
  private boolean mEnableHorizontalAlignment;
  private Context mContext;

  public CustomListRowPresenter(Context context) {
    mContext = context;
  }

  public CustomListRowPresenter(Context context, int focusZoomFactor) {
    super(focusZoomFactor);
    mContext = context;
  }

  public CustomListRowPresenter(Context context, int focusZoomFactor, boolean useFocusDimmer) {
    super(focusZoomFactor, useFocusDimmer);
    mContext = context;
  }

  public int getHorizontalSpace() {
    return mHorizontalSpace;
  }

  public void setHorizontalSpace(int horizontalSpace) {
    mHorizontalSpace = horizontalSpace;
  }

  public int getVerticalSpace() {
    return mVerticalSpace;
  }

  public void setVerticalSpace(int verticalSpace) {
    mVerticalSpace = verticalSpace;
  }

  public int getFirstRowHeight() {
    return mFirstRowHeight;
  }

  public void setWindowAlignmentOffsetPercent(float windowAlignmentOffsetPercent) {
    mWindowAlignmentOffsetPercent = windowAlignmentOffsetPercent;
  }

  public void setEnableHorizontalAlignment(boolean enableHorizontalAlignment) {
    mEnableHorizontalAlignment = enableHorizontalAlignment;
  }

  public HorizontalGridView getHorizontalGridView() {
    return mHorizontalGridView;
  }

  @Override
  protected RowPresenter.ViewHolder createRowViewHolder(ViewGroup parent) {
    ViewHolder viewHolder = (ViewHolder) super.createRowViewHolder(parent);
    mHorizontalGridView = viewHolder.getGridView();

    if (mEnableHorizontalAlignment) {
      mHorizontalGridView.setWindowAlignment(BaseGridView.WINDOW_ALIGN_LOW_EDGE); // WINDOW_ALIGN_HIGH_EDGE
      mHorizontalGridView.setFocusScrollStrategy(BaseGridView.FOCUS_SCROLL_ALIGNED); //FOCUS_SCROLL_ALIGNED
      mHorizontalGridView.setWindowAlignmentOffsetPercent(mWindowAlignmentOffsetPercent);
    }

    mHorizontalGridView.setFadingLeftEdge(false);

    if (mHorizontalSpace > 0)
      mHorizontalGridView.setHorizontalSpacing(mHorizontalSpace);
    if (mVerticalSpace > 0)
      mHorizontalGridView.setVerticalSpacing(mVerticalSpace);

    mHorizontalGridView.setPadding(0, 0, 0, 0);
    return viewHolder;
  }


  @Override
  protected void onBindRowViewHolder(final RowPresenter.ViewHolder holder, Object item) {
    super.onBindRowViewHolder(holder, item);

    final RowHeaderPresenter.ViewHolder headerHolder = holder.getHeaderViewHolder();
    holder.view.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
      @Override
      public void onGlobalFocusChanged(View view, View view1) {
        mFirstRowHeight = holder.view.getHeight();
        holder.view.getViewTreeObserver().removeOnGlobalFocusChangeListener(this);
      }
    });
  }
}
