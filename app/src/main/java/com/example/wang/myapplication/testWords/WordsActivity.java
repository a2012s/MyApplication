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
import android.widget.TextView;
import android.widget.Toast;


import com.example.wang.myapplication.R;

import org.greenrobot.eventbus.EventBus;


public class WordsActivity extends AppCompatActivity {

    private Fragment currentFragment = new Fragment();

    private FragmentWordDetail wordDetailFragment = new FragmentWordDetail();

    private int iNow = 0;
    private int num = 1;
    private int wordsCount = 10;
    private static final int LEFT = 1;
    private static final int MID = 2;
    private static final int RIGHT = 3;//right

    private ProgressBar mProgressBarHorizontal;

    private TextView tv_left, tv_right;

    private AppCompatImageView iv_next, iv_left, iv_play;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        initView();

        switchFragment(wordDetailFragment, 0).commit();
    }

    private void initView() {
        iv_next = findViewById(R.id.iv_next);
        mProgressBarHorizontal = findViewById(R.id.pb_w);
        mProgressBarHorizontal.setMax(wordsCount);

        tv_left = findViewById(R.id.tv_left);
        tv_right = findViewById(R.id.tv_right);

        iv_left = findViewById(R.id.iv_left);
        iv_play = findViewById(R.id.iv_play);


        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num >= wordsCount) {
                    Toast.makeText(WordsActivity.this, "完成了", Toast.LENGTH_SHORT).show();
                } else {
                    tv_left.setText((num + 1) + "/" + wordsCount);
                    switchFragment(wordDetailFragment, RIGHT).commit();
                }
            }
        });


        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (num == 1) {
                    Toast.makeText(WordsActivity.this, "没有了", Toast.LENGTH_SHORT).show();
                } else {
                    tv_left.setText((num - 1) + "/" + wordsCount);
                    switchFragment(wordDetailFragment, LEFT).commit();
                }
            }
        });




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
        if (i == LEFT) {
            Log.e("logcat", "i < ii;i==" + i);
            if (num > 1) {
                num--;
            }
            transaction.setCustomAnimations(
                    R.anim.slide_left_in,
                    R.anim.slide_right_out,
                    R.anim.slide_right_in,
                    R.anim.slide_left_out

            );
        } else if (i == RIGHT) {
            num++;
            Log.e("logcat", "i >= ii;i==" + i);
            transaction.setCustomAnimations(
                    R.anim.slide_right_in,
                    R.anim.slide_left_out,
                    R.anim.slide_left_in,
                    R.anim.slide_right_out

            );
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
