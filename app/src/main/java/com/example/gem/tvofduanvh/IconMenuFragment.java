package com.example.gem.tvofduanvh;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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

    setListIconMenu();

    setOnHeaderClickedListener(new OnHeaderClickedListener() {
      @Override
      public void onHeaderClicked(RowHeaderPresenter.ViewHolder viewHolder, Row row) {
        if (mListener != null) {
          mListener.onIconMenuItemClicked((int) row.getId());
          Log.e(TAG, "icon menu clicked");

//          updateLastIcon();
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

    getVerticalGridView().setPadding(0, 200, 0, 0);
    getVerticalGridView().setVerticalSpacing(0);
    customSetBackground();
  }

  public View getSelectedView() {
    RecyclerView.ViewHolder vh = getVerticalGridView().findViewHolderForAdapterPosition(getSelectedPosition());
    if (vh != null)
      return vh.itemView;
    return null;
  }


  private void setListIconMenu() {
    mAdapterRows = new ArrayObjectAdapter(new ListRowPresenter());
    iconMenuItems = new ArrayList<>();

    IconMenuItem search = new IconMenuItem(1, "search", R.drawable.icon_search);
    iconMenuItems.add(search);
    mAdapterRows.add(new ListRow(search, new ArrayObjectAdapter(new IconMenuPresenter(getActivity()))));

    // hide
    IconMenuItem none = new IconMenuItem(2, "none", -1, null);
    iconMenuItems.add(none);
    mAdapterRows.add(new ListRow(none, new ArrayObjectAdapter(new IconMenuPresenter(getActivity()))));

    IconMenuItem home = new IconMenuItem(3, "home", R.drawable.icon_home, null);
    iconMenuItems.add(home);
    mAdapterRows.add(new ListRow(home, new ArrayObjectAdapter(new IconMenuPresenter(getActivity()))));

    IconMenuItem supervisorAccount = new IconMenuItem(4, "supervisor account", R.drawable.icon_supervisor_account, null);
    iconMenuItems.add(supervisorAccount);
    mAdapterRows.add(new ListRow(supervisorAccount, new ArrayObjectAdapter(new IconMenuPresenter(getActivity()))));

    IconMenuItem folder = new IconMenuItem(5, "folder", R.drawable.icon_folder, null);
    iconMenuItems.add(folder);
    mAdapterRows.add(new ListRow(folder, new ArrayObjectAdapter(new IconMenuPresenter(getActivity()))));

    // hide
    IconMenuItem none2 = new IconMenuItem(6, "none2", -1, null);
    iconMenuItems.add(none2);
    mAdapterRows.add(new ListRow(none2, new ArrayObjectAdapter(new IconMenuPresenter(getActivity()))));

    IconMenuItem account = new IconMenuItem(7, "account", R.drawable.icon_account, null);
    iconMenuItems.add(account);
    mAdapterRows.add(new ListRow(account, new ArrayObjectAdapter(new IconMenuPresenter(getActivity()))));

    IconMenuItem setting = new IconMenuItem(8, "setting", R.drawable.icon_setting, null);
    iconMenuItems.add(setting);
    mAdapterRows.add(new ListRow(setting, new ArrayObjectAdapter(new IconMenuPresenter(getActivity()))));

    setAdapter(mAdapterRows);
  }

  // change image at last index
  public void updateLastIcon() {
    int lastIndex = mAdapterRows.size() - 1;
    Row lastRow = (ListRow) mAdapterRows.get(lastIndex);
    IconMenuItem icon = (IconMenuItem) lastRow.getHeaderItem();
    icon.setImage(R.drawable.icon_myclip);
    mAdapterRows.notifyItemRangeChanged(lastIndex, 1);
  }
  
  public void updateAvatar(Uri uriImage) {
    int indexAvatar = mAdapterRows.size() - 2;
    Row indexRow = (ListRow) mAdapterRows.get(indexAvatar);
    IconMenuItem iconAvatar = (IconMenuItem) indexRow.getHeaderItem();
    iconAvatar.setUri(uriImage);
    mAdapterRows.notifyItemRangeChanged(indexAvatar, 1);
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
