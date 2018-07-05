package com.example.gem.tvofduanvh;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.HeadersSupportFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowHeaderPresenter;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gem on 7/5/18.
 */

public class TopIconMenuFragment extends HeadersSupportFragment {

  private static final String KEY_TOP_ICON_MENU = "KEY_TOP_ICON_MENU";

  private ArrayObjectAdapter mAdapterRows;

  private List<IconMenuItem> iconMenuItems;
  OnTopMenuItemClickListener mListener;

  public void setLister(OnTopMenuItemClickListener mListener) {
    this.mListener = mListener;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null && getArguments().containsKey(KEY_TOP_ICON_MENU)) {

    }

    setPresenterSelector(new PresenterSelector() {
      @Override
      public Presenter getPresenter(Object item) {
        return new IconMenuPresenter(getActivity());
      }
    });
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    setTopIconMenu();

    setOnHeaderViewSelectedListener(new OnHeaderViewSelectedListener() {
      @Override
      public void onHeaderSelected(RowHeaderPresenter.ViewHolder viewHolder, Row row) {
        if (mListener != null)
          mListener.onTopIconMenuItemClicked((int) row.getId());
      }
    });

    setOnHeaderClickedListener(new OnHeaderClickedListener() {
      @Override
      public void onHeaderClicked(RowHeaderPresenter.ViewHolder viewHolder, Row row) {

      }
    });

    getVerticalGridView().setPadding(0, 0, 0, 0);
    getVerticalGridView().setVerticalSpacing(0);
    customSetBackground();
  }

  public View getSelectedView() {
    RecyclerView.ViewHolder vh = getVerticalGridView().findViewHolderForAdapterPosition(getSelectedPosition());
    if (vh != null)
      return vh.itemView;
    return null;
  }

  private void setTopIconMenu() {
    mAdapterRows = new ArrayObjectAdapter(new ListRowPresenter());
    iconMenuItems = new ArrayList<>();

    IconMenuItem iconApp = new IconMenuItem(0, "search", R.drawable.icon_myclip);
    iconMenuItems.add(iconApp);
    mAdapterRows.add(new ListRow(iconApp, new ArrayObjectAdapter(new IconMenuPresenter(getActivity()))));

    IconMenuItem search = new IconMenuItem(1, "search", R.drawable.icon_search);
    iconMenuItems.add(search);
    mAdapterRows.add(new ListRow(search, new ArrayObjectAdapter(new IconMenuPresenter(getActivity()))));

    setAdapter(mAdapterRows);
  }

  private void customSetBackground() {
    try {
      Class clazz = HeadersSupportFragment.class;
      Method m = clazz.getDeclaredMethod("setBackgroundColor", Integer.TYPE);
      m.setAccessible(true);
      m.invoke(this, Color.DKGRAY);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public interface OnTopMenuItemClickListener {
    void onTopIconMenuItemClicked(long id);
  }

}
