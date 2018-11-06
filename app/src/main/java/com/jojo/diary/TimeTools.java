package com.jojo.diary;

import android.content.Context;

//获得英文名的月份、星期
public class TimeTools {

    private String[] monthsFullName;
    private String[] daysFullName;

    private static TimeTools instance = null;

    public static TimeTools getInstance(Context context) {
        if (instance == null) {
            instance = new TimeTools( context);
        }
        return instance;
    }

    private TimeTools(Context context) {
        monthsFullName = context.getResources().getStringArray(R.array.months_full_name);
        daysFullName = context.getResources().getStringArray(R.array.days_full_name);
    }

    public String[] getMonths() {
        return monthsFullName;
    }

    public String[] getDays() {
        return daysFullName;
    }
}
