package com.example.gem.tvofduanvh;

import android.annotation.SuppressLint;
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
import android.util.Log;
import android.view.View;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class IconMenuFragment extends HeadersSupportFragment {

  private static final String KEY_ICON_MENU = "KEY_ICON_MENU";
  private static final String TAG = IconMenuFragment.class.getSimpleName();

  public static final int TOP_ICON_MENU = 0;
  public static final int BET_ICON_MENU = 1;
  public static final int BOT_ICON_MENU = 2;

  private ArrayObjectAdapter mAdapterRows;
  private OnMenuItemClickListener mListener;

  private List<IconMenuItem> iconMenuItems;
  private int mCurrentPosition;


  public void setListener(OnMenuItemClickListener listener) {
    this.mListener = listener;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null && getArguments().containsKey(KEY_ICON_MENU)) {
      // ??? DTO
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

    setListBetweenIconMenu();

    setOnHeaderClickedListener(new OnHeaderClickedListener() {
      @Override
      public void onHeaderClicked(RowHeaderPresenter.ViewHolder viewHolder, Row row) {
        if (mListener != null) {
          mListener.onIconMenuItemClicked((int) row.getId());
          Log.e(TAG, "icon menu clicked");
        }
      }
    });

    setOnHeaderViewSelectedListener(new OnHeaderViewSelectedListener() {
      @Override
      public void onHeaderSelected(RowHeaderPresenter.ViewHolder viewHolder, Row row) {
        if (mListener != null) {
//          mListener.onIconMenuItemClicked((int) row.getId());
          Log.e(TAG, "icon menu selected");
        }
      }
    });

    getVerticalGridView().setPadding(0, 100, 0, 0);
    getVerticalGridView().setVerticalSpacing(0);
    customSetBackground();
  }

  public View getSelectedView() {
    RecyclerView.ViewHolder vh = getVerticalGridView().findViewHolderForAdapterPosition(getSelectedPosition());
    if (vh != null)
      return vh.itemView;
    return null;
  }


  private void setListBetweenIconMenu() {
    mAdapterRows = new ArrayObjectAdapter(new ListRowPresenter());
    iconMenuItems = new ArrayList<>();

    IconMenuItem home = new IconMenuItem(3, "home", R.drawable.icon_home);
    iconMenuItems.add(home);
    mAdapterRows.add(new ListRow(home, new ArrayObjectAdapter(new IconMenuPresenter(getActivity()))));

    IconMenuItem supervisorAccount = new IconMenuItem(4, "supervisor account", R.drawable.icon_supervisor_account);
    iconMenuItems.add(supervisorAccount);
    mAdapterRows.add(new ListRow(supervisorAccount, new ArrayObjectAdapter(new IconMenuPresenter(getActivity()))));

    IconMenuItem folder = new IconMenuItem(5, "folder", R.drawable.icon_folder);
    iconMenuItems.add(folder);
    mAdapterRows.add(new ListRow(folder, new ArrayObjectAdapter(new IconMenuPresenter(getActivity()))));

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

  public interface OnMenuItemClickListener {
    void onIconMenuItemClicked(long id);
  }
}
