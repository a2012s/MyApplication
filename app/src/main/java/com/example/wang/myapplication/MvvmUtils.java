package com.example.wang.myapplication;

/**
 * 作者：wjj99@qq.com
 * 时间： 2018/6/29 10:32
 * <p>
 * 描述：
 */

public class MvvmUtils {

    /**
     * 第一个字母大写
     *
     * @param word
     * @return
     */
    public static String capitalize(String word) {
        if (word.length() > 1) {
            return String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1);
        }
        return word;
    }

    public static String getUserSex(float stats) {
        if (stats == 1f) {
            return "男";
        } else {
            return "女";
        }
    }

}
