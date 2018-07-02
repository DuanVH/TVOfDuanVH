package com.example.gem.tvofduanvh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YoutubePlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView mYouTubePlayerView;
    private VideoView mVideoVv;

    private YouTubePlayer YPlayer;
    private static final String YoutubeDeveloperKey = "AIzaSyDVMdxp2lnNLr-6nbxahfYoq5Viu-IlI1w";
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_view_layout);
//        mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_video_player);
        mYouTubePlayerView.initialize(YoutubeDeveloperKey, this);

        mVideoVv = (VideoView) findViewById(R.id.vv_play_video);
        MediaController controller = new MediaController(this);
        mVideoVv.setVideoPath("https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4");
        mVideoVv.setMediaController(controller);
        mVideoVv.start();

    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        YPlayer = youTubePlayer;
        if (!b) {
            YPlayer.cueVideo("C3KzANL6gTY");
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {

        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format("There was an error initializing the YouTubePlayer",
                errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            getYouTubePlayerProvider().initialize(YoutubeDeveloperKey, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
//        return (YouTubePlayerView) findViewById(R.id.youtube_video_player);
        return mYouTubePlayerView;
    }
}
