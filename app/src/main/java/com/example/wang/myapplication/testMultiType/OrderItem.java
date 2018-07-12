package com.example.wang.myapplication.testMultiType;

import android.support.annotation.NonNull;

/**
 * Created by wjj on 2018/7/11 15:13
 * E-Mail ：wjj99@qq.com
 * 描述：我的订单页
 */
public class OrderItem {

    public final @NonNull
    String url;//图片
    public final @NonNull
    String name;//
    public final @NonNull
    String nameDetails;//
    public final @NonNull
    String time;//有效期
    public final @NonNull
    String apply;//适用
    public final @NonNull
    String cost;

    public OrderItem(@NonNull String url, @NonNull String name,
                     @NonNull String nameDetails, @NonNull String time, @NonNull String apply, @NonNull String cost) {
        this.url = url;
        this.name = name;
        this.apply = apply;
        this.nameDetails = nameDetails;
        this.time = time;
        this.cost = cost;
    }
}
