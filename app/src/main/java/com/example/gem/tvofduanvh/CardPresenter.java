package com.example.gem.tvofduanvh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Util;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.net.URI;

/**
 * Created by gem on 6/26/18.
 */

public class CardPresenter extends Presenter {

  private static Context mContext;
  private static final int WIDTH = 300;
  private static final int HEIGHT = 200;

  @Override
  public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {

    mContext = parent.getContext();
    ImageCardView cardView = new ImageCardView(mContext);
    cardView.setFocusable(true);
    cardView.setFocusableInTouchMode(true);
    cardView.setBackgroundColor(mContext.getResources().getColor(R.color.colorBackground));

    return new ViewHolder(cardView);
  }

  @Override
  public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {

    Movie movie = (Movie) item;
    ((ViewHolder) viewHolder).setMoive(movie);

    ((ViewHolder) viewHolder).mImageCardView.setTitleText(movie.getTitle());
    ((ViewHolder) viewHolder).mImageCardView.setContentText(movie.getDescription());
    ((ViewHolder) viewHolder).mImageCardView.setMainImageDimensions(WIDTH, HEIGHT);

    if (movie.getUrl() != null) {
      ((ViewHolder) viewHolder).updateImageCardView(movie.getURI());
    }
  }

  @Override
  public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

  }

  static class ViewHolder extends Presenter.ViewHolder {

    private Movie mMoive;
    private ImageCardView mImageCardView;
    private Drawable mDrawable;
    private PicassoImageCardViewTarget mPicassoImageCardViewTarget;

    public ViewHolder(View view) {
      super(view);
      mImageCardView = (ImageCardView) view;
      mPicassoImageCardViewTarget = new PicassoImageCardViewTarget(mImageCardView);
      mDrawable = mContext.getResources().getDrawable(R.drawable.lb_ic_cc);
    }

    public void setMoive(Movie mMoive) {
      this.mMoive = mMoive;
    }

    public Movie getMoive() {
      return mMoive;
    }

    public ImageCardView getImageCardView() {
      return mImageCardView;
    }

    public Drawable getDrawable() {
      return mDrawable;
    }

    public void updateImageCardView(URI uri) {
      Picasso.with(mContext)
          .load(uri.toString())
          .resize(Utils.convertDpToPixel(mContext, WIDTH), Utils.convertDpToPixel(mContext, HEIGHT))
          .error(mDrawable)
          .into(mPicassoImageCardViewTarget);
    }
  }

  public static class PicassoImageCardViewTarget implements Target {

    private ImageCardView imageCardView;

    public PicassoImageCardViewTarget(ImageCardView imageCardView) {
      this.imageCardView = imageCardView;
    }


    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
      Drawable drawable = new BitmapDrawable(mContext.getResources(), bitmap);
      imageCardView.setMainImage(drawable);
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
      imageCardView.setMainImage(errorDrawable);
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }
  }
}
