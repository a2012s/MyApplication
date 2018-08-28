package com.example.wang.myapplication.player;


import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import android.widget.TextView;

import com.danikula.videocache.HttpProxyCacheServer;
import com.example.wang.myapplication.R;
import com.example.wang.myapplication.utils.MediaHelper;

import java.io.IOException;

/**
 * prepare方法是将资源同步缓存到内存中,一般加载本地较小的资源可以用这个,如果是较大的资源或者网络资源建议使用prepareAsync方法,异步加载.
 * 但如果想让资源启动,即start()起来,因为在异步中,如果不设置监听直接start的话,是拿不到这个资源,如果让线程睡眠一段时间,则可以取得资源,
 * 因为这个时候,异步线程已经取得资源,但不可能使用线程睡眠的方式来获取资源啊.
 * 所以就需要设置监听事件setOnPreparedListener();来通知MediaPlayer资源已经获取到了,
 * 然后实现onPrepared(MediaPlayer mp)方法.在里面启动MediaPlayer.
 */
public class MediaPlayerTest extends Activity implements MediaPlayer.OnPreparedListener {

    private Button streamButton;
    private ImageButton playButton;
    private boolean isPlaying;
    private TextView playTime;
    private StreamingMediaPlayer audioStreamer;

    //  private Uri uri;

    private MediaPlayer mediaPlayer;

    String VIDEO_URL = "http://www.ytmp3.cn/down/51164.mp3";//"http://langyinedu.oss-cn-shenzhen.aliyuncs.com/sbt/subject/audio/20180723/193714.mpga";

    @Override
    public void onCreate(Bundle icicle) {

        super.onCreate(icicle);

        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.main_player2);


        // HttpProxyCacheServer proxy = getProxy();

        // Log.e("logcat", "是否已经缓存VIDEO_URL=" + proxy.isCached(VIDEO_URL));
        // VIDEO_URL = proxy.getProxyUrl(VIDEO_URL);


        //  uri = Uri.parse(VIDEO_URL);

        findViewById(R.id.bt_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //通过同步的方式装载媒体资源
                MediaHelper.playSound(VIDEO_URL, new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {

                    }
                });

            }
        });

        findViewById(R.id.bt_play_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //通过异步的方式装载媒体资源
                yibu();
            }
        });

        findViewById(R.id.bt_play_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaHelper.release();
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.release();
                    mediaPlayer = null;

                }
            }
        });


    }

    private void yibu() {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(VIDEO_URL);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(this);
        } catch (IOException e) {
            Log.e("logcat", e.getMessage());
            e.printStackTrace();
        }

    }


    @Override
    public void onBackPressed() {
        MediaHelper.release();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();

        }
        finish();
        super.onBackPressed();
    }

    private HttpProxyCacheServer getProxy() {
        return new HttpProxyCacheServer(this);
        // should return single instance of HttpProxyCacheServer shared for whole app.
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

        Log.e("logcat", "onPrepared");
        mediaPlayer.start();

    }

//    private void initControls() {
//        playTime = (TextView) findViewById(R.id.playTime);
//        streamButton = (Button) findViewById(R.id.button_stream);
//
//        streamButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                startStreamingAudio();
//            }
//        });
//
//        playButton = (ImageButton) findViewById(R.id.button_play);
//        playButton.setEnabled(false);
//        playButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                if (audioStreamer.getMediaPlayer().isPlaying()) {
//                    audioStreamer.getMediaPlayer().pause();
//                    playButton.setImageResource(R.drawable.button_play);
//                } else {
//                    audioStreamer.getMediaPlayer().start();
//                    audioStreamer.startPlayProgressUpdater();
//                    playButton.setImageResource(R.drawable.button_pause);
//                }
//                isPlaying = !isPlaying;
//            }
//        });
//    }

//    private void startStreamingAudio() {
//        try {
//            final SeekBar progressBar = (SeekBar) findViewById(R.id.progress_bar);
//            if (audioStreamer != null) {
//                audioStreamer.interrupt();
//            }
//            audioStreamer = new StreamingMediaPlayer(this, playButton, streamButton, progressBar, playTime);
//            // 设置歌曲的大小 K，时长S
////            audioStreamer
////                    .startStreaming(
////                            "http://dl.stream.qqmusic.qq.com/C40000481cWs2JgWe0.m4a?vkey=D1DE97D387819D8E193EABDEDD484E3BF5F80C8A4DD6EA7AA6BD17FDBB9CBB41659FA3F8E618A8909F14C762C7EA0683A40F8C32D0ACEF5B&guid=ffffffff8afc2b3d93639dff5d33dfdf&continfo=C888DD27CEFF5A7B252810D5ADA054FB165851DEC5080732&uin=4611687117939015704&fromtag=100",
////                            2772, 240);
//            audioStreamer
//                    .startStreaming(url, 5164, 35);
//            // audioStreamer
//            // .startStreaming(
//            // "http://dl.stream.qqmusic.qq.com/F0000027zPYs3A9fyb.flac?vkey=AF67176C6455629061C5B4404528CF8329B79EB429D8E18E019E31B341BCCFA40C253BB04E73151BC0C7E3C50FCE259098A7655307AF07C2&guid=ffffffffc0e6fbd693639dff5173dd97&continfo=CD3C5C32CC9693D136D03850A6AD82302244173175B63981&uin=4611687117939015688&fromtag=100",
//            // 26840, 242);
//
//            streamButton.setEnabled(false);
//        } catch (IOException e) {
//            Log.e(getClass().getName(), "Error starting to stream audio.", e);
//        }
//
//    }
}
