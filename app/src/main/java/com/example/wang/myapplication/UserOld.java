package com.example.wang.myapplication;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * 作者：wjj99@qq.com
 * 时间： 2018/6/14 17:20
 * <p>
 * 描述：
 * Observable是个接口，Google为我们提供了一个BaseObservable类，我们只要把Model类继承自它就获得了通知UI更新数据的能力了.
 * <p>
 * getter方法都添加了 注解 @Bindable，它支持该属性在BR类中产生一个对应的静态int类型常量,
 * setter方法中多了一句notifyPropertyChanged(BR.xxx)提醒UI更新数据。
 * 至于什么是BR，可以理解为它类似于我们认识的R类.
 * Bindable注解会自动生成一个BR类，该类位于app module包下，通过BR类我们设置更新的数据，
 * 当Model中的数据发生变化时，setter方法中的notifyPropertyChanged()就会通知UI更新数据了。
 */
public class UserOld extends BaseObservable {

    private String firstName;
    private String lastName;





    public UserOld(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Bindable
    public String getFirstName() {
        return this.firstName;
    }

    @Bindable
    public String getLastName() {
        return this.lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);

    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);

    }


}



