package com.example.wang.myapplication;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
/**
 * Created by wjj on 2018/7/9 15:28
 * E-Mail ：wjj99@qq.com
 * 描述：
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SimpleViewTest {

    @Rule
    public ActivityTestRule<SimpleViewActivity> mActivityTestRule =
            new ActivityTestRule<SimpleViewActivity>(SimpleViewActivity.class);



    @Test
    public void textViewTest()  {
        onView(withId(R.id.tv_simple_view))
                .check(matches(withText("111")));
    }

    @Test
    public void buttonTest()  {
        onView(withId(R.id.btn_simple_view))
                .check(matches(withText("222")))
                .perform(click());
        onView(withId(R.id.tv_simple_view))
                .check(matches(withText("777")));
    }
}
