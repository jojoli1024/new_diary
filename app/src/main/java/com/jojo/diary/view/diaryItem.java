package com.jojo.diary.view;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

//封装diary类，便于插入、获得数据库DBdiary表中数据

public class diaryItem {
    private long id;
    private Date createDate;
    private String title;
    private String summary;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    public diaryItem(long id, String title, String createDate) {
        this.id = id;
        try {
            this.createDate = simpleDateFormat.parse(createDate);
        } catch (Exception e){
            Log.e("diaryItem的创建","时间类型转换异常");
        }

        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreateDate(String createDate) {
        try {
            this.createDate = simpleDateFormat.parse(createDate);
        } catch (Exception e){
            Log.e("setCreateDate","时间类型转换异常");
        }

    }

    public long getId() {
        return id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

}
