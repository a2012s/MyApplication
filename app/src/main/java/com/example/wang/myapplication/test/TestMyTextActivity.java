package com.example.wang.myapplication.test;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wang.myapplication.R;
import com.example.wang.myapplication.utils.MyTextTool;

/**
 * Created by wjj on 2018/8/11 18:31
 * E-Mail ：wjj99@qq.com
 * 描述：
 */
public class TestMyTextActivity extends AppCompatActivity {
    //activity_text_utils

    CountDownTimer timer;
    private TextView tv_about_spannable;
    private TextView tv;
    int mCount = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_utils);


        tv = (TextView) findViewById(R.id.tv);


        /** 倒计时n秒，一次1秒 */
//        timer = new CountDownTimer(3 * 1000, 100) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                // TODO Auto-generated method stub
//                tv.setText("还剩0." + millisUntilFinished / 100 + "秒");
//                Log.e("logcat", "millisUntilFinished=" + millisUntilFinished);
//            }
//
//            @Override
//            public void onFinish() {
//                tv.setText("倒计时完毕了");
//            }
//        };

        timer = new CountDownTimer(mCount * 1000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.e("logcat", "onTick----l==" + millisUntilFinished);
                tv.setText("还剩0." + ((int) millisUntilFinished / 100 - 1) + " 秒");
            }

            @Override
            public void onFinish() {
                Log.d("logcat", "onFinish-------");
                tv.setText("finish！");
            }
        };


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.start();


            }
        });


        tv_about_spannable = findViewById(R.id.tv_about_spannable);
        testMyText();
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
