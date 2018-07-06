package com.example.gem.tvofduanvh;

import android.content.Context;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.RowHeaderPresenter;
import android.support.v17.leanback.widget.RowHeaderView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabWidget;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IconMenuPresenter extends RowHeaderPresenter {

  private Context mContext;

  public IconMenuPresenter(Context mContext) {
    this.mContext = mContext;
  }

  @Override
  public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
    return new IconMenuViewHolder((LayoutInflater.from(mContext).inflate(R.layout.item_icon_menu, parent, false)));
  }

  @Override
  public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
    super.onBindViewHolder(viewHolder, item);

    final IconMenuItem iconMenuItem = (IconMenuItem) ((ListRow) item).getHeaderItem();

    final IconMenuViewHolder iconMenuViewHolder = (IconMenuViewHolder) viewHolder;

    if (iconMenuItem.getId() == 2 || iconMenuItem.getId() == 6) {
      iconMenuViewHolder.view.setVisibility(View.INVISIBLE);
      iconMenuViewHolder.view.setFocusable(false);
      iconMenuViewHolder.view.setFocusableInTouchMode(false);
    } else {
      iconMenuViewHolder.view.setVisibility(View.VISIBLE);
      iconMenuViewHolder.view.setFocusable(true);
      iconMenuViewHolder.view.setFocusableInTouchMode(true);
      if (iconMenuItem.getUri() == null)
        iconMenuViewHolder.mIv.setImageDrawable(mContext.getResources().getDrawable(iconMenuItem.getImage()));
      else
        Picasso.with(mContext).load(iconMenuItem.getUri()).fit().into(iconMenuViewHolder.mIv);
    }

    iconMenuViewHolder.view.setTag(iconMenuItem.getImage());

    iconMenuViewHolder.view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View view, boolean b) {
//        IconMenuItem data = (IconMenuFragment) view.getTag();

      }
    });
  }

  class IconMenuViewHolder extends ViewHolder {

    @BindView(R.id.iv_icon_item_menu)
    ImageView mIv;

    public IconMenuViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }


}
