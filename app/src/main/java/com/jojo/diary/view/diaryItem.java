package com.jojo.diary.view;

import java.util.Date;
//implements Comparable<CalendarDay>
public class diaryItem {
    private long id;
    private Date createDate;
    private String title;
    private String summary;
    private int weatherId;
    private int moodId;

    public diaryItem(){}

    public diaryItem(long id, Date createDate, String title,
                         int weatherId, int moodId) {
        this.id = id;
        this.createDate = createDate;
        this.title = title;
        this.weatherId = weatherId;
        this.moodId = moodId;
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
