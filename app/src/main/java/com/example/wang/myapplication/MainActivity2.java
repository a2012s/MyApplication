package com.example.wang.myapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.wang.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main2);
        ArrayList<String> datas=new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            datas.add("SeeMyGo "+i);
        }
        //以下代码绑定了2个数据
        binding.setVariable(BR.list,datas);
        binding.setVariable(BR.index,2);

    }


}
