package com.jojo.diary.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private Context context;
    private SQLiteDatabase db;
    private DBhelper mDBHelper;

    public DBManager(Context context) { this.context = context; }

    public DBManager(SQLiteDatabase db) { this.db = db; }

    public void openDB() throws SQLException {
        mDBHelper = new DBhelper(context);
        this.db = mDBHelper.getWritableDatabase();
    }

    public void closeDB() { mDBHelper.close(); }

    // Id自动生成
    // 日记：新建、更新、删除
    public long insertDiary(String title, long musicId, String content){
        return db.insert(
                DBStructure.DBdiary.TABLE_NAME,
                //nullColumnHack这个参数是一个字段名，String类型，
                // 数据本来是不允许插入所有字段值都为NULL的记录，
                // 但是如果你指定一个字段名为nullColumnHack的值，
                // 则数据库允许参加所有字段值都为NULL的记录，就不会报错。
                null,
                this.createDiary(title, musicId, content));
    }

    public long updateDiary(long diaryId, String title, long musicId, String content){
        ContentValues values = this.createDiary(title, musicId, content);
        return db.update(
                DBStructure.DBdiary.TABLE_NAME,
                values,
                DBStructure.DBdiary._ID + " =?",
                new String[]{String.valueOf(diaryId)}
        );
    }

    public long delDiary(long diaryId){
        return db.delete(
                DBStructure.DBdiary.TABLE_NAME,
                DBStructure.DBdiary._ID + " =?",
                new String[]{String.valueOf(diaryId)}
        );
    }

    private ContentValues createDiary(String  title, long musicId, String content){
        ContentValues values = new ContentValues();
        values.put(DBStructure.DBdiary.COLUMN_TITLE, title);
        values.put(DBStructure.DBdiary.COLUMN_MUSICID, musicId);
        values.put(DBStructure.DBdiary.COLUMN_CONTENT, content);
        return values;
    }


    // 备忘录：新建、更新、删除
    public long insertMemo(String date, String info){
        return db.insert(
                DBStructure.DBmemo.TABLE_NAME,
                null,
                this.createMemo(date, info));
    }

    public long updateMemo(long memoId, String date, String info){
        ContentValues values = this.createMemo(date, info);
        return db.update(
                DBStructure.DBmemo.TABLE_NAME,
                values,
                DBStructure.DBmemo._ID + "=?",
                new String[]{String.valueOf(memoId)}
        );
    }

    public long delMemo(long memoId){
        return db.delete(
                DBStructure.DBmemo.TABLE_NAME,
                DBStructure.DBmemo._ID + " =?",
                new String[]{String.valueOf(memoId)}
        );
    }

    private ContentValues createMemo(String date, String info){
        ContentValues values = new ContentValues();
        values.put(DBStructure.DBmemo.COLUMN_DATE, date);
        values.put(DBStructure.DBmemo.COLUMN_INFO, info);
        return values;
    }


    // 音乐：插入音乐
    public long insertMusic(String musicName){
        return db.insert(
                DBStructure.DBmusic.TABLE_NAME,
                null,
                this.createMusic(musicName));
    }

    private ContentValues createMusic(String musicName){
        ContentValues values = new ContentValues();
        values.put(DBStructure.DBmusic.COLUMN_NAME, musicName);
        return values;
    }



    public Cursor selectDiaryList(long diaryId) {
        Cursor c = db.query(DBStructure.DBdiary.TABLE_NAME,
                null, DBStructure.DBdiary._ID + " = ?",
                new String[]{String.valueOf(diaryId)}, null, null,
                DBStructure.DBdiary.COLUMN_DATE + " DESC , " + DBStructure.DBdiary._ID + " DESC",
                null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
}
