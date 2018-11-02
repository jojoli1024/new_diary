package com.jojo.diary.db;

import java.util.Date;


public class DBdiary {
    private int diaryId;
    private Date diaryDate;
    private String title;
    private int musicId;
    private String context;

    public DBdiary(){}

    public DBdiary(int diaryId,Date date,String title,int musicId,String context){
        this.diaryId=diaryId;
        this.diaryDate=date;
        this.title=title;
        this.musicId=musicId;
        this.context=context;
    }

    public void setDate(Date date) {
        this.diaryDate = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getDiaryId() {
        return diaryId;
    }

    public Date getDate() {
        return diaryDate;
    }

    public int getMusicId() {
        return musicId;
    }

    public String getContext() {
        return context;
    }

    public String getTitle() {
        return title;
    }
}
