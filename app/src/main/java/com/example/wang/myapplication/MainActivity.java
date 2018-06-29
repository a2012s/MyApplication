package com.example.wang.myapplication;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.wang.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //Data Binding为我们生成了databinding包，以及ActivityMainBinding类
    private ActivityMainBinding binding;
    private User user;
    private int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 获取<data />标签对象
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        user=new User();
        user.firstName.set("wang");
        user.lastName.set("btn");
        binding.setUser(user);

    }

    //参数View必须有，必须是public，参数View不能改成对应的控件，只能是View，否则编译不通过
    public void myClick(View view) {
        n++;
       // user.setFirstName("点击" + n);
        user.firstName.set("点击哈" + n);
    }


}
