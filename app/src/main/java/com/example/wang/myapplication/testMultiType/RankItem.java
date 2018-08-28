package com.example.wang.myapplication.testMultiType;

import android.support.annotation.NonNull;

/**
 * Created by wjj on 2018/7/11 15:13
 * E-Mail ：wjj99@qq.com
 * 描述：我的排行
 */
public class RankItem {

    public final @NonNull
    String url;//图片
    public final @NonNull
    String name;//
    public final @NonNull
    String timeDetails;//
    public final @NonNull
    String totalScore;
    public final @NonNull
    String score;//适用
    public final @NonNull
    int rank;


    public RankItem(@NonNull int rank, @NonNull String url, @NonNull String name,
                    @NonNull String timeDetails, @NonNull String totalScore, @NonNull String score) {
        this.url = url;
        this.name = name;
        this.totalScore = totalScore;
        this.timeDetails = timeDetails;
        this.score = score;
        this.rank = rank;
    }
}
