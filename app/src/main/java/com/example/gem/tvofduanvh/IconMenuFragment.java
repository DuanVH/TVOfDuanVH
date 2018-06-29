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
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class IconMenuFragment extends HeadersSupportFragment {

  private static final String KEY_ICON_MENU = "KEY_ICON_MENU";
  private static final String TAG = IconMenuFragment.class.getSimpleName();

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

    setListIconMenu();

    setOnHeaderClickedListener(new OnHeaderClickedListener() {
      @Override
      public void onHeaderClicked(RowHeaderPresenter.ViewHolder viewHolder, Row row) {
        if (mListener != null) {
          mListener.onMenuItemClicked((int) row.getId());
          Log.e(TAG, "icon menu clicked");
        }
      }
    });

    setOnHeaderViewSelectedListener(new OnHeaderViewSelectedListener() {
      @Override
      public void onHeaderSelected(RowHeaderPresenter.ViewHolder viewHolder, Row row) {
        if (mListener != null) {
          mListener.onMenuItemClicked((int) row.getId());
          Log.e(TAG, "icon menu selected");
        }
      }
    });

    getVerticalGridView().setPadding(0, 0, 0, 0);
    getVerticalGridView().setVerticalSpacing(0);
    customSetBackground();
  }

  private void setListIconMenu() {
    mAdapterRows = new ArrayObjectAdapter(new ListRowPresenter());
    iconMenuItems = new ArrayList<>();

    IconMenuItem search = new IconMenuItem(0, "search", R.drawable.icon_search);
    iconMenuItems.add(search);
    mAdapterRows.add(new ListRow(search, new ArrayObjectAdapter(new IconMenuPresenter(getActivity()))));

    IconMenuItem home = new IconMenuItem(1, "home", R.drawable.icon_home);
    iconMenuItems.add(home);
    mAdapterRows.add(new ListRow(home, new ArrayObjectAdapter(new IconMenuPresenter(getActivity()))));

    IconMenuItem supervisorAccount = new IconMenuItem(2, "supervisor account", R.drawable.icon_supervisor_account);
    iconMenuItems.add(supervisorAccount);
    mAdapterRows.add(new ListRow(supervisorAccount, new ArrayObjectAdapter(new IconMenuPresenter(getActivity()))));

    IconMenuItem folder = new IconMenuItem(3, "folder", R.drawable.icon_folder);
    iconMenuItems.add(folder);
    mAdapterRows.add(new ListRow(folder, new ArrayObjectAdapter(new IconMenuPresenter(getActivity()))));

    IconMenuItem account = new IconMenuItem(4, "account", R.drawable.icon_account);
    iconMenuItems.add(account);
    mAdapterRows.add(new ListRow(account, new ArrayObjectAdapter(new IconMenuPresenter(getActivity()))));

    IconMenuItem setting = new IconMenuItem(5, "setting", R.drawable.icon_setting);
    iconMenuItems.add(setting);
    mAdapterRows.add(new ListRow(setting, new ArrayObjectAdapter(new IconMenuPresenter(getActivity()))));


    setAdapter(mAdapterRows);
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

  public interface OnMenuItemClickListener {
    void onMenuItemClicked(long id);
  }
}
