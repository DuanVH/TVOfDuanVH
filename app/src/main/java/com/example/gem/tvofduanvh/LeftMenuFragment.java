package com.example.gem.tvofduanvh;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.HeadersSupportFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowHeaderPresenter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gem on 6/26/18.
 */

public class LeftMenuFragment extends HeadersSupportFragment {

  private static final String KEY_MENU = "KEY_MENU";
  private static final String TAG = LeftMenuFragment.class.getSimpleName();

  private ArrayObjectAdapter mRowsAdapter;
  private OnMenuItemClickListener mListener;

  private List<LeftMenuItem> mLeftMenuItems;
  private List<LeftMenuDTO> mLeftMenuDTOS;
  private int mCurrentPosition;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments() != null && getArguments().containsKey(KEY_MENU)) {
      mLeftMenuDTOS = getArguments().getParcelableArrayList(KEY_MENU);
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

        if (mListener != null) {
          mListener.onLeftMenuItemClicked((int) row.getId());
          Log.e(TAG, "left menu clicked");
        }
      }
    });

    setOnHeaderViewSelectedListener(new OnHeaderViewSelectedListener() {
      @Override
      public void onHeaderSelected(RowHeaderPresenter.ViewHolder viewHolder, Row row) {

        if (mListener != null) {
          mListener.onLeftMenuItemClicked((int) row.getId());
          Log.e(TAG, "left menu selected");
        }

//        if (row != null) {
//          int position = (int) row.getId();
//          if (mCurrentPosition != position && position != -1) {
//            mLeftMenuItems.get(mCurrentPosition).mIsChosen = false;
//            mRowsAdapter.notifyArrayItemRangeChanged(mCurrentPosition, 1);
//            mCurrentPosition = position;
//            mLeftMenuItems.get(mCurrentPosition).mIsChosen = true;
//            mRowsAdapter.notifyArrayItemRangeChanged(mCurrentPosition, 1);
//
//            if (mListener != null) {
//              mListener.onLeftMenuItemClicked(mCurrentPosition);
//              Log.e(TAG, "left menu selected");
//            }
//          }
//        }
      }
    });

    getVerticalGridView().setPadding(
        0, getActivity().getResources().getDimensionPixelSize(R.dimen.lb_action_1_line_height), 0, 0);
    getVerticalGridView().setVerticalSpacing(0);
    customSetBackground(Color.BLACK);
  }

  public View getSelectedView() {
    RecyclerView.ViewHolder vh = getVerticalGridView().findViewHolderForAdapterPosition(getSelectedPosition());
    if (vh != null)
      return vh.itemView;
    return null;
  }

  private void setupHeaderAdapter() {
    mRowsAdapter = new ArrayObjectAdapter();
    mLeftMenuItems = new ArrayList<>();

//    for (int i = 0; i < mMenuDtos.size(); i++) {  // ...???
//      LeftMenuItem menuItem = new LeftMenuItem(i, mMenuDtos.get(i).getName());
//      mLeftMenuItems.add(menuItem);
//      mRowsAdapter.add(new ListRow(menuItem, new ArrayObjectAdapter()));
//    }

    LeftMenuItem home = new LeftMenuItem(0, getString(R.string.left_menu_home));
    mLeftMenuItems.add(home);
    mRowsAdapter.add(new ListRow(home, new ArrayObjectAdapter(new LeftMenuPresenter(getActivity()))));

    LeftMenuItem suggest = new LeftMenuItem(1, getString(R.string.left_menu_suggest));
    mLeftMenuItems.add(suggest);
    mRowsAdapter.add(new ListRow(suggest, new ArrayObjectAdapter(new LeftMenuPresenter(getActivity()))));

    LeftMenuItem hotTrend = new LeftMenuItem(2, getString(R.string.left_menu_hot_trend));
    mLeftMenuItems.add(hotTrend);
    mRowsAdapter.add(new ListRow(hotTrend, new ArrayObjectAdapter(new LeftMenuPresenter(getActivity()))));

    LeftMenuItem music = new LeftMenuItem(3, getString(R.string.left_menu_music));
    mLeftMenuItems.add(music);
    mRowsAdapter.add(new ListRow(music, new ArrayObjectAdapter(new LeftMenuPresenter(getActivity()))));

    LeftMenuItem entertainment = new LeftMenuItem(4, getString(R.string.left_menu_entertainment));
    mLeftMenuItems.add(entertainment);
    mRowsAdapter.add(new ListRow(entertainment, new ArrayObjectAdapter(new LeftMenuPresenter(getActivity()))));

    setAdapter(mRowsAdapter);

    getVerticalGridView().post(new Runnable() {
    @Override
    public void run() {
      ((MainActivity)getActivity()).requestFocusIcon();  // Sau khi add LeftMenuFragment thi Focus lai IconMenuFragment
    }
  });
  }

  private void customSetBackground(int color) {
    try {
      Class clazz = HeadersSupportFragment.class;
      Method m = clazz.getDeclaredMethod("setBackgroundColor", Integer.TYPE);
      m.setAccessible(true);
      m.invoke(this, color);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public interface OnMenuItemClickListener {
    void onLeftMenuItemClicked(long id);
  }
}
