package com.jojo.diary.main;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyPagerAdapter extends PagerAdapter{
    private List<View> pageList;

    public MyPagerAdapter(List<View> pageList){
        this.pageList=pageList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(pageList.get(position));
        return pageList.get(position);
    }

    @Override
    public int getCount() {
        return pageList.size();
        //返回要展示的页数，应为4页：浏览、日记、备忘录、我（设置页面）
    }

    @Override
    public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
        return arg0==arg1;
        //???
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(pageList.get(position));
        //将当前位置的view移除
    }
}
