package com.example.gem.tvofduanvh;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by gem on 6/26/18.
 */

public class MainActivity extends FragmentActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_fragment);

    setupMenu();
  }

  private void setupMenu() {
    LeftMenuFragment leftMenuFragment = new LeftMenuFragment();

    leftMenuFragment.setListener(new LeftMenuFragment.OnMenuItemClickListener() {
      @Override
      public void onMenuItemClicked(long id) {

      }
    });

    getSupportFragmentManager().beginTransaction()
        .add(R.id.leftMenuContainer, leftMenuFragment)
        .commit();
  }
}
