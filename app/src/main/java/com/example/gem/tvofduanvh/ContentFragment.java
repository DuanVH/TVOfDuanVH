package com.example.gem.tvofduanvh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gem on 6/28/18.
 */

public class ContentFragment extends BaseRowsFragment {

  private List<ContentRight> mContents;

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    showData();
  }

  private void showData() {
    mContents = new ArrayList<>();
    mContents.add(new ContentRight("Luffy", " Straw Hat",
        "https://otakukart.com/wp-content/uploads/2017/08/one_piece_movie_z_luffy_by_exalmas-d61qk9b.png"));
    mContents.add(new ContentRight("Zoro", " Straw Hat",
        "https://vignette.wikia.nocookie.net/onepiece/images/6/64/Roronoa_Zoro_Anime_Pre_Timeskip_Infobox.png"));
    mContents.add(new ContentRight("Usopp", " Straw Hat",
        "https://vignette.wikia.nocookie.net/onepiece/images/8/8a/Kuro_Kabuto_Infobox.png"));
    mContents.add(new ContentRight("Nami", " Straw Hat",
        "https://res.cloudinary.com/teepublic/image/private/s--4Z1N4EFE--/t_Preview/b_rgb:ffffff,c_limit,f_jpg,h_630,q_90,w_630/v1511697333/production/designs/2103685_1.jpg"));
    mContents.add(new ContentRight("Luffy", " Straw Hat",
        "https://otakukart.com/wp-content/uploads/2017/08/one_piece_movie_z_luffy_by_exalmas-d61qk9b.png"));
    mContents.add(new ContentRight("Zoro", " Straw Hat",
        "https://vignette.wikia.nocookie.net/onepiece/images/6/64/Roronoa_Zoro_Anime_Pre_Timeskip_Infobox.png"));
    mContents.add(new ContentRight("Usopp", " Straw Hat",
        "https://vignette.wikia.nocookie.net/onepiece/images/8/8a/Kuro_Kabuto_Infobox.png"));
    mContents.add(new ContentRight("Nami", " Straw Hat",
        "https://res.cloudinary.com/teepublic/image/private/s--4Z1N4EFE--/t_Preview/b_rgb:ffffff,c_limit,f_jpg,h_630,q_90,w_630/v1511697333/production/designs/2103685_1.jpg"));


    getRowsAdapter().clear();

    for(int i=0; i<10; i++) {
      ArrayObjectAdapter itemsAdapter = new ArrayObjectAdapter(
          new VideoPresenter(getActivity(), false));
      Collections.shuffle(mContents);
      itemsAdapter.addAll(0, mContents);
      HeaderItem headerItem = new HeaderItem("Row ".concat(i+""));
      getRowsAdapter().add(new ListRow(headerItem, itemsAdapter));
    }
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
    return false;
  }

  @Override
  protected float horizontalWindowAlignmentOffsetPercent() {
    return 50;
  }
}
