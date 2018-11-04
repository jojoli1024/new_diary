package com.jojo.diary.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jojo.diary.db.DBStructure.DBdiary;
import com.jojo.diary.db.DBStructure.DBmemo;

public class DBhelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mydiary.db";

    private static final String SQL_CREATE_DBdiary =
            "CREATE TABLE " + DBdiary.TABLE_NAME +
                    "(" +
                    DBdiary._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBdiary.COLUMN_DATE + " TEXT," +
                    DBdiary.COLUMN_TITLE + " TEXT," +
                    DBdiary.COLUMN_CONTENT + " TEXT" +
                    ")";


    private static final String SQL_CREATE_DBmemo =
            "CREATE TABLE " + DBmemo.TABLE_NAME +
                    "(" +
                    DBmemo._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBmemo.COLUMN_DATE + " TEXT," +
                    DBmemo.COLUMN_INFO + " TEXT" +
                    ")";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DBdiary);
        db.execSQL(SQL_CREATE_DBmemo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            try{
                db.beginTransaction();
                db.setTransactionSuccessful();
            }
            finally {
                db.endTransaction();
            }
        }
        else {
            onCreate(db);
        }
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
