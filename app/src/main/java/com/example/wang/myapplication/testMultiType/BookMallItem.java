package com.example.wang.myapplication.testMultiType;

import android.support.annotation.NonNull;

/**
 * Created by wjj on 2018/7/11 15:13
 * E-Mail ：wjj99@qq.com
 * 描述：购书商城
 */
public class BookMallItem {

    public final @NonNull
    String url;
    public final @NonNull
    String name;
    public final @NonNull
    String details;
    public final @NonNull
    String apply;


    public BookMallItem(@NonNull String url, @NonNull String name,
                        @NonNull String details, @NonNull String apply) {
        this.url = url;
        this.name = name;
        this.apply = apply;
        this.details = details;

    }
}
