package com.example.gem.tvofduanvh;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
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

//    int hidingOffset = 20;
//    final int currentMargin = 100;
//    final int slideDestination = 200;
////    final int currentMargin = ((ViewGroup.MarginLayoutParams) relatedFilmsContainer.getLayoutParams()).topMargin;
////    final int slideDestination = hide ? (int) (mScreenHeight * 0.2f) + playbackControlContainer.getHeight() + hidingOffset : 0;
//    final int slideDelta = slideDestination - currentMargin;
//
//    Animation toggleAnimation = new Animation() {
//      @Override
//      protected void applyTransformation(float interpolatedTime, Transformation t) {
//        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) mLeftMenuFl.getLayoutParams();
//        layoutParams.topMargin = (int) (currentMargin + slideDelta * interpolatedTime);
//        mLeftMenuFl.setLayoutParams(layoutParams);
//      }
//    };
//    toggleAnimation.setDuration(300);
//    mLeftMenuFl.startAnimation(toggleAnimation);



    getSupportFragmentManager().beginTransaction()
        .add(R.id.fl_icon_menu, iconMenuFragment, ICON_MENU)
        .commit();

    getSupportFragmentManager().beginTransaction()
        .add(R.id.fl_menu, leftMenuFragment, LEFT_MENU)
        .commit();

    getSupportFragmentManager().beginTransaction()
        .add(R.id.fl_content, contentFragment, CONTENT)
//        .addToBackStack(null)
        .commit();

    leftMenuFragment.setListener(new LeftMenuFragment.OnMenuItemClickListener() {

      @Override
      public void onMenuItemClicked(long id) {

      }
    });

    iconMenuFragment.setListener(new IconMenuFragment.OnMenuItemClickListener() {

      @Override
      public void onMenuItemClicked(long id) {

      }
    });

    contentFragment.setListener(new ContentFragment.OnContentListener() {

      @Override
      public void onContentListener(long id) {

      }
    });

    parentContainer.setOnChildFocusListener(new OnChildFocusListener() {
      @Override
      public boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        return false;
      }

      @Override
      public void onRequestChildFocus(View child, View focused) {
        if (child.getId() == R.id.fl_content) {
//          toggleMiddleMenu(true);
//          FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//          LeftMenuFragment fragment = (LeftMenuFragment) getSupportFragmentManager().findFragmentByTag(LEFT_MENU);
//          if (fragment != null) {
//            Log.e(TAG, "Hide Left Menu");
//            fragmentTransaction.remove(fragment);
//            fragmentTransaction.commit();
//            mLeftMenuFl.setVisibility(View.GONE);
//          }
        } else if (child.getId() == R.id.fl_icon_menu) {
          toggleMiddleMenu(false);
//          if (leftMenuFragment.isAdded()) {
//            Toast.makeText(getApplicationContext(), "Fragment added ", Toast.LENGTH_SHORT).show();
//          } else {
//            Log.e(TAG, "Add Left Menu");
//            mLeftMenuFl.setVisibility(View.VISIBLE);
//            getSupportFragmentManager().beginTransaction()
//                .add(R.id.fl_menu, leftMenuFragment, LEFT_MENU)
////                .setCustomAnimations(R.anim.left_to_right, R.anim.right_to_left)
////                .show(leftMenuFragment)
////                .addToBackStack(null)
//                .commit();
//          }
        }
      }
    });

    parentContainer.setOnFocusSearchListener(new OnFocusSearchListener() {
      @Override
      public View onFocusSearch(View focused, int direction) {
        if(focused.getId() == R.id.itemIconMenu && direction == View.FOCUS_RIGHT){
          toggleMiddleMenu(true);
          return leftMenuFragment.getSelectedView();
        }
        return null;  // return cai rows ma da focus
      }
    });
    iconMenuFragment.setSelectedPosition(0);
  }

  private void toggleMiddleMenu(boolean show) {
    final int currentMargin = ((ViewGroup.MarginLayoutParams) mLeftMenuFl.getLayoutParams()).rightMargin;
    final int slideDestination = show ? 0:getResources().getDimensionPixelSize(R.dimen.left_menu_margin_right);
    final int slideDelta = slideDestination - currentMargin;

    Animation toggleAnimation = new Animation() {
      @Override
      protected void applyTransformation(float interpolatedTime, Transformation t) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) mLeftMenuFl.getLayoutParams();
        layoutParams.rightMargin = (int) (currentMargin + slideDelta * interpolatedTime);
        mLeftMenuFl.setLayoutParams(layoutParams);
      }
    };
    toggleAnimation.setDuration(300);
    parentContainer.startAnimation(toggleAnimation);
  }

}
