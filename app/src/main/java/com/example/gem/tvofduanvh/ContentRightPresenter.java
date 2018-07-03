package com.example.gem.tvofduanvh;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gem on 6/28/18.
 */

public class ContentRightPresenter extends AbstractPresenter {

  private static final String TAG = ContentRightPresenter.class.getSimpleName();
  OnContentListener listener;

  public ContentRightPresenter(Context context, boolean isAlignFirstItem) {
    super(context, isAlignFirstItem);
  }

  public void setListener(OnContentListener listener) {
    this.listener = listener;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent) {
    return new ProgramItemHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_content, parent, false));
  }

  @Override
  public void onBindViewHolder(final ViewHolder viewHolder, final Object item) {

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
    holder.view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Log.e(TAG, "onClick: ");
      }
    });
  }


  @Override
  public void onUnbindViewHolder(ViewHolder viewHolder) {
  }

  class ProgramItemHolder extends ViewHolder {

    @BindView(R.id.iv_icon_content)
    ImageView ivContent;

    @BindView(R.id.tv_title_content)
    TextView tvTitle;

    public String getTitle() {
      return tvTitle.getText().toString();
    }

    public ProgramItemHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }

  public interface OnContentListener {
    void onContentListener(String title);
  }

}
