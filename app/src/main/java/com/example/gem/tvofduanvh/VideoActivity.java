package com.example.gem.tvofduanvh;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends Activity {

    @BindView(R.id.vv_play_video)
    VideoView mVideoVv;


//    MediaController mediaController = new MediaController(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_layout);

        ButterKnife.bind(this);

        MediaController controller = new MediaController(this);
        mVideoVv.setVideoPath("https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4");
        mVideoVv.setMediaController(controller);
        mVideoVv.start();
    }
}
