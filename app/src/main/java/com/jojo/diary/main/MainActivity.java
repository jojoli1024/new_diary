package com.jojo.diary.main;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jojo.diary.R;
import com.jojo.diary.db.DBManager;
import com.jojo.diary.db.DBhelper;
import com.jojo.diary.diary.DiaryFragment;
import com.jojo.diary.memo.MemoFragment;
import com.jojo.diary.settings.SettingsFragment;
import com.jojo.diary.view.ViewFragment;

import info.hoang8f.android.segmented.SegmentedGroup;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener{
    private SegmentedGroup topbar;

    private ViewPager myViewPager;
    private TextView topbar_title;
    private RadioButton topbar_view, topbar_diary, topbar_memo, topbar_settings;
    private ScreenSlidePagerAdapter mPagerAdapter;

    private DBhelper dBhelper;
    private DBManager dbManager;
//    List<diaryItem> diaryItemList;
//    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topbar = (SegmentedGroup)findViewById(R.id.topbar);
        topbar.setOnCheckedChangeListener(this);

        initButton();
        initViewPager();

        gotoPage(0);
        topbar_view.setChecked(true);

//        deleteDatabase("mydiary.db");
//
//        dBhelper = new DBhelper(MainActivity.this);
//        SQLiteDatabase db = dBhelper.getWritableDatabase();
//        dbManager = new DBManager(db);

        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("data/user/0/com.jojo.diary/mydiary.db",null);
    }

    public void initButton(){
        topbar_title = (TextView)findViewById(R.id.topbar_title);

        topbar_view = (RadioButton)findViewById(R.id.topbar_view);
        topbar_diary = (RadioButton)findViewById(R.id.topbar_diary);
        topbar_memo = (RadioButton)findViewById(R.id.topbar_memo);
        topbar_settings = (RadioButton)findViewById(R.id.topbar_settings);
    }

    private void gotoPage(int position) {
        myViewPager.setCurrentItem(position);
    }

    private void initViewPager(){
        myViewPager = (ViewPager)findViewById(R.id.ViewPager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(mPagerAdapter);
        myViewPager.addOnPageChangeListener(onPageChangeListener);

    }

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

    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter{

        private SparseArray<Fragment> registeredFragments =new SparseArray<Fragment>();

        public ScreenSlidePagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position){
                default:
                    fragment=new ViewFragment();
                    break;
                case 1:
                    fragment=new DiaryFragment();
                    break;
                case 2:
                    fragment=new MemoFragment();
                    break;
                case 3:
                    fragment=new SettingsFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            registeredFragments.put(position, fragment);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            registeredFragments.remove(position);
            super.destroyItem(container, position, object);
        }

        public Fragment getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }
    }
}
