package com.example.wang.myapplication.test;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wang.myapplication.R;
import com.example.wang.myapplication.utils.JustifyTextView;
import com.example.wang.myapplication.utils.MyTextTool;

import java.util.HashMap;

/**
 * Created by wjj on 2018/8/11 18:31
 * E-Mail ：wjj99@qq.com
 * 描述：
 */
public class TestMyTextActivity extends AppCompatActivity {
    //activity_text_utils

    ClickableSpan clickableSpan;

    String paper = " Some of my  friends drove me to the airport a few months ago. On the whole ride there, I didn\\'t know that two of them wrote some __1__ and put them everywhere in my luggage (行李)．\n" +
            "    When I __2__ the airport and opened my luggage, I found some encouraging notes all over my clothes and bags. And they had __3__ put them inside my backpack! So in the next month, I would find an encouraging note when taking a pen or a book.\n" +
            "    I __4__ all these notes on the wall next to my bed. They were small signs of love from my friends.\n" +
            "    Today, two months __5__， I wanted to send my neighbor some kindness for his birthday. He\\'s someone who has given much to many people—families, friends  and strangers—and I thought that there was no __6__ that would be better than a small act of kindness.\n" +
            "    While I was trying to decide __7__ I could do, I found the encouraging notes on my wall. It was __8__! I took down some of them and walked around my neighbor's house, putting them __9__—on the window, the door and the wall.\n" +
            "        It made me smile to __10__ the good friends that wrote these notes eight weeks ago, and to see how these little __11__ messages would travel around my __12__ neighborhood sharing joy with him!";

    CountDownTimer timer;
    private TextView tv_about_spannable;
    private JustifyTextView tv_paper;
    int mCount = 3;
    int select;

    HashMap<Integer, String> mHashMap = new HashMap();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_utils);

        setPaper(paper, 0);


        tv_about_spannable = findViewById(R.id.tv_about_spannable);
        testMyText();
    }

    private void setPaper(String paper2, final int select) {
        Log.e("logcat", "setPaper=" + paper2);
        String paperS[] = paper2.split("__[1-9][0-9]?__");


        tv_paper = findViewById(R.id.tv_paper);
        // 响应点击事件的话必须设置以下属性
        tv_paper.setMovementMethod(LinkMovementMethod.getInstance());


        //  tv_paper.setText(paper);


        tv_paper.setText("");

        MyTextTool.Builder thisText = MyTextTool.getBuilder(TestMyTextActivity.this, "");

        int leng = paperS.length;


        for (int i = 0; i < leng; i++) {
            Log.e("logcat", paperS[i] + ";clickableSpan=" + (clickableSpan == null));
            final int finalI = i;

            String inStr = String.valueOf(i + 1);

            if (select == 0) {
                inStr = "__" + (i + 1) + "__";
            } else {

                if (!TextUtils.isEmpty(mHashMap.get(i))) {
                    inStr = mHashMap.get(i);
                }
            }

            if (i == (leng - 1)) {
                thisText.append(paperS[i]);
                break;
            }

            thisText.append(paperS[i]).append(inStr).setClickSpan(new ClickableSpan() {
                @Override
                public void onClick(View v) {
                    toDo(finalI);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setColor(Color.BLUE);
                    ds.setUnderlineText(false);
                }
            });


        }

        thisText.into(tv_paper);
    }

    private void toDo(int i) {

        String inStr;//=mHashMap.get(i);
        if (TextUtils.isEmpty(mHashMap.get(i))) {
            inStr = "__#" + i;
        } else {
            inStr = mHashMap.get(i);
        }

        mHashMap.put(i, "__#点击" + i);
        Toast.makeText(TestMyTextActivity.this, "点击" + (i + 1), Toast.LENGTH_SHORT).show();
//        Log.e("logcat", "点击" + (i + 1));
//        paper = tv_paper.getText().toString().replace(inStr, mHashMap.get(i));
//        setPaper(paper, i);

    }

    private void testMyText() {


        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(TestMyTextActivity.this, "点击", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        };


        // 响应点击事件的话必须设置以下属性
        tv_about_spannable.setMovementMethod(LinkMovementMethod.getInstance());

        MyTextTool.getBuilder(TestMyTextActivity.this, "哈哈\n").setBold().setAlign(Layout.Alignment.ALIGN_CENTER)
                .append("测试").append("Url\n").setUrl("https://www.baidu.com")
                .append("列表项\n").setBullet(60, getResources().getColor(R.color.colorPrimary))
                .append("  测试引用\n").setQuoteColor(getResources().getColor(R.color.google_red))
                .append("首行缩进\n").setLeadingMargin(30, 50)
                .append("测试").append("上标").setSuperscript().append("下标\n").setSubscript()
                .append("测试").append("点击事件\n").setClickSpan(clickableSpan)
                .append("测试").append("serif 字体\n").setFontFamily("serif")
                .append("测试").append("monospace字体\n").setFontFamily("monospace")
                .append("测试").append("图片\n").setResourceId(R.drawable.ic_next)
                .append("测试").append("前景色\n").setForegroundColor(Color.GREEN)
                .append("测试").append("背景色\n").setBackgroundColor(getResources().getColor(R.color.colorAccent2))
                .append("测试").append("删除线").setStrikethrough().append("下划线\n").setUnderline()
                .append("测试").append("sans-serif 字体\n").setFontFamily("sans-serif")
                .append("测试").append("2倍字体\n").setProportion(2).setForegroundColor(Color.RED)
                .append("测试").append("普通模糊效果字体\n").setBlur(3, BlurMaskFilter.Blur.NORMAL)
                .append("测试").append(" 粗体 ").setBold().append(" 斜体 ").setItalic().append(" 粗斜体 \n").setBoldItalic()
                .append("测试").append("横向2倍字体\n").setXProportion(2)
                .append("测试正常对齐\n").setAlign(Layout.Alignment.ALIGN_NORMAL)
                .append("测试居中对齐\n").setAlign(Layout.Alignment.ALIGN_CENTER)
                .append("测试相反对齐\n").setAlign(Layout.Alignment.ALIGN_OPPOSITE)
                .into(tv_about_spannable);
    }
}
