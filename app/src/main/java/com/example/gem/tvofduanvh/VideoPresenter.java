package com.example.gem.tvofduanvh;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gem on 6/28/18.
 */

public class VideoPresenter extends AbstractPresenter {

  public VideoPresenter(Context context, boolean isAlignFirstItem) {
    super(context, isAlignFirstItem);
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent) {
    return new ProgramItemHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_content, parent, false));
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, Object item) {

    final ProgramItemHolder holder = (ProgramItemHolder) viewHolder;

    ContentRightItem contentRightItem = (ContentRightItem) item;

    Picasso.with(getContext()).load(contentRightItem.getImageUrl()).fit().into(holder.ivContent);
//    holder.ivContent.setImageResource(R.mipmap.ic_launcher);
    holder.tvTitle.setText(contentRightItem.getTitle());

    String urlVideo = contentRightItem.getVideoUrl(); // your URL here
//    Uri videoUri = Uri.parse(urlVideo);
//    holder.videoView.setVideoURI(videoUri);
//    holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//      @Override
//      public void onPrepared(MediaPlayer mp) {
//        mp.setLooping(true);
//        holder.videoView.start();
//      }
//    });
  }



  @Override
  public void onUnbindViewHolder(ViewHolder viewHolder) {
  }

  class ProgramItemHolder extends ViewHolder {

    @BindView(R.id.iv_icon_content)
    ImageView ivContent;

    @BindView(R.id.tv_title_content)
    TextView tvTitle;

    public ProgramItemHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
