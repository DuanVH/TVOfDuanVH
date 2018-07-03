package com.example.gem.tvofduanvh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gem on 7/2/18.
 */

public class VideoActivity extends Activity {

  @BindView(R.id.tv_title_video)
  TextView mTvTitleVideo;

  @BindView(R.id.iv_image_video)
  ImageView mIvImageVideo;


  @BindView(R.id.vv_play_video)
  VideoView mVv;


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.youtube_view_layout);
    ButterKnife.bind(this);

    Intent intent = getIntent();
    Bundle bundle = intent.getBundleExtra(MainActivity.BUNDLE);
    mTvTitleVideo.setText(bundle.getString(MainActivity.TITLE));
    Picasso.with(this).load(bundle.getString(MainActivity.IMAGE)).fit().into(mIvImageVideo);

    MediaController mediaController = new MediaController(this);
    mVv.setVideoPath(bundle.getString(MainActivity.VIDEO_URL));
    mVv.setMediaController(mediaController);
    mVv.start();
  }
}
