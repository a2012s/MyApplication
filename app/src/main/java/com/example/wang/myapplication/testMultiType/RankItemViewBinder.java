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
public class RankItemViewBinder extends ItemViewBinder<RankItem, RankItemViewBinder.TextHolder> {

    static class TextHolder extends RecyclerView.ViewHolder {


        private @NonNull
        final ImageView iv_head;
        private @NonNull
        final TextView tv_name;
        private @NonNull
        final TextView tv_rank;
        private @NonNull
        final TextView tv_time_detail;

        private @NonNull
        final TextView tv_total_score;
        private @NonNull
        final TextView tv_score;

        TextHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_head = itemView.findViewById(R.id.iv_head);
            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_rank = itemView.findViewById(R.id.tv_rank);
            this.tv_time_detail = itemView.findViewById(R.id.tv_time_detail);
            this.tv_total_score = itemView.findViewById(R.id.tv_total_score);
            this.tv_score = itemView.findViewById(R.id.tv_score);

        }
    }

    @NonNull
    @Override
    protected TextHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_rank, parent, false);
        return new TextHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull TextHolder holder, @NonNull RankItem textItem) {

        int rank = textItem.rank;

        holder.tv_name.setText(textItem.name);

        holder.tv_score.setText(textItem.score);
        holder.tv_time_detail.setText(textItem.timeDetails);
        holder.tv_total_score.setText(textItem.totalScore);

        if (rank == 1) {

            holder.tv_rank.setText("");
            holder.tv_rank.setBackgroundResource(R.mipmap.rank1);
        } else if (rank == 2) {
            holder.tv_rank.setBackgroundResource(R.mipmap.rank2);
            holder.tv_rank.setText("");
        } else if (rank == 3) {
            holder.tv_rank.setBackgroundResource(R.mipmap.rank3);
            holder.tv_rank.setText("");
        } else {
            holder.tv_rank.setText("  "+textItem.rank);
            holder.tv_rank.setBackground(null);
        }


        // Log.d("demo", "position: " + getPosition(holder));
        // Log.d("demo", "adapter: " + getAdapter());
    }
}
