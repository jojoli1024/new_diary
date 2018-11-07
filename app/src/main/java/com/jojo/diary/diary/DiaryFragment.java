package com.jojo.diary.diary;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jojo.diary.R;
import com.jojo.diary.TimeTools;
import com.jojo.diary.db.DBManager;
import com.jojo.diary.db.DBhelper;
import com.jojo.diary.main.MainActivity;
import com.jojo.diary.view.ViewFragment;
import com.jojo.diary.view.diaryItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DiaryFragment extends Fragment implements View.OnClickListener {

    private ImageView diary_page_clear;
    private ImageView diary_page_save;
    private ImageView diary_page_add_music;
    private EditText EDT_diary_title,EDT_diary_content;

    private String title,content,date;
    private TextView TV_diary_month, TV_diary_date, TV_diary_day, TV_diary_time;
    private Calendar calendar;
    private TimeTools timeTools;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    private DBhelper dBhelper;

    //初始化界面以及为相关按钮添加监听
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.diary_page,container,false);

        diary_page_clear = (ImageView)rootView.findViewById(R.id.IV_diary_page_delete);
        diary_page_clear.setOnClickListener(this);

        diary_page_save = (ImageView)rootView.findViewById(R.id.IV_diary_page_save);
        diary_page_save.setOnClickListener(this);

        diary_page_add_music=(ImageView) rootView.findViewById(R.id.IV_diary_music);
        diary_page_add_music.setOnClickListener(this);

        TV_diary_month = (TextView) rootView.findViewById(R.id.TV_diary_month);
        TV_diary_date = (TextView) rootView.findViewById(R.id.TV_diary_date);
        TV_diary_day = (TextView) rootView.findViewById(R.id.TV_diary_day);
        TV_diary_time = (TextView) rootView.findViewById(R.id.TV_diary_time);

        EDT_diary_content = (EditText) rootView.findViewById(R.id.EDT_diary_content);
        EDT_diary_title = (EditText) rootView.findViewById(R.id.EDT_diary_title);

        calendar = Calendar.getInstance();
        timeTools = TimeTools.getInstance(getActivity().getApplicationContext());

        setCurrentTime();

        return rootView;
    }

    //初始化界面时，初始化时间为系统当前时间
    private void setCurrentTime(){
        calendar.setTimeInMillis(System.currentTimeMillis());
        TV_diary_month.setText(timeTools.getMonths()[calendar.get(Calendar.MONTH)]);
        TV_diary_date.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        TV_diary_day.setText(timeTools.getDays()[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
        TV_diary_time.setText(sdf.format(calendar.getTime()));
    }

    @Override
    public void onClick(View v) {

        //打开数据库，便于插入日记
        dBhelper = new DBhelper(getActivity());
        SQLiteDatabase db = dBhelper.getWritableDatabase();
//        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("mydiary.db",null);
        DBManager dbManager = new DBManager(db);

        switch(v.getId()){
            //清除所有输入内容的按钮
            case R.id.IV_diary_page_delete:
                EDT_diary_title.setText("");
                EDT_diary_content.setText("");
                Toast.makeText(getActivity(),"clear successfully!!", Toast.LENGTH_SHORT).show();

                break;

            //保存所有输入、存入数据库DBdiary中，并清空输入内容
            case R.id.IV_diary_page_save:
                saveDiary(dbManager);
//                db.close();
                EDT_diary_title.setText("");
                EDT_diary_content.setText("");
                break;

//            //为日记选择音乐的按钮
//            case R.id.IV_diary_music:
//                Toast.makeText(getActivity(),"music successfully!!", Toast.LENGTH_SHORT).show();
//                break;

            default:
//                db.close();
                break;
        }
    }

    private void saveDiary(DBManager dbManager) {
        //获得输入内容
        title = EDT_diary_title.getText().toString();
        content = EDT_diary_content.getText().toString();
        date = "" + calendar.get(Calendar.YEAR) + "-" +
                (calendar.get(Calendar.MONTH)+1) + "-" +
                calendar.get(Calendar.DAY_OF_MONTH) + " " +
                calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                calendar.get(Calendar.MINUTE);
        //将数据插入到数据库中
        dbManager.insertDiary(date,title,content);

        //刷新浏览我的日记界面：ViewFragment
        ViewFragment.diaryItemList=new ArrayList<diaryItem>();
        ViewFragment.diaryItemList=dbManager.getDiaryItemList(ViewFragment.diaryItemList);

        int index = ViewFragment.diaryItemList.size();
        long diaryId = ViewFragment.diaryItemList.get(index - 1).getId();

        diaryItem item = dbManager.getDiaryItem(diaryId);
        ViewFragment.recycleAdapter.add(item,(int)index);
    }
}
