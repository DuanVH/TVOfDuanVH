package com.example.gem.tvofduanvh;


import android.graphics.Color;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.HeadersSupportFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowHeaderPresenter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gem on 6/26/18.
 */

public class LeftMenuFragment extends HeadersSupportFragment {

  private static final String KEY_MENU = "KEY_MENU";

  private ArrayObjectAdapter mRowsAdapter;
  private OnMenuItemClickListener mListener;

//  private OnMenuItem

  private List<MenuItem> mMenuItems;
  private List<MenuDTO> mMenuDTOs;
  private int mCurrentPosition;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments() != null && getArguments().containsKey(KEY_MENU)) {
      mMenuDTOs = getArguments().getParcelableArrayList(KEY_MENU);
    }

    setPresenterSelector(new PresenterSelector() {
      @Override
      public Presenter getPresenter(Object item) {
        return new LeftMenuPresenter(getActivity());
      }
    });
  }

  public void setListener(OnMenuItemClickListener mListener) {
    this.mListener = mListener;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    setupHeaderAdapter();

    setOnHeaderClickedListener(new OnHeaderClickedListener() {
      @Override
      public void onHeaderClicked(RowHeaderPresenter.ViewHolder viewHolder, Row row) {
        if (row != null) {
          int position = (int) row.getId();
          if (mCurrentPosition != position && position != -1) {
            mMenuItems.get(mCurrentPosition).mIsChosen = false;
            mRowsAdapter.notifyArrayItemRangeChanged(mCurrentPosition, 1);
            mCurrentPosition = position;
            mMenuItems.get(mCurrentPosition).mIsChosen = true;
            mRowsAdapter.notifyArrayItemRangeChanged(mCurrentPosition, 1);


          }
        }
      }
    });

    setOnHeaderViewSelectedListener(new OnHeaderViewSelectedListener() {
      @Override
      public void onHeaderSelected(RowHeaderPresenter.ViewHolder viewHolder, Row row) {
        //  ...???
      }
    });

    getVerticalGridView().setPadding(
        getActivity().getResources().getDimensionPixelSize(R.dimen.lb_action_1_line_height), 0, 0, 0);
    getVerticalGridView().setVerticalSpacing(0);
    customSetBackground();
  }

  private void setupHeaderAdapter() {
    mRowsAdapter = new ArrayObjectAdapter();
    mMenuItems = new ArrayList<>();

//    for (int i = 0; i < mMenuDtos.size(); i++) {  // ...???
//      MenuItem menuItem = new MenuItem(i, mMenuDtos.get(i).getName());
//      mMenuItems.add(menuItem);
//      mRowsAdapter.add(new ListRow(menuItem, new ArrayObjectAdapter()));
//    }

    for (int i = 0; i < 5; i++) {
      MenuItem menuItem = new MenuItem(i, "DuanVH");
      mMenuItems.add(menuItem);
      mRowsAdapter.add(new ListRow(menuItem, new ArrayObjectAdapter(new LeftMenuPresenter(getActivity()))));
    }

    setAdapter(mRowsAdapter);
  }

  private void customSetBackground() {
    try {
      Class clazz = HeadersSupportFragment.class;
      Method m = clazz.getDeclaredMethod("setBackgroundColor", Integer.TYPE);
      m.setAccessible(true);
      m.invoke(this, Color.TRANSPARENT);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public interface OnMenuItemClickListener{
    void onMenuItemClicked(long id);
  }
}
