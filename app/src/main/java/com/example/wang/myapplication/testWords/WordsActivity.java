package com.example.wang.myapplication.testWords;
/*************************************************
 * 单词页面
 * **********************************************************/

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.wang.myapplication.R;

import org.greenrobot.eventbus.EventBus;


public class WordsActivity extends AppCompatActivity {

    private Fragment currentFragment = new Fragment();

    private int iNow = 0;
    private int num = 1;
    private int wordsCount = 10;
    private static final int LEFT = 1;
    private static final int MID = 2;
    private static final int RIGHT = 3;//right

    private ProgressBar mProgressBarHorizontal;


    private AppCompatImageView iv_next, iv_replay, iv_play;

    private FragmentPre first = new FragmentPre();
    private FragmentNext third = new FragmentNext();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        initView();

        switchFragment(first, 0).commit();
    }

    private void initView() {
        iv_next = findViewById(R.id.iv_next);
        mProgressBarHorizontal = findViewById(R.id.pb_w);
        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (num >= wordsCount) {
                    Toast.makeText(WordsActivity.this, "完成了", Toast.LENGTH_SHORT).show();
                } else {
                    switchFragment(third, RIGHT).commit();
                }
            }
        });
        iv_replay = findViewById(R.id.iv_replay);
        iv_play = findViewById(R.id.iv_play);

        mProgressBarHorizontal.setMax(wordsCount);


        iv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_play.setImageResource(R.drawable.ic_pause);
            }
        });
    }

    //Fragment优化
    public FragmentTransaction switchFragment(Fragment targetFragment, int i) {

        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (i == iNow && i != RIGHT) {
            //doNothing
            Log.e("logcat", "i==i==" + i);

        } else if (i < iNow) {
            Log.e("logcat", "i < ii;i==" + i);
            transaction.setCustomAnimations(
                    R.anim.slide_left_in,
                    R.anim.slide_right_out,
                    R.anim.slide_right_in,
                    R.anim.slide_left_out

            );
        } else {
            Log.e("logcat", "i >= ii;i==" + i);
            transaction.setCustomAnimations(
                    R.anim.slide_right_in,
                    R.anim.slide_left_out,
                    R.anim.slide_left_in,
                    R.anim.slide_right_out

            );
        }

        if (i == 1) {
            if (num > 1) {
                num--;
            }
        } else if (i == 3) {
            num++;
        }


        EventBus.getDefault().post(new MessageEvent(num));
        mProgressBarHorizontal.setProgress(num);

        iNow = i;


        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.fragment, targetFragment, targetFragment.getClass().getName());

        } else {
            transaction.hide(currentFragment).show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }


}
