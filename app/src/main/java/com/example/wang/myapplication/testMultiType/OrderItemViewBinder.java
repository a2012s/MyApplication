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
public class OrderItemViewBinder extends ItemViewBinder<OrderItem, OrderItemViewBinder.TextHolder> {

    static class TextHolder extends RecyclerView.ViewHolder {


        private @NonNull
        final ImageView iv_head;

        private @NonNull
        final TextView tv_name;
        private @NonNull
        final TextView tv_detail;
        private @NonNull
        final TextView tv_cost;
        private @NonNull
        final TextView tv_apply_detail;
        private @NonNull
        final TextView tv_time_detail;

        TextHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_head = itemView.findViewById(R.id.iv_head);
            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_detail = itemView.findViewById(R.id.tv_detail);
            this.tv_cost = itemView.findViewById(R.id.tv_cost);
            this.tv_apply_detail = itemView.findViewById(R.id.tv_apply_detail);
            this.tv_time_detail = itemView.findViewById(R.id.tv_time_detail);

        }
    }

    @NonNull
    @Override
    protected TextHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.order_text, parent, false);
        return new TextHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull TextHolder holder, @NonNull OrderItem textItem) {

        holder.tv_name.setText( textItem.name);
        holder.tv_cost.setText(textItem.cost);
        holder.tv_detail.setText(textItem.nameDetails);
        holder.tv_apply_detail.setText(textItem.apply);
        holder.tv_time_detail.setText(textItem.time);
        GlideUtils.loadRoundCircleImage(holder.iv_head.getContext(), textItem.url, holder.iv_head);


        Log.d("demo", "position: " + getPosition(holder));
        Log.d("demo", "adapter: " + getAdapter());
    }
}
