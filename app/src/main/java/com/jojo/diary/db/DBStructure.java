package com.jojo.diary.db;

import android.provider.BaseColumns;

public class DBStructure {
    public static abstract class DBdiary implements BaseColumns{
        public static final String TABLE_NAME = "DBdiary";
        public static final String COLUMN_DATE = "diaryDate";
        public static final String COLUMN_TITLE = "diaryTitle";
        public static final String COLUMN_CONTENT = "diaryContent";
    }

    public static abstract class DBmemo implements BaseColumns{
        public static final String TABLE_NAME = "DBmemo";
        public static final String COLUMN_DATE = "memoDate";
        public static final String COLUMN_INFO = "memoInfo";

    }
}
