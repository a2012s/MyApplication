package com.example.wang.myapplication;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * Created by wjj on 2018/7/9 15:25
 * E-Mail ：wjj99@qq.com
 * 描述：
 */
public class SimpleViewActivity extends Activity{
    private TextView tv;
    private Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_simple_view);

        initView();
    }

    private void initView() {
        tv=findViewById(R.id.tv_simple_view);
        btn=findViewById(R.id.btn_simple_view);

        tv.setText("111");
        btn.setText("222");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("777");
            }
        });
    }
}
