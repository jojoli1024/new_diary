package com.jojo.diary.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

//implements Comparable<CalendarDay>
public class diaryItem {
    private long id;
    private Date createDate;
    private String title;
    private String summary;
    private int weatherId;
    private int moodId;
//    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
//    String dstr="2008-4-24";
//    java.util.Date date=sdf.parse(dstr);
    public diaryItem(){}

    public diaryItem(long id, String title, long createDate
            ) {//, int weatherId, int moodId
        this.id = id;
        this.createDate = new Date(createDate);
        this.title = title;
//        this.weatherId = weatherId;
//        this.moodId = moodId;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public int getWeatherId() {
        return weatherId;
    }

    public int getMoodId() {return moodId;}

//    @Override
//    public int compareTo( CalendarDay calendarDay) {
//        //TODO improve the compare performance
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(createDate);
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MILLISECOND, 0);
//        return Long.valueOf(calendarDay.getDate().getTimeInMillis()).compareTo(
//                cal.getTimeInMillis());
//    }
}
