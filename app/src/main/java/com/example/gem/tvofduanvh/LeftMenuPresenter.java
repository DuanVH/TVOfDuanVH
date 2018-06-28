package com.example.gem.tvofduanvh;


import android.content.Context;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.RowHeaderPresenter;
import android.view.*;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gem on 6/26/18.
 */

public class LeftMenuPresenter extends RowHeaderPresenter {

  private Context mContext;

  public LeftMenuPresenter(Context mContext) {
    this.mContext = mContext;
  }

  @Override
  public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
    return new MenuItemHolder((LayoutInflater.from(mContext)).inflate(R.layout.item_left_menu, parent, false));
  }

  @Override
  public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
    super.onBindViewHolder(viewHolder, item);
    final LeftMenuItem leftMenuItem = (LeftMenuItem) ((ListRow) item).getHeaderItem();

    final MenuItemHolder menuItemHolder = (MenuItemHolder) viewHolder;
    menuItemHolder.mTitleTv.setText(leftMenuItem.getName());

    menuItemHolder.view.setTag(leftMenuItem);
    menuItemHolder.view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View view, boolean b) {
        LeftMenuItem data = (LeftMenuItem) view.getTag();
        if (data.mIsChosen) {
          menuItemHolder.mTitleTv.setSelected(true);
        } else
          menuItemHolder.mTitleTv.setSelected(b);
      }
    });

  }

  class MenuItemHolder extends ViewHolder {

    @BindView(R.id.tv_item_title)
    TextView mTitleTv;

    public MenuItemHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
//      mTitleTv.setPivotX(0);
    }

    public void zoom(boolean zoomed) {
      if (zoomed) {
        mTitleTv.animate().scaleX(1f).scaleY(1f).setDuration(150).start();
      } else
        mTitleTv.animate().scaleX(0.8f).scaleY(0.8f).setDuration(150).start();
    }
  }
}
