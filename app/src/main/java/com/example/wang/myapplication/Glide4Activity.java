package com.example.wang.myapplication;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wang.myapplication.databinding.ActivityMainBinding;
import com.example.wang.myapplication.utils.GlideUtils;

public class Glide4Activity extends AppCompatActivity {

    //Data Binding为我们生成了databinding包，以及ActivityMainBinding类
    private ActivityMainBinding binding;
    private User user;
    private int n = 0;
    private String URL = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4138850978,2612460506&fm=200&gp=0.jpg";

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_glide);
        context = Glide4Activity.this;
        ImageView iv = findViewById(R.id.iv_test);
        ImageView iv2 = findViewById(R.id.iv_test2);
        ImageView iv3 = findViewById(R.id.iv_test3);
        ImageView iv4 = findViewById(R.id.iv_test4);

        GlideUtils.loadImage(context,URL,iv);
        GlideUtils.loadBlurImage(context,URL,iv2,50);
        GlideUtils.loadRoundCircleImage(context,URL,iv3);
        GlideUtils.loadCircleImage(context,URL,iv4);

    }


}
