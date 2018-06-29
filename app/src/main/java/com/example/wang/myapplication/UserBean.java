package com.example.wang.myapplication;

import android.databinding.ObservableDouble;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;

/**
 * 作者：wjj99@qq.com
 * 时间： 2018/6/29 10:16
 * <p>
 * 描述：
 */
public class UserBean {

    public ObservableInt userId = new ObservableInt();
    public ObservableField<String> userName = new ObservableField<>();
    public ObservableDouble userAge = new ObservableDouble();
    public ObservableFloat userSex = new ObservableFloat();
}
