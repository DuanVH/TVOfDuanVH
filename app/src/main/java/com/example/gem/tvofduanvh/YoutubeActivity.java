package com.example.gem.tvofduanvh;

import android.net.Uri;
import android.os.Bundle;
import android.support.v17.leanback.app.VideoFragment;
import android.support.v17.leanback.app.VideoFragmentGlueHost;
import android.support.v17.leanback.media.MediaPlayerAdapter;
import android.support.v17.leanback.media.PlaybackGlue;
import android.support.v17.leanback.media.PlaybackTransportControlGlue;
import android.support.v17.leanback.widget.PlaybackSeekDataProvider;

/**
 * Created by gem on 7/3/18.
 */

public class YoutubeActivity extends VideoFragment {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final PlaybackTransportControlGlue<MediaPlayerAdapter> playerGlue =
        new PlaybackTransportControlGlue<>(getActivity(), new MediaPlayerAdapter(getActivity()));

    playerGlue.setHost(new VideoFragmentGlueHost(this));
    playerGlue.addPlayerCallback(new PlaybackGlue.PlayerCallback() {
      @Override
      public void onPreparedStateChanged(PlaybackGlue glue) {
        if (glue.isPrepared()) {
          playerGlue.setSeekProvider(new PlaybackSeekDataProvider());
          playerGlue.play();
        }
      }
    });

    playerGlue.setSubtitle("DuanVH");
    playerGlue.setTitle("Maboy");
    String uriPath = "android.resource://com.example.android.leanback/raw/video";
    playerGlue.getPlayerAdapter().setDataSource(Uri.parse(uriPath));
  }
}
