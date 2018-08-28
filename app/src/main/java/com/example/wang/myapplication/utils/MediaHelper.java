package com.example.wang.myapplication.utils;

/**
 * Created by wjj on 2018/8/17 17:24
 * E-Mail ：wjj99@qq.com
 * 描述：
 */
import java.io.IOException;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.util.Log;

/**
 * @描述 声音控制类
 * @项目名称 App_imooc
 * @包名 com.android.imooc.chat
 * @类名 MediaHelper
 * @author chenlin
 * @date 2013年6月17日 下午10:46:01
 * @version 1.0
 */

public class MediaHelper {
    private static MediaPlayer mPlayer;
    private static boolean isPause = false;

    public static void playSound(String filePath, OnCompletionListener listener) {
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
        } else {
            mPlayer.reset();
        }
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setOnCompletionListener(listener);
        mPlayer.setOnErrorListener(new OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mPlayer.reset();
                return false;
            }
        });
        try {
            mPlayer.setDataSource(filePath);
            mPlayer.prepare();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("logcat",e.getMessage());
           // throw new RuntimeException("读取文件异常：" + e.getMessage());
        }
        mPlayer.start();
        isPause = false;
    }

    public static void pause() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
            isPause = true;
        }
    }

    // 继续
    public static void resume() {
        if (mPlayer != null && isPause) {
            mPlayer.start();
            isPause = false;
        }
    }

    public static void release() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.reset();
            mPlayer.release();
            mPlayer = null;
        }
    }

}