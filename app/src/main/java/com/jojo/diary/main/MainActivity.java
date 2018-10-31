package com.jojo.diary.main;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jojo.diary.MyPagerAdapter;
import com.jojo.diary.R;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.android.segmented.SegmentedGroup;

//ViewPager.OnPageChangeListener ,View.OnClickListener,
public class MainActivity extends AppCompatActivity  implements RadioGroup.OnCheckedChangeListener{
    private SegmentedGroup topbar;

    private ViewPager myViewPager;
    //要使用的ViewPager
    private View view,diary,memo,settings;
    //ViewPager所包含的页面
    private List<View> pageList;
    //ViewPager所包含的页面列表
    private MyPagerAdapter myPagerAdapter;
    //适配器
    private TextView topbar_title;

    private RadioButton topbar_view;
    private RadioButton topbar_diary;
    private RadioButton topbar_memo;
    private RadioButton topbar_settings;

//    private boolean isScrolling = false;
//    // 手指是否在滑动
//
//    private boolean isBackScrolling  = false;
//    // 手指离开后的回弹

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topbar = (SegmentedGroup)findViewById(R.id.topbar);
        topbar.setOnCheckedChangeListener(this);

        initButton();

        initView();
    }

    public void initButton(){
        topbar_title = (TextView)findViewById(R.id.topbar_title);
//        RadioButton topbar_view = (RadioButton)findViewById(R.id.topbar_view);
//        RadioButton topbar_diary = (RadioButton)findViewById(R.id.topbar_diary);
//        RadioButton topbar_memo = (RadioButton)findViewById(R.id.topbar_memo);
//        RadioButton topbar_settings = (RadioButton)findViewById(R.id.topbar_settings);

        topbar_view = (RadioButton)findViewById(R.id.topbar_view);
        topbar_diary = (RadioButton)findViewById(R.id.topbar_diary);
        topbar_memo = (RadioButton)findViewById(R.id.topbar_memo);
        topbar_settings = (RadioButton)findViewById(R.id.topbar_settings);


//        topbar_view.setOnClickListener(this);
//        topbar_diary.setOnClickListener(this);
//        topbar_memo.setOnClickListener(this);
//        topbar_settings.setOnClickListener(this);
    }

    public void initView(){
//        ViewPager myViewPager;
//        //要使用的ViewPager
//        View view,diary,memo,settings;
//        //ViewPager所包含的页面
//        List<View> pageList;
//        //ViewPager所包含的页面列表
//        MyPagerAdapter myPagerAdapter;
//        //适配器

        myViewPager= (ViewPager)findViewById(R.id.ViewPager);

        LayoutInflater inflater = getLayoutInflater();
        view = inflater.inflate(R.layout.view_page,null);
        diary = inflater.inflate(R.layout.diary_page,null);
        memo = inflater.inflate(R.layout.memo_page,null);
        settings = inflater.inflate(R.layout.settings_page,null);

        pageList = new ArrayList<View>();
        pageList.add(view);
        pageList.add(diary);
        pageList.add(memo);
        pageList.add(settings);

        myPagerAdapter = new MyPagerAdapter(pageList);
        myViewPager.setAdapter(myPagerAdapter);
        myViewPager.addOnPageChangeListener(onPageChangeListener);

        myViewPager.setCurrentItem(0);
        topbar_view.setChecked(true);
        //默认启动之后第一个页面为view_page
    }

//    public void gotoPage(int position) {
//        myViewPager.setCurrentItem(position);
//    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.topbar_view:
                myViewPager.setCurrentItem(0);
                topbar_title.setText("浏览");
                break;
            case R.id.topbar_diary:
                myViewPager.setCurrentItem(1);
                topbar_title.setText("日记");
                break;
            case R.id.topbar_memo:
                myViewPager.setCurrentItem(2);
                topbar_title.setText("备忘录");
                break;
            case R.id.topbar_settings:
                myViewPager.setCurrentItem(3);
                topbar_title.setText("我的设置");
                break;
            default:
                topbar_title.setText("");
                break;
        }
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                default:
                    topbar_view.setChecked(true);
                    topbar_title.setText("浏览");
                    break;
                case 1:
                    topbar_diary.setChecked(true);
                    topbar_title.setText("日记");
                    break;
                case 2:
                    topbar_memo.setChecked(true);
                    topbar_title.setText("备忘录");
                    break;
                case 3:
                    topbar_settings.setChecked(true);
                    topbar_title.setText("我的设置");
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
//    @Override
//    public void onClick(View v) {
////        TextView topbar_title = (TextView)findViewById(R.id.topbar_title);
//        功能1：设置topbar的标题
//        功能2：点击按钮，viewpager滑动到响应页面
//        功能3：滑动页面，tab滑动到对应的选卡按钮（未实现）
//        switch (v.getId()){
//            case R.id.topbar_view:
//                topbar_title.setText("浏览");
//                myViewPager.setCurrentItem(0);
//                break;
//            case R.id.topbar_diary:
//                topbar_title.setText("日记");
//                myViewPager.setCurrentItem(1);
//                break;
//            case R.id.topbar_memo:
//                topbar_title.setText("备忘录");
//                myViewPager.setCurrentItem(2);
//                break;
//            case R.id.topbar_settings:
//                topbar_title.setText("设置");
//                myViewPager.setCurrentItem(3);
//                break;
//            default:
//                topbar_title.setText("");
//                myViewPager.setCurrentItem(0);
//                break;
//        }
//    }

//    @Override
//    public void onPageScrollStateChanged(int i) {
//        switch (i){
//            case 1:
//                isScrolling = true;
//                isBackScrolling = false;
//                break;
//            case 2:
//                isBackScrolling = false;
//                isBackScrolling = true;
//                break;
//            default:
//                isScrolling = false;
//                isBackScrolling = false;
//                break;
//        }
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//        switch(position){
//            case 0:
////                topbar_view.callOnClick();
////                topbar_view.setBackgroundColor(Color.BLUE);
//                //上面两个都没有用！！！
//                topbar_view.setChecked(true);
//                break;
//            case 1:
////                topbar_diary.callOnClick();
////                topbar_diary.setBackgroundColor(Color.BLUE);
//                topbar_diary.setChecked(true);
//                break;
//            case 2:
////                topbar_memo.callOnClick();
////                topbar_memo.setBackgroundColor(Color.BLUE);
//                topbar_memo.setChecked(true);
//                break;
//            case 3:
////                topbar_settings.callOnClick();
////                topbar_settings.setBackgroundColor(Color.BLUE);
//                topbar_settings.setChecked(true);
//                break;
//            default:
//                break;
//        }
//    }
//
//    @Override
//    public void onPageScrolled(int i, float v, int i1) {
//
////    }
}
