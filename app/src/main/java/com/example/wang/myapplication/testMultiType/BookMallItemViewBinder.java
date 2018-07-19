package com.example.wang.myapplication.testMultiType;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wang.myapplication.R;
import com.example.wang.myapplication.utils.GlideUtils;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by wjj on 2018/7/11 15:21
 * E-Mail ：wjj99@qq.com
 * 描述：
 */
public class BookMallItemViewBinder extends ItemViewBinder<BookMallItem, BookMallItemViewBinder.TextHolder> {

    static class TextHolder extends RecyclerView.ViewHolder {


        private @NonNull
        final ImageView iv_head;
        private @NonNull
        final TextView tv_name;
        private @NonNull
        final TextView tv_detail;

        private @NonNull
        final TextView tv_apply_detail;


        TextHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_head = itemView.findViewById(R.id.iv_head);
            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_detail = itemView.findViewById(R.id.tv_detail);
            this.tv_apply_detail = itemView.findViewById(R.id.tv_apply_detail);


        }
    }

    @NonNull
    @Override
    protected TextHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.bookmall_item, parent, false);
        return new TextHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull TextHolder holder, @NonNull BookMallItem item) {

        holder.tv_name.setText(item.name);
        holder.tv_detail.setText(item.details);
        holder.tv_apply_detail.setText(item.apply);
        GlideUtils.loadRoundCircleImage(holder.iv_head.getContext(), item.url, holder.iv_head);

        Log.d("demo", "position: " + getPosition(holder));
        Log.d("demo", "adapter: " + getAdapter());
    }
}
