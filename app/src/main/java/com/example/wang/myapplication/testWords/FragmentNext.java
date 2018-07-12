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
public class FragmentNext extends BaseFragment {


    private TextView mTextView;


    @Override
    protected int setView() {
        return R.layout.fragment_words_ll;
    }

    @Override
    protected void init(View view) {
        mTextView = view.findViewById(R.id.text_word);

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
            Toast.makeText(getActivity(), "隐藏fragment 3", Toast.LENGTH_SHORT).show();
        } else {
            //Fragment显示时调用
            //mTextView.setText("显示fragmentNext" );
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        mTextView.setText("onMessageEvent显示fragmentPre" + event.num);

    }
}
