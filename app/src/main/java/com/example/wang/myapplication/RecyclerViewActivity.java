package com.example.wang.myapplication;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.wang.myapplication.databinding.ActivityRecyclerViewBinding;

import java.util.ArrayList;

/**
 * 作者：wjj99@qq.com
 * 时间： 2018/6/29 10:13
 * <p>
 * 描述：
 */
public class RecyclerViewActivity extends Activity {

    ActivityRecyclerViewBinding recyclerViewBinding;
    ArrayList<UserBean> data;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_view);
        data = initData();
        //需要设置recyclerview的layoutmanager，不然视图无法显示
        //第二个参数表示水平布局，第三个参数表示是否反转,视图从下往上滑
        //new GridLayoutManager(this,3,GridLayoutManager.HORIZONTAL,false)); //表格布局
        //new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, true));
        //new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));//瀑布流
        recyclerViewBinding.recyclerTest.setLayoutManager(new LinearLayoutManager(this, GridLayoutManager.VERTICAL, false));


        //初始化适配器
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(data);
        //绑定适配器
        recyclerViewBinding.setAdapter(new RecyclerViewAdapter(data));
        //item的点击事件
        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecyclerViewActivity.this, "行数：" + position, Toast.LENGTH_LONG).show();
            }
        });
        //通过属性设置view的背景色
        recyclerViewBinding.setImage(getResources().getColor(R.color.colorAccent));
    }

    //初始化测试数据
    private ArrayList<UserBean> initData() {
        ArrayList<UserBean> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            UserBean userBean = new UserBean();
            userBean.userId.set(i);
            userBean.userName.set(i + "aaa");
            userBean.userAge.set(18 + i);
            userBean.userSex.set(i % 2 == 0 ? 1 : 0);
            list.add(userBean);
        }
        return list;
    }
}