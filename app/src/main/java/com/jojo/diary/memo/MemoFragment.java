package com.jojo.diary.memo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jojo.diary.R;
import com.jojo.diary.TimeTools;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MemoFragment extends Fragment implements View.OnClickListener{
    private Button But_edit_memo_cancel,But_edit_memo_ok;
    private EditText EDT_edit_memo_content;
    private ImageView IV_diary_delete,IV_diary_save;
    private RelativeLayout memo_page_add_calendar;
    private TextView TV_md,TV_hs;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private int month,day,hour,minute;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.memo_page,container,false);
        But_edit_memo_cancel = (Button)rootView.findViewById(R.id.But_edit_memo_cancel);
        But_edit_memo_cancel.setOnClickListener(this);

        But_edit_memo_ok = (Button)rootView.findViewById(R.id.But_edit_memo_ok);
        But_edit_memo_ok.setOnClickListener(this);

        EDT_edit_memo_content = (EditText)rootView.findViewById(R.id.EDT_edit_memo_content);
        EDT_edit_memo_content.setOnClickListener(this);

        IV_diary_delete = (ImageView)rootView.findViewById(R.id.IV_diary_delete);
        IV_diary_delete.setOnClickListener(this);

        IV_diary_save = (ImageView)rootView.findViewById(R.id.IV_diary_save);
        IV_diary_save.setOnClickListener(this);

        memo_page_add_calendar = (RelativeLayout)rootView.findViewById(R.id.RL_date_time_picker);
        memo_page_add_calendar.setOnClickListener(this);

        TV_md = (TextView)rootView.findViewById(R.id.md);
        TV_hs = (TextView)rootView.findViewById(R.id.hs);

        setCurrentTime();

        return rootView;
    }

    private void setCurrentTime() {
        simpleDateFormat = new SimpleDateFormat("HH:mm");
        calendar = Calendar.getInstance();
//        timeTools = TimeTools.getInstance(getActivity().getApplicationContext());
//        if (updateCurrentTime) {
//            calendar.setTimeInMillis(System.currentTimeMillis());
//        }
        calendar.setTimeInMillis(System.currentTimeMillis());
        String md = "";
//        md += timeTools.getMonths()[calendar.get(Calendar.MONTH)];
        md += calendar.get(Calendar.MONTH)+1;
        md += "-" + calendar.get(Calendar.DAY_OF_MONTH);
        String TAG = "md";
        if(md != null){
            Log.e(TAG,md);
            TV_md.setText(md);
        }else {
            Toast.makeText(getActivity(),md + "md is null !", Toast.LENGTH_SHORT).show();
        }

        String hs = simpleDateFormat.format(calendar.getTime());
        if(hs != null){
            TV_hs.setText(hs);
        }else {
            Toast.makeText(getActivity(),hs + "hs is null !", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setCurrentTime(); 不能放这里？
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.But_edit_memo_cancel:
//                But_edit_memo_cancel.setText("test");
                break;
            case R.id.But_edit_memo_ok:
                break;
            case R.id.EDT_edit_memo_content:
                break;
            case R.id.IV_diary_delete:
                break;
            case R.id.IV_diary_save:
                break;
            case R.id.RL_date_time_picker:
//                Toast.makeText(getActivity(),"calendar successfully!!", Toast.LENGTH_SHORT).show();
                calendar.getInstance();
                new TimePickerDialog(getActivity(),R.style.ThemeDialog,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                TV_hs.setText(""+hourOfDay+":"+minute);
                            }
                        },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();

                new DatePickerDialog(getActivity(),R.style.ThemeDialog,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                TV_md.setText(""+(month+1)+"-"+dayOfMonth);
                            }
                        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

//                new Thread(){
//                    public void run(){
//                        try{
//                            sleep(20000);
//                        }catch (Exception e){
//                            Log.e("sleep","失败了");
//                        }
//                    }
//                }.start();


                break;
            default:
                break;
        }
    }
}
