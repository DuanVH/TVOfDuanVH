package com.example.gem.tvofduanvh;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gem on 6/28/18.
 */

public class ContentFragment extends BaseRowsFragment {

  private static final String TAG = ContentFragment.class.getSimpleName();
  private List<ContentRightItem> mContents;

  private OnContentListener mListener;

  public void setListener(OnContentListener mListener) {
    this.mListener = mListener;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    showData();
    setEventListener();
  }

  private void setEventListener() {

    setOnItemViewSelectedListener(new OnItemViewSelectedListener() {
      @Override
      public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        if (mListener != null) {
          mListener.onContentSelectedListener(((ContentRightItem) item));
          Log.e(TAG, "Content Selected: ");
        }
      }
    });

    setOnItemViewClickedListener(new OnItemViewClickedListener() {
      @Override
      public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        if (mListener != null) {
          mListener.onContentClickedListener(((ContentRightItem) item));
          Log.e(TAG, "Content Clicked: ");

        }
      }
    });

    getVerticalGridView().setPadding(0, 0, 0, 0);
    getVerticalGridView().setVerticalSpacing(0);

    customSetBackground(Color.BLUE);

  }

  public void showData() {
    mContents = new ArrayList<>();
    mContents.add(new ContentRightItem("Luffy", " Straw Hat",
        "https://otakukart.com/wp-content/uploads/2017/08/one_piece_movie_z_luffy_by_exalmas-d61qk9b.png",
        "https://zmp3-mp3-mv1-te-vnno-vn-5.zadn.vn/9337d5daea9f03c15a8e/4632193790717001957?authen=exp=1530864416~acl=/9337d5daea9f03c15a8e/*~hmac=46aa79c46641895e7589d8ef782ae25e"));
    mContents.add(new ContentRightItem("Zoro", " Straw Hat",
        "https://vignette.wikia.nocookie.net/onepiece/images/6/64/Roronoa_Zoro_Anime_Pre_Timeskip_Infobox.png",
        "https://zmp3-mp3-mv1.zadn.vn/69cab0758e30676e3e21/5124731231472285839?authen=exp=1530689174~acl=/69cab0758e30676e3e21/*~hmac=f223f8936c33c7ea461d1d278ebddf07"));
    mContents.add(new ContentRightItem("Usopp", " Straw Hat",
        "https://vignette.wikia.nocookie.net/onepiece/images/8/8a/Kuro_Kabuto_Infobox.png",
        "https://zmp3-mp3-mv1.zadn.vn/61520d2f316ad834817b/43783655726625144?authen=exp=1530689212~acl=/61520d2f316ad834817b/*~hmac=441efa4e1a44d1feba263bf7d2a7716b"));
    mContents.add(new ContentRightItem("Nami", " Straw Hat",
        "https://res.cloudinary.com/teepublic/image/private/s--4Z1N4EFE--/t_Preview/b_rgb:ffffff,c_limit,f_jpg,h_630,q_90,w_630/v1511697333/production/designs/2103685_1.jpg",
        "https://zmp3-mp3-mv1.zadn.vn/c0c8bbf487b16eef37a0/5589423063346503767?authen=exp=1530689278~acl=/c0c8bbf487b16eef37a0/*~hmac=a79a650fa1f18e0c10730a4e6882af77"));
    mContents.add(new ContentRightItem("Luffy", " Straw Hat",
        "https://otakukart.com/wp-content/uploads/2017/08/one_piece_movie_z_luffy_by_exalmas-d61qk9b.png",
        "https://zmp3-mp3-mv1-te-vnno-vn-5.zadn.vn/9337d5daea9f03c15a8e/4632193790717001957?authen=exp=1530864416~acl=/9337d5daea9f03c15a8e/*~hmac=46aa79c46641895e7589d8ef782ae25e"));
    mContents.add(new ContentRightItem("Zoro", " Straw Hat",
        "https://vignette.wikia.nocookie.net/onepiece/images/6/64/Roronoa_Zoro_Anime_Pre_Timeskip_Infobox.png",
        "https://zmp3-mp3-mv1.zadn.vn/b4f66c445201bb5fe210/2412224448554404240?authen=exp=1530689413~acl=/b4f66c445201bb5fe210/*~hmac=4e14993cb6d7cfd6a30ecff977a9544c"));
    mContents.add(new ContentRightItem("Usopp", " Straw Hat",
        "https://vignette.wikia.nocookie.net/onepiece/images/8/8a/Kuro_Kabuto_Infobox.png",
        "https://zmp3-mp3-mv1.zadn.vn/bfd0b2658e20677e3e31/385753945392507054?authen=exp=1530689506~acl=/bfd0b2658e20677e3e31/*~hmac=e2319620cc1ea1fa9d8b4aa592e7b49c"));
    mContents.add(new ContentRightItem("Nami", " Straw Hat",
        "https://res.cloudinary.com/teepublic/image/private/s--4Z1N4EFE--/t_Preview/b_rgb:ffffff,c_limit,f_jpg,h_630,q_90,w_630/v1511697333/production/designs/2103685_1.jpg",
        "https://zmp3-mp3-mv1.zadn.vn/ed52d61be85e0100584f/7151052435356843107?authen=exp=1530682901~acl=/ed52d61be85e0100584f/*~hmac=0e15838f821b2f29abc1f1e83dc14de7"));
    getRowsAdapter().clear();

    for (int i = 0; i < 10; i++) {
      ArrayObjectAdapter itemsAdapter = new ArrayObjectAdapter(new ContentRightPresenter(getActivity(), false));
      Collections.shuffle(mContents);

      itemsAdapter.addAll(0, mContents);

      HeaderItem headerItem = new HeaderItem("Row ".concat(i + ""));

      getRowsAdapter().add(new ListRow(headerItem, itemsAdapter));
    }
  }

  public void showNewData() {
    if (!mContents.isEmpty()) {
      mContents.clear();
      getRowsAdapter().clear();
    }
    mContents = new ArrayList<>();
    mContents.add(new ContentRightItem("Luffy", " Straw Hat",
        "https://otakukart.com/wp-content/uploads/2017/08/one_piece_movie_z_luffy_by_exalmas-d61qk9b.png",
        "https://zmp3-mp3-mv1-te-vnno-vn-5.zadn.vn/9337d5daea9f03c15a8e/4632193790717001957?authen=exp=1530864416~acl=/9337d5daea9f03c15a8e/*~hmac=46aa79c46641895e7589d8ef782ae25e"));
    mContents.add(new ContentRightItem("Zoro", " Straw Hat",
        "https://vignette.wikia.nocookie.net/onepiece/images/6/64/Roronoa_Zoro_Anime_Pre_Timeskip_Infobox.png",
        "https://zmp3-mp3-mv1.zadn.vn/69cab0758e30676e3e21/5124731231472285839?authen=exp=1530689174~acl=/69cab0758e30676e3e21/*~hmac=f223f8936c33c7ea461d1d278ebddf07"));
    mContents.add(new ContentRightItem("Usopp", " Straw Hat",
        "https://vignette.wikia.nocookie.net/onepiece/images/8/8a/Kuro_Kabuto_Infobox.png",
        "https://zmp3-mp3-mv1.zadn.vn/61520d2f316ad834817b/43783655726625144?authen=exp=1530689212~acl=/61520d2f316ad834817b/*~hmac=441efa4e1a44d1feba263bf7d2a7716b"));
    mContents.add(new ContentRightItem("Nami", " Straw Hat",
        "https://res.cloudinary.com/teepublic/image/private/s--4Z1N4EFE--/t_Preview/b_rgb:ffffff,c_limit,f_jpg,h_630,q_90,w_630/v1511697333/production/designs/2103685_1.jpg",
        "https://zmp3-mp3-mv1.zadn.vn/c0c8bbf487b16eef37a0/5589423063346503767?authen=exp=1530689278~acl=/c0c8bbf487b16eef37a0/*~hmac=a79a650fa1f18e0c10730a4e6882af77"));

    getRowsAdapter().clear();

    for (int i = 0; i < 10; i++) {
      ArrayObjectAdapter itemsAdapter = new ArrayObjectAdapter(
          new ContentRightPresenter(getActivity(), false));
      Collections.shuffle(mContents);

      itemsAdapter.addAll(0, mContents);

      HeaderItem headerItem = new HeaderItem("Row ".concat(i + ""));

      getRowsAdapter().add(new ListRow(headerItem, itemsAdapter));
    }

  }


  private void customSetBackground(int color) {
    try {
      Class clazz = BaseRowsFragment.class;
      Method m = clazz.getDeclaredMethod("setBackgroundColor", Integer.TYPE);
      m.setAccessible(true);
      m.invoke(this, color);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public interface OnContentListener {
    void onContentSelectedListener(ContentRightItem item);
    void onContentClickedListener(ContentRightItem item);
  }

  @Override
  protected int zoomFactor() {
    return 1;
  }

  @Override
  protected boolean enableShadow() {
    return true;
  }

  @Override
  protected boolean enableHorizontalAlignment() {
    return true;
  }

  @Override
  protected float horizontalWindowAlignmentOffsetPercent() {
    return 50;
  }

}
