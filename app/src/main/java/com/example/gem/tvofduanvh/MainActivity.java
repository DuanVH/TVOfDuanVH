package com.example.gem.tvofduanvh;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gem on 6/26/18.
 */

public class MainActivity extends FragmentActivity {

  private static final String TAG = MainActivity.class.getSimpleName();
  private static final String LEFT_MENU = "left_menu";
  private static final String ICON_MENU = "icon_menu";
  private static final String CONTENT = "content";

  private FragmentManager fragmentManager = getSupportFragmentManager();

  @BindView(R.id.fl_menu)
  FrameLayout mLeftMenuFl;

  @BindView(R.id.fl_icon_menu)
  FrameLayout mIconMenuFl;

  @BindView(R.id.fl_content)
  FrameLayout mContentFl;

  @BindView(R.id.parentContainer)
  CustomConstraintLayout parentContainer;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_fragment);
    ButterKnife.bind(this);

    mLeftMenuFl.setVisibility(View.VISIBLE);
    setupMenu();
  }

  private void setupMenu() {
    final LeftMenuFragment leftMenuFragment = new LeftMenuFragment();
    final IconMenuFragment iconMenuFragment = new IconMenuFragment();
    final ContentFragment contentFragment = new ContentFragment();

//    getSupportFragmentManager().beginTransaction()
//        .add(R.id.fl_menu, leftMenuFragment, LEFT_MENU)
//        .commit();

    getSupportFragmentManager().beginTransaction()
        .add(R.id.fl_icon_menu, iconMenuFragment, ICON_MENU)
        .commit();

    getSupportFragmentManager().beginTransaction()
        .add(R.id.fl_content, contentFragment, CONTENT)
        .commit();

    leftMenuFragment.setListener(new LeftMenuFragment.OnMenuItemClickListener() {

      @Override
      public void onMenuItemClicked(long id) {

      }
    });

//    iconMenuFragment.setListener(new IconMenuFragment.OnMenuItemClickListener() {
//
//      @Override
//      public void onMenuItemClicked(long id) {
//
//        if (leftMenuFragment.isAdded()) {
//          Toast.makeText(getApplicationContext(), "Fragment added ", Toast.LENGTH_SHORT).show();
//        } else {
//          Log.e(TAG, "Add Left Menu" );
//          mLeftMenuFl.setVisibility(View.VISIBLE);
//          getSupportFragmentManager().beginTransaction()
//              .add(R.id.fl_menu, leftMenuFragment, LEFT_MENU)
//              .commit();
//        }
//      }
//    });

//    contentFragment.setListener(new ContentFragment.OnContentListener() {
//
//      @Override
//      public void onContentListener(long id) {
//
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        LeftMenuFragment fragment = (LeftMenuFragment) getSupportFragmentManager().findFragmentByTag(LEFT_MENU);
//        if (fragment != null) {
//          Log.e(TAG, "Hide Left Menu" );
//          fragmentTransaction.remove(fragment);
//          fragmentTransaction.commit();
//          mLeftMenuFl.setVisibility(View.GONE);
//        }
//      }
//    });

    parentContainer.setOnChildFocusListener(new OnChildFocusListener() {
      @Override
      public boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        return false;
      }

      @Override
      public void onRequestChildFocus(View child, View focused) {
        if (child.getId() == R.id.fl_content) {
          FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            LeftMenuFragment fragment = (LeftMenuFragment) getSupportFragmentManager().findFragmentByTag(LEFT_MENU);
            if (fragment != null) {
              Log.e(TAG, "Hide Left Menu");
              fragmentTransaction.remove(fragment);
              fragmentTransaction.commit();
              mLeftMenuFl.setVisibility(View.GONE);
          }
        } else if (child.getId() == R.id.fl_icon_menu) {
          if (leftMenuFragment.isAdded()) {
            Toast.makeText(getApplicationContext(), "Fragment added ", Toast.LENGTH_SHORT).show();
          } else {
            Log.e(TAG, "Add Left Menu");
            mLeftMenuFl.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_menu, leftMenuFragment, LEFT_MENU)
                .commit();
          }
        }
      }
    });

    parentContainer.setOnFocusSearchListener(new OnFocusSearchListener() {
      @Override
      public View onFocusSearch(View focused, int direction) {
        return null;  // return cai rows ma da focus
      }
    });
  }

}
