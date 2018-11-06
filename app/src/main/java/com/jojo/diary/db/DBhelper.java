package com.jojo.diary.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jojo.diary.db.DBStructure.DBdiary;
import com.jojo.diary.db.DBStructure.DBmemo;

public class DBhelper extends SQLiteOpenHelper {

    //数据库的版本
    public static final int DATABASE_VERSION = 1;

    //数据库的名字
    public static final String DATABASE_NAME = "mydiary.db";

    //创建DBdiary表的数据库命令
    private static final String SQL_CREATE_DBdiary =
            "CREATE TABLE " + DBdiary.TABLE_NAME +
                    "(" +
                    DBdiary._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBdiary.COLUMN_DATE + " TEXT," +
                    DBdiary.COLUMN_TITLE + " TEXT," +
                    DBdiary.COLUMN_CONTENT + " TEXT" +
                    ")";

    //创建DBmemo表的数据库命令
    private static final String SQL_CREATE_DBmemo =
            "CREATE TABLE " + DBmemo.TABLE_NAME +
                    "(" +
                    DBmemo._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBmemo.COLUMN_DATE + " TEXT," +
                    DBmemo.COLUMN_INFO + " TEXT" +
                    ")";

    //为外部操作数据库提供的接口
    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //创建数据库的同时，创建两张表：DBdiary和DBmemo
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DBdiary);
        db.execSQL(SQL_CREATE_DBmemo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

}
