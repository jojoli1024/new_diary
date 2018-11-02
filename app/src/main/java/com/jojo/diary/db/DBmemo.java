package com.jojo.diary.db;

import java.util.Date;

public class DBmemo {
    private int memoId;
    private Date memoDate;
    private String memoInfo;

    public DBmemo(){}

    public DBmemo(int memoId,Date memoDate,String memoInfo){
        this.memoId=memoId;
        this.memoDate=memoDate;
        this.memoInfo=memoInfo;
    }

    public void setMemoDate(Date memoDate) {
        this.memoDate = memoDate;
    }

    public void setMemoInfo(String memoInfo) {
        this.memoInfo = memoInfo;
    }

    public int getMemoId() {
        return memoId;
    }

    public Date getMemoDate() {
        return memoDate;
    }

    public String getMemoInfo() {
        return memoInfo;
    }
}
