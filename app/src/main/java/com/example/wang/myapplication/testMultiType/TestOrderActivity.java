/*
 * Copyright 2016 drakeet. https://github.com/drakeet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.wang.myapplication.testMultiType;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.wang.myapplication.R;


import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class TestOrderActivity extends Activity {


    private SwipeRefreshLayout swipeRefreshLayout;
    private MultiTypeAdapter adapter;
    private Items items;
    private String urlHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
        adapter = new MultiTypeAdapter();
        recyclerView.setAdapter(adapter);

        // adapter.register(OrderItem.class, new OrderItemViewBinder());
        adapter.register(RankItem.class, new RankItemViewBinder());

        urlHead = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=302701032,2300144492&fm=27&gp=0.jpg";
        items = new Items();
        for (int i = 0; i < 10; i++) {
            // items.add(new OrderItem(urlHead, "付费课程包", "内含10本书", "2017.01.01-2019.01.01", "初中", "已购买"));
            items.add(new RankItem(i, urlHead, "xiao", "2017.08.30 17:25:30", "100", "98"));
        }
        adapter.setItems(items);
        adapter.notifyDataSetChanged();


        // 设置颜色属性的时候一定要注意是引用了资源文件还是直接设置16进制的颜色，因为都是int值容易搞混
        // 设置下拉进度的背景颜色，默认就是白色的
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);

        swipeRefreshLayout.setOnRefreshListener((new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 开始刷新，设置当前为刷新状态
                //swipeRefreshLayout.setRefreshing(true);

                // 这里是主线程
                // 一些比较耗时的操作，比如联网获取数据，需要放到子线程去执行
                // TODO 获取数据
                urlHead = "http://pic1.cxtuku.com/00/01/78/b374df6c27cb.jpg";
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        //items.add(new HeavyItem("我是天才" + random.nextInt(100) + "号"));
//                        items.clear();
//                        for (int i = 0; i < 10; i++) {
//                            items.add(new OrderItem(urlHead, "付费课程包", "内含11本书", "2018.01.01-2019.01.01", "初中、高中", "赠送"));
//                        }
//                        adapter.notifyDataSetChanged();
//
//                        Toast.makeText(TestOrderActivity.this, "刷新了数据", Toast.LENGTH_SHORT).show();
//
//                        // 加载完数据设置为不刷新状态，将下拉进度收起来
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 1200);

                // System.out.println(Thread.currentThread().getName());

                // 这个不能写在外边，不然会直接收起来
                //swipeRefreshLayout.setRefreshing(false);


            }
        }));


        Toast.makeText(this, "Try to click or long click items", Toast.LENGTH_SHORT).show();
    }


}
