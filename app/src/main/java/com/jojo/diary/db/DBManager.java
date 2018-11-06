package com.jojo.diary.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jojo.diary.memo.memoItem;
import com.jojo.diary.view.diaryItem;

import java.text.SimpleDateFormat;
import java.util.List;

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
    public long insertDiary(String diary_date, String title, String content){
        return db.insert(
                DBStructure.DBdiary.TABLE_NAME,
                //nullColumnHack这个参数是一个字段名，String类型，
                // 数据本来是不允许插入所有字段值都为NULL的记录，
                // 但是如果你指定一个字段名为nullColumnHack的值，
                // 则数据库允许参加所有字段值都为NULL的记录，就不会报错。
                null,
                this.createDiary(title, content,diary_date));
    }

    public long updateDiary(long diaryId, String title, String content,String diary_date){
        ContentValues values = this.createDiary(title, content ,diary_date);
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

    private ContentValues createDiary(String  title, String content, String diary_date){
        ContentValues values = new ContentValues();
        values.put(DBStructure.DBdiary.COLUMN_TITLE, title);
        values.put(DBStructure.DBdiary.COLUMN_CONTENT, content);
        values.put(DBStructure.DBdiary.COLUMN_DATE,diary_date);
        return values;
    }


    // 备忘录：新建、更新、删除
    public long insertMemo(String Memo_date, String info){
        return db.insert(
                DBStructure.DBmemo.TABLE_NAME,
                null,
                this.createMemo(Memo_date, info));
    }

//    public long updateMemo(long memoId, String date, String info){
//        ContentValues values = this.createMemo(date, info);
//        return db.update(
//                DBStructure.DBmemo.TABLE_NAME,
//                values,
//                DBStructure.DBmemo._ID + "=?",
//                new String[]{String.valueOf(memoId)}
//        );
//    }

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

    public List<diaryItem> getDiaryItemList(List<diaryItem> diaryItemList){
        Cursor cursor = db.query(DBStructure.DBdiary.TABLE_NAME,
                new String[]{"_id","diaryDate","diaryTitle","diaryContent"},
                null,null,null,null,null);
        long id;
        String diaryDate,diaryTitle,diaryContent;
        while (cursor.moveToNext()){
            id = cursor.getLong(cursor.getColumnIndex("_id"));
            diaryDate = cursor.getString(cursor.getColumnIndex("diaryDate"));
            diaryTitle = cursor.getString(cursor.getColumnIndex("diaryTitle"));
            diaryContent = cursor.getString(cursor.getColumnIndex("diaryContent"));
            diaryItem diaryItem = new diaryItem(id, diaryTitle,diaryDate);

//            Log.e("diaryItem date", diaryDate);
//            Log.e("diaryItem title",diaryTitle);

            diaryItem.setSummary(diaryContent);
//            Log.e("diaryItem content",diaryContent);

            if(diaryItem == null){
                Log.e("diaryItem","is null!");
            } else{
                diaryItemList.add(diaryItem);
            }
        }
        return diaryItemList;
    }

    public List<memoItem> getMemoItemList(List<memoItem> memoItemList){
        Cursor cursor = db.query(DBStructure.DBmemo.TABLE_NAME,
                new String[]{"_id","memoDate","memoInfo"},
                null,null,null,null,null);
        long id;
        String memoDate,memoInfo;
        while (cursor.moveToNext()){
            id = cursor.getLong(cursor.getColumnIndex("_id"));
            memoDate = cursor.getString(cursor.getColumnIndex("memoDate"));
            memoInfo = cursor.getString(cursor.getColumnIndex("memoInfo"));

            memoItem memoItem = new memoItem(id,memoDate,memoInfo);

//            Log.e("memoItem date", memoDate);
//            Log.e("memoItem title",memoInfo);

            if(memoItem == null){
                Log.e("memoItem","is null!");
            } else{
                memoItemList.add(memoItem);
            }
        }
        return memoItemList;
    }

    public memoItem getMemoItem(long memoId){
        Cursor cursor = db.query(DBStructure.DBmemo.TABLE_NAME,
                new String[]{"_id","memoDate","memoInfo"},
                DBStructure.DBmemo._ID + " = ?",
                new String[]{String.valueOf(memoId)},null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        memoItem memoItem = new memoItem(cursor.getLong(cursor.getColumnIndex("_id")),
                cursor.getString(cursor.getColumnIndex("memoDate")),
                cursor.getString(cursor.getColumnIndex("memoInfo")));
        return memoItem;
    }

    public diaryItem getDiaryItem(long diaryId) {
        Cursor cursor = db.query(DBStructure.DBdiary.TABLE_NAME,
                new String[]{"_id","diaryDate","diaryTitle","diaryContent"},
                DBStructure.DBdiary._ID + " = ?",
                new String[]{String.valueOf(diaryId)}, null, null,
                DBStructure.DBdiary.COLUMN_DATE + " DESC , " + DBStructure.DBdiary._ID + " DESC",
                null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        long id;
        String diaryDate,diaryTitle,diaryContent;

        id = cursor.getLong(cursor.getColumnIndex("_id"));
        diaryDate = cursor.getString(cursor.getColumnIndex("diaryDate"));
        diaryTitle = cursor.getString(cursor.getColumnIndex("diaryTitle"));
        diaryContent = cursor.getString(cursor.getColumnIndex("diaryContent"));
        diaryItem diaryItem = new diaryItem(id, diaryTitle,diaryDate);

//        Log.e("diaryItem date", diaryDate);
//        Log.e("diaryItem title",diaryTitle);

        diaryItem.setSummary(diaryContent);
//        Log.e("diaryItem content",diaryContent);

        return diaryItem;
    }


}
