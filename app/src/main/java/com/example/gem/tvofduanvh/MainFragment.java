package com.example.gem.tvofduanvh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;

/**
 * Created by gem on 6/26/18.
 */

public class MainFragment extends BrowseFragment {

  private ArrayObjectAdapter mAdapter;

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
//    setupUI();
//    loadRows();

   // setHeaderUI();
    loadRowsNew();
  }

  private void setHeaderUI() {
    //setupUI();
    setHeaderPresenterSelector(new PresenterSelector() {
      @Override
      public Presenter getPresenter(Object item) {
        return new LeftMenuPresenter(getActivity());
      }
    });
  }

  private void loadRowsNew() {
    mAdapter = new ArrayObjectAdapter(new ListRowPresenter());

    LeftMenuItem leftMenuItem = new LeftMenuItem(0, "DuanVH");

    mAdapter.add(new ListRow(leftMenuItem, new ArrayObjectAdapter(new LeftMenuPresenter(getActivity()
    ))));
    setAdapter(mAdapter);
  }

  private void setupUI() {
    setTitle("TV of DuanVH");
    setHeadersState(HEADERS_ENABLED);
    setHeadersTransitionOnBackEnabled(true);
    setBrandColor(getResources().getColor(R.color.colorBackground));
    setSearchAffordanceColor(getResources().getColor(R.color.colorRed));
  }

  private void loadRows() {
    mAdapter = new ArrayObjectAdapter(new ListRowPresenter());

    HeaderItem headerItem = new HeaderItem(0, "MyTV");
    CardPresenter cardPresenter = new CardPresenter();
    ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(cardPresenter);

    arrayObjectAdapter.add(new Movie("Luffy", " Straw Hat", "https://otakukart.com/wp-content/uploads/2017/08/one_piece_movie_z_luffy_by_exalmas-d61qk9b.png"));
    arrayObjectAdapter.add(new Movie("Zoro", " Straw Hat", "https://vignette.wikia.nocookie.net/onepiece/images/6/64/Roronoa_Zoro_Anime_Pre_Timeskip_Infobox.png"));
    arrayObjectAdapter.add(new Movie("Usopp", " Straw Hat", "https://vignette.wikia.nocookie.net/onepiece/images/8/8a/Kuro_Kabuto_Infobox.png"));
    arrayObjectAdapter.add(new Movie("Nami", " Straw Hat", "https://res.cloudinary.com/teepublic/image/private/s--4Z1N4EFE--/t_Preview/b_rgb:ffffff,c_limit,f_jpg,h_630,q_90,w_630/v1511697333/production/designs/2103685_1.jpg"));

    mAdapter.add(new ListRow(headerItem, arrayObjectAdapter));

    setAdapter(mAdapter);
  }


}
