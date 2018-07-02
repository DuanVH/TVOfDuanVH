package com.example.gem.tvofduanvh;

import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.app.HeadersSupportFragment;
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

import com.squareup.picasso.Picasso;

import java.lang.reflect.Method;

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
  private BackgroundManager mBackgroundManager = null;
  private PicassoBackgroundManager picassoBackgroundManager = null;

  private IconMenuFragment iconMenuFragment;

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

    picassoBackgroundManager = new PicassoBackgroundManager(this);

    setupMenu();
  }

  public void requestFocusIcon() {
    iconMenuFragment.getSelectedView().requestFocus();
  }

  private void setupMenu() {
    final LeftMenuFragment leftMenuFragment = new LeftMenuFragment();
    iconMenuFragment = new IconMenuFragment();
    final ContentFragment contentFragment = new ContentFragment();


    getSupportFragmentManager().beginTransaction()
        .add(R.id.fl_menu, leftMenuFragment, LEFT_MENU)
        .commit();

    getSupportFragmentManager().beginTransaction()
        .add(R.id.fl_icon_menu, iconMenuFragment, ICON_MENU)
        .commit();

    getSupportFragmentManager().beginTransaction()
        .add(R.id.fl_content, contentFragment, CONTENT)
        .commit();

    iconMenuFragment.setListener(new IconMenuFragment.OnMenuItemClickListener() {

      @Override
      public void onIconMenuItemClicked(long id) {
        Log.e(TAG, "onLeftMenuItemClicked: " + id);
      }
    });

    leftMenuFragment.setListener(new LeftMenuFragment.OnMenuItemClickListener() {

      @Override
      public void onLeftMenuItemClicked(long id) {
        Log.e(TAG, "onLeftMenuItemClicked: " + id);
        switch ((int) id) {

          case 0:
//            picassoBackgroundManager.updateBackgroundWithDelay("http://i0.kym-cdn.com/photos/images/original/000/693/750/f61.jpg");
            break;

          case 1:
//            picassoBackgroundManager.updateBackgroundWithDelay("https://www.animuk.co.uk/images/watermarked/1/detailed/14/One_Piece_-_FILM_GOLD_Character_Poster_Collection.jpg?t=1471128535");
            break;

          case 2:
//            picassoBackgroundManager.updateBackgroundWithDelay("http://i0.kym-cdn.com/photos/images/original/000/693/750/f61.jpg");
            break;

          case 3:
//            picassoBackgroundManager.updateBackgroundWithDelay("https://www.animuk.co.uk/images/watermarked/1/detailed/14/One_Piece_-_FILM_GOLD_Character_Poster_Collection.jpg?t=1471128535");
            break;

          case 4:
//            picassoBackgroundManager.updateBackgroundWithDelay("http://i0.kym-cdn.com/photos/images/original/000/693/750/f61.jpg");
            break;

          default:
            break;
        }
      }
    });

    contentFragment.setListener(new ContentFragment.OnContentListener() {

      @Override
      public void onContentListener(long id) {
        Log.e(TAG, "onContentListener: " + id);
      }
    });

    parentContainer.setOnChildFocusListener(new OnChildFocusListener() {
      @Override
      public boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        return false;
      }

      @Override
      public void onRequestChildFocus(View child, View focused) {
//        if (child.getId() == R.id.fl_content) {
//          toggleMiddleMenu(true);
//          FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//          LeftMenuFragment fragment = (LeftMenuFragment) getSupportFragmentManager().findFragmentByTag(LEFT_MENU);
//          if (fragment != null) {
//            Log.e(TAG, "Hide Left Menu");
//            fragmentTransaction.remove(fragment);
//            fragmentTransaction.commit();
//            mLeftMenuFl.setVisibility(View.GONE);
//          }
//        } else
        if (child.getId() == R.id.fl_icon_menu) {
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
        if (focused.getId() == R.id.itemIconMenu && direction == View.FOCUS_RIGHT) {
          toggleMiddleMenu(true);
          return leftMenuFragment.getSelectedView();
        } else if (focused.getId() == R.id.itemContent && direction == View.FOCUS_LEFT) {
          toggleMiddleMenu(true);
          return leftMenuFragment.getSelectedView();
        }
        return null;
      }
    });

  }


  private void toggleMiddleMenu(boolean show) {
    final int currentMargin = ((ViewGroup.MarginLayoutParams) mLeftMenuFl.getLayoutParams()).rightMargin;
    final int slideDestination = show ? 0 : getResources().getDimensionPixelSize(R.dimen.left_menu_margin_right);
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
