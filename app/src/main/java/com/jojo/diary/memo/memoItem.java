package com.jojo.diary.memo;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class memoItem {
    private long memoId;
    private Date createDate;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
    private String info;

    public memoItem(){ }

    public memoItem(long memoId, String Date, String info){
        this.memoId = memoId;
        try {
            this.createDate = simpleDateFormat.parse(Date);
        } catch (Exception e){
            Log.e("diaryItem的创建","时间类型转换异常");
        }

        this.info = info;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public long getMemoId() {
        return memoId;
    }

    public String getInfo() {
        return info;
    }
}
