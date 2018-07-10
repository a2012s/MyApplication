package com.example.wang.myapplication;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 *
 *
 * 通知UI更新的方法是用User类继承自BaseObservable，然后在getter上添加注解、在setter中添加notify方法，
 * 这感觉总是有点麻烦，步骤繁琐，于是，Google推出ObservableFields类，使用它我们可以简化我们的Model类。
 * ObservableField<T>中传入的泛型可以是Java中的基本类型，
 * 当然我们还可以使用 ObservableBoolean, ObservableByte, ObservableChar, ObservableShort, ObservableInt,
 * ObservableLong, ObservableFloat, ObservableDouble, ObservableParcelable等具体的类型，效果也和ObservableField<T>是一样的.
 * 如:
 *      public final ObservableField<Integer> userAge = new ObservableField<>();
 等同于 public final ObservableInt userAge = new ObservableInt();
 */
public class User  {
    public final ObservableField<String> firstName = new ObservableField<>();
    public final ObservableField<String> lastName = new ObservableField<>();
    public final ObservableField<String> headPic = new ObservableField<>();

}



