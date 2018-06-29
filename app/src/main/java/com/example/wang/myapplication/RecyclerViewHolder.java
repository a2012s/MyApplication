package com.example.wang.myapplication;

import android.support.v7.widget.RecyclerView;

import com.example.wang.myapplication.databinding.ItemMvvmBinding;

/**
 * 作者：wjj99@qq.com
 * 时间： 2018/6/29 10:14
 * <p>
 * 描述：
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    //这里只要给RecyclerView.ViewHolder返回一个view就可以，所以我们将构造方法中传入databinding
    ItemMvvmBinding itemMvvmBinding;

    public RecyclerViewHolder(ItemMvvmBinding itemMvvmBinding) {
        super(itemMvvmBinding.getRoot());
        this.itemMvvmBinding = itemMvvmBinding;
    }

    public ItemMvvmBinding getBinding() {
        return itemMvvmBinding;
    }

    public void setBinding(ItemMvvmBinding itemMvvmBinding) {
        this.itemMvvmBinding = itemMvvmBinding;
    }
}
