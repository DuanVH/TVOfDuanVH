package com.example.gem.tvofduanvh;

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
          mListener.onContentListener((int) row.getId());
          Log.e(TAG, "Content Selected: ");
        }
      }
    });

    setOnItemViewClickedListener(new OnItemViewClickedListener() {
      @Override
      public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        if (mListener != null) {
          mListener.onContentListener((int) row.getId());
          Log.e(TAG, "Content Clicked: ");
        }
      }
    });

    getVerticalGridView().setPadding(0, 0, 0, 0);
    getVerticalGridView().setVerticalSpacing(0);

  }

  private void showData() {
    mContents = new ArrayList<>();
    mContents.add(new ContentRightItem("Luffy", " Straw Hat",
        "https://otakukart.com/wp-content/uploads/2017/08/one_piece_movie_z_luffy_by_exalmas-d61qk9b.png",
        "https://www.youtube.com/watch?v=BGhUwXnXre0"));
    mContents.add(new ContentRightItem("Zoro", " Straw Hat",
        "https://vignette.wikia.nocookie.net/onepiece/images/6/64/Roronoa_Zoro_Anime_Pre_Timeskip_Infobox.png",
        "https://www.youtube.com/watch?v=C3KzANL6gTY"));
    mContents.add(new ContentRightItem("Usopp", " Straw Hat",
        "https://vignette.wikia.nocookie.net/onepiece/images/8/8a/Kuro_Kabuto_Infobox.png",
        "https://www.youtube.com/watch?v=UYBRXOxVDIA"));
    mContents.add(new ContentRightItem("Nami", " Straw Hat",
        "https://res.cloudinary.com/teepublic/image/private/s--4Z1N4EFE--/t_Preview/b_rgb:ffffff,c_limit,f_jpg,h_630,q_90,w_630/v1511697333/production/designs/2103685_1.jpg",
        "https://www.youtube.com/watch?v=t6fPzVNIEB0"));
    mContents.add(new ContentRightItem("Luffy", " Straw Hat",
        "https://otakukart.com/wp-content/uploads/2017/08/one_piece_movie_z_luffy_by_exalmas-d61qk9b.png",
        "https://www.youtube.com/watch?v=OGS1sGhGsOs"));
    mContents.add(new ContentRightItem("Zoro", " Straw Hat",
        "https://vignette.wikia.nocookie.net/onepiece/images/6/64/Roronoa_Zoro_Anime_Pre_Timeskip_Infobox.png",
        "https://www.youtube.com/watch?v=7C2z4GqqS5E"));
    mContents.add(new ContentRightItem("Usopp", " Straw Hat",
        "https://vignette.wikia.nocookie.net/onepiece/images/8/8a/Kuro_Kabuto_Infobox.png",
        "https://www.youtube.com/watch?v=hbKKL9E5uEs"));
    mContents.add(new ContentRightItem("Nami", " Straw Hat",
        "https://res.cloudinary.com/teepublic/image/private/s--4Z1N4EFE--/t_Preview/b_rgb:ffffff,c_limit,f_jpg,h_630,q_90,w_630/v1511697333/production/designs/2103685_1.jpg",
        "https://www.youtube.com/watch?v=x9qQujBVXAo"));


    getRowsAdapter().clear();

    for (int i = 0; i < 10; i++) {
      ArrayObjectAdapter itemsAdapter = new ArrayObjectAdapter(
          new VideoPresenter(getActivity(), false));
      Collections.shuffle(mContents);

      itemsAdapter.addAll(0, mContents);

      HeaderItem headerItem = new HeaderItem("Row ".concat(i + ""));

      getRowsAdapter().add(new ListRow(headerItem, itemsAdapter));
    }
  }

  public interface OnContentListener {
    void onContentListener(long id);
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
