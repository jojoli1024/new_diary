package com.jojo.diary.diary;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;

// implements View.OnClickListener
public class DiaryFragment extends Fragment implements View.OnClickListener {

    private ImageView diary_page_clear;
    private ImageView diary_page_save;
    private ImageView diary_page_add_music;
    private RelativeLayout diary_page_add_date;
    private EditText EDT_diary_title,EDT_diary_content;
    private DiaryItemHelper diaryItemHelper;

    private String title,content,date;
    private TextView TV_diary_month, TV_diary_date, TV_diary_day, TV_diary_time;
    private Calendar calendar;
    private TimeTools timeTools;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");


    private DBhelper dBhelper;
//    private SQLiteDatabase db = dBhelper.getWritableDatabase();

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

        diary_page_add_date = (RelativeLayout) rootView.findViewById(R.id.RL_diary_info);
        diary_page_add_date.setOnClickListener(this);

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


    private void setCurrentTime(){
        calendar.setTimeInMillis(System.currentTimeMillis());
        TV_diary_month.setText(timeTools.getMonths()[calendar.get(Calendar.MONTH)]);
        TV_diary_date.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        TV_diary_day.setText(timeTools.getDays()[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
        TV_diary_time.setText(sdf.format(calendar.getTime()));
    }

    @Override
    public void onClick(View v) {
//        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("data/user/0/com.jojo.diary/mydiary.db",null);
        dBhelper = new DBhelper(getActivity());
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        DBManager dbManager = new DBManager(db);
        switch(v.getId()){
            case R.id.IV_diary_page_delete:
                Toast.makeText(getActivity(),"delete successfully!!", Toast.LENGTH_SHORT).show();
//                if (diaryItemHelper.getItemSize())
                break;

            case R.id.IV_diary_page_save:
//                if (diaryItemHelper.getItemSize()>0){
//                    saveDiary();
//                    Toast.makeText(getActivity(),"Save successfully!!", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(getActivity(),"Diary is empty!", Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(getActivity(),"save successfully!!", Toast.LENGTH_SHORT).show();
                saveDiary(dbManager);
                break;
            case R.id.IV_diary_music:
                Toast.makeText(getActivity(),"music successfully!!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.RL_diary_info:
                Toast.makeText(getActivity(),"calendar successfully!!", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void saveDiary(DBManager dbManager) {
//        dbManager.openDB();
        title = EDT_diary_title.getText().toString();
        content = EDT_diary_content.getText().toString();
        date = "" + calendar.get(Calendar.YEAR) + "-" +
                calendar.get(Calendar.MONTH) + "-" +
                calendar.get(Calendar.DAY_OF_MONTH) + " " +
                calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                calendar.get(Calendar.MINUTE);
        dbManager.insertDiary(date,title,content);
//        dbManager.closeDB();
    }
}

