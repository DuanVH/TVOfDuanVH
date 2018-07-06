package com.example.gem.tvofduanvh;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

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

  public static final String BUNDLE = "bundle";
  public static final String TITLE = "title";
  public static final String IMAGE = "image";
  public static final String VIDEO_URL = "video_url";

  private FragmentManager fragmentManager = getSupportFragmentManager();
  private PicassoBackgroundManager picassoBackgroundManager = null;

  private IconMenuFragment iconMenuFragment;

  private static final int RC_SIGN_IN = 1;
  private GoogleSignInClient mGoogleSignInClient;


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

    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
  }

  @Override
  protected void onStart() {
    super.onStart();
    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
     updateUI(account);
  }

  private void updateUI(GoogleSignInAccount account) {
    if (account != null) {
      // set image
      iconMenuFragment.updateAvatar(account.getPhotoUrl());
    } else
      Log.e(TAG, "updateUI: account null"  );
  }

  private void signIn() {
    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
    startActivityForResult(signInIntent, RC_SIGN_IN);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == RC_SIGN_IN) {
      Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
      handleSignInResult(task);
    }
  }

  private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
    try {
      GoogleSignInAccount account = completedTask.getResult(ApiException.class);

      // Signed in successfully, show authenticated UI.
      updateUI(account);
    } catch (ApiException e) {
      // The ApiException status code indicates the detailed failure reason.
      // Please refer to the GoogleSignInStatusCodes class reference for more information.
      Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
      updateUI(null);
    }
  }

  /*
    Muốn selected vào 1 item thì đầu tiên phải focus vào list chứa nó trước
    Focus >> Seleced >> Clicked
     */
  public void requestFocusIcon() {
    iconMenuFragment.getSelectedView().requestFocus();
    iconMenuFragment.setSelectedPosition(2);
  }

  private void setupMenu() {
    iconMenuFragment = new IconMenuFragment();
    final LeftMenuFragment leftMenuFragment = new LeftMenuFragment();
    final ContentFragment contentFragment = new ContentFragment();

    getSupportFragmentManager().beginTransaction()
        .add(R.id.fl_icon_menu, iconMenuFragment, ICON_MENU)
        .commit();

    getSupportFragmentManager().beginTransaction()
        .add(R.id.fl_menu, leftMenuFragment, LEFT_MENU)
        .commit();

    getSupportFragmentManager().beginTransaction()
        .add(R.id.fl_content, contentFragment, CONTENT)
        .commit();

    iconMenuFragment.setListener(new IconMenuFragment.OnMenuItemClickListener() {

      @Override
      public void onIconMenuItemClicked(long id) {
        switch ((int) id) {

          // search
          case 1:

            break;

          // home
          case 3:

            break;

          case 4:
            break;

          case 5:
            break;

          // account
          case 7:
            signIn();
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            break;

          // settings
          case 8:
            break;

          default:
            break;
        }
      }
    });

    leftMenuFragment.setListener(new LeftMenuFragment.OnMenuItemClickListener() {

      @Override
      public void onLeftMenuItemClicked(long id) {
        Log.e(TAG, "onLeftMenuItemClicked: " + id);
        switch ((int) id) {

          // Home
          case 0:
            contentFragment.showData();
            break;

          // Suggest
          case 1:
            contentFragment.showNewData();
            break;

          // Hot trend
          case 2:
            contentFragment.showData();
            break;

          // Music
          case 3:
            contentFragment.showNewData();
            break;

          // Entertainment
          case 4:
            contentFragment.showData();
            break;

          default:
            break;
        }
      }
    });

    contentFragment.setListener(new ContentFragment.OnContentListener() {
      @Override
      public void onContentSelectedListener(ContentRightItem item) {
        if (item != null) {
//          picassoBackgroundManager.updateBackgroundWithDelay(item.getImageUrl());
        }
      }

      @Override
      public void onContentClickedListener(ContentRightItem item) {
        if (item != null) {
          Intent intent = new Intent(MainActivity.this, VideoActivity.class);
          Bundle bundle = new Bundle();
          bundle.putString(TITLE, item.getTitle());
          bundle.putString(IMAGE, item.getImageUrl());
          bundle.putString(VIDEO_URL, item.getVideoUrl());
          intent.putExtra(BUNDLE, bundle);
          startActivity(intent);
        }
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
        } else if (focused.getId() == R.id.itemIconMenu && (int) focused.getTag() == R.drawable.icon_search && direction == View.FOCUS_UP) {
          return focused;
        } else if (focused.getId() == R.id.itemIconMenu && (int) focused.getTag() == R.drawable.icon_setting && direction == View.FOCUS_DOWN) {
          return focused;
        }
        return null;
      }
    });

  }


  private void toggleMiddleMenu(boolean show) {
    final int currentMargin = ((ViewGroup.MarginLayoutParams) mLeftMenuFl.getLayoutParams()).rightMargin;
    final int slideDestination = show ? 0 : getResources().getDimensionPixelSize(R.dimen.left_menu_margin_right);
    final int slideDelta = slideDestination - currentMargin;

    Log.e(TAG, "currentMargin: " + currentMargin);
    Log.e(TAG, "slideDestination: " + slideDestination);
    Log.e(TAG, "slideDelta: " + slideDelta);

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
