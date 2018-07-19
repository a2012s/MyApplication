package com.example.wang.myapplication.testWords;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wang.myapplication.R;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * A simple {@link Fragment} subclass.
 */

public class FragmentPre extends BaseFragment {


    private TextView mTextViewWord;


    @Override
    protected int setView() {
        return R.layout.fragment_words_ll;
    }

    @Override
    protected void init(View view) {
        mTextViewWord = view.findViewById(R.id.text_word);
        mTextViewWord.setText("fragmentPre1");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }


//此方法在Fragment中

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //Fragment隐藏时调用
            //Toast.makeText(getActivity(), "隐藏fragment 1", Toast.LENGTH_SHORT).show();
        } else {
            //Fragment显示时调用
            //mTextView.setText("显示fragmentPre" + num);
        }

    }


    /**
     * 从发布者那里得到eventbus传送过来的数据
     * <p>
     * 加上@Subscribe以防报错：its super classes have no public methods with the @Subscribe annotation
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        mTextViewWord.setText("fragmentPre" + event.num);
    }
}
