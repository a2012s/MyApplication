package com.example.wang.myapplication.TestHellocharts;

/**
 * Created by wjj on 2018/7/17 11:20
 * E-Mail ：wjj99@qq.com
 * 描述：
 */

public class DateForLine {
    private String time;
    private String value;

    public DateForLine(String time, String value) {
        this.time = time;
        this.value = value;
    }

    public DateForLine() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
