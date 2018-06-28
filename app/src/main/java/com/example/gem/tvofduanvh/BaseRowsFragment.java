package com.example.gem.tvofduanvh;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v17.leanback.app.RowsSupportFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.BaseGridView;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.view.View;
import android.widget.Toast;


/**
 * Created by gem on 6/28/18.
 */

public class BaseRowsFragment extends RowsSupportFragment {
  private ArrayObjectAdapter mRowsAdapter;
  private CustomListRowPresenter mListRowPresenter;

  protected static final int ZOOM_FACTOR = 1;
  protected static final int ITEM_HORIZONTAL_SPACE = 0;
  protected static final int ITEM_VERTICAL_SPACE = 0;
  protected static final int HORIZONTAL_PADDING = 0;
  protected static final int VERTICAL_OFFSET_PERCENT = 0;
  protected static final int HORIZONTAL_OFFSET_PERCENT = 15;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mListRowPresenter = new CustomListRowPresenter(getContext(), zoomFactor());
    mListRowPresenter.setHorizontalSpace(rowItemHorizontalSpace());
    mListRowPresenter.setVerticalSpace(rowItemVerticalSpace());
    mListRowPresenter.setShadowEnabled(enableShadow());
    mListRowPresenter.setSelectEffectEnabled(dimUnselectedRows());
    mListRowPresenter.enableChildRoundedCorners(false);
    mListRowPresenter.setEnableHorizontalAlignment(enableHorizontalAlignment());
    mListRowPresenter.setWindowAlignmentOffsetPercent(horizontalWindowAlignmentOffsetPercent());

    mRowsAdapter = new ArrayObjectAdapter(mListRowPresenter);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getVerticalGridView().setPadding(0, 0, 0, 0);

    if (enableVerticalAlignment()) {
      getVerticalGridView().setWindowAlignment(BaseGridView.WINDOW_ALIGN_BOTH_EDGE);
      getVerticalGridView().setItemAlignmentOffsetPercent(verticalItemAlignmentOffsetPercent());
    }
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    setAdapter(mRowsAdapter);
  }

  public void showMessage(String brMessage) {
    Toast.makeText(getActivity(), brMessage, Toast.LENGTH_SHORT).show();
  }

  public void showMessage(@StringRes int brMessageResId) {
    Toast.makeText(getActivity(), brMessageResId, Toast.LENGTH_SHORT).show();
  }



  protected ArrayObjectAdapter getRowsAdapter() {
    return mRowsAdapter;
  }

  protected int zoomFactor() {
    return ZOOM_FACTOR;
  }

  protected boolean enableShadow() {
    return true;
  }

  protected boolean dimUnselectedRows() {
    return false;
  }

  protected ListRowPresenter getCustomListRowPresenter() {
    return mListRowPresenter;
  }

  protected int rowItemHorizontalSpace() {
    return ITEM_HORIZONTAL_SPACE;
  }

  protected int rowItemVerticalSpace() {
    return ITEM_VERTICAL_SPACE;
  }

  protected int horizontalPadding() {
    return HORIZONTAL_PADDING;
  }

  protected boolean enableVerticalAlignment() {
    return true;
  }

  protected boolean enableHorizontalAlignment() {
    return false;
  }

  protected float horizontalWindowAlignmentOffsetPercent() {
    return HORIZONTAL_OFFSET_PERCENT;
  }

  protected float verticalItemAlignmentOffsetPercent() {
    return VERTICAL_OFFSET_PERCENT;
  }
}
