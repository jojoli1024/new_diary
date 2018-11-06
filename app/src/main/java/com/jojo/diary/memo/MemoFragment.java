package com.jojo.diary.memo;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jojo.diary.R;
import com.jojo.diary.db.DBManager;
import com.jojo.diary.db.DBhelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MemoFragment extends Fragment implements View.OnClickListener{
    private Button But_edit_memo_cancel,But_edit_memo_ok;
    private EditText EDT_edit_memo_info;

    private RelativeLayout memo_page_add_calendar;
    private TextView TV_md,TV_hs;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;

    private String memoInfo, memoDate;

    private DBhelper dBhelper;

    private RecyclerView recyclerView;
    private memo_recycleAdapter memo_recycleAdapter;
    private List<memoItem> memoItemList;

    private SQLiteDatabase db;
    private DBManager dbManager;

    private long time;

    //初始化界面的各项组件，并为RecycleView_memoR配置其适配器
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //初始化数据库接口
        dBhelper = new DBhelper(getActivity());
        db = dBhelper.getWritableDatabase();
        dbManager = new DBManager(db);

        //获得所有memo
        memoItemList = new ArrayList<memoItem>();
        memoItemList = dbManager.getMemoItemList(memoItemList);

        View rootView = inflater.inflate(R.layout.memo_page,container,false);
        But_edit_memo_cancel = (Button)rootView.findViewById(R.id.But_edit_memo_cancel);
        But_edit_memo_cancel.setOnClickListener(this);

        But_edit_memo_ok = (Button)rootView.findViewById(R.id.But_edit_memo_ok);
        But_edit_memo_ok.setOnClickListener(this);

        EDT_edit_memo_info = (EditText)rootView.findViewById(R.id.EDT_edit_memo_content);
        EDT_edit_memo_info.setOnClickListener(this);

        memo_page_add_calendar = (RelativeLayout)rootView.findViewById(R.id.RL_date_time_picker);
        memo_page_add_calendar.setOnClickListener(this);

        TV_md = (TextView)rootView.findViewById(R.id.md);
        TV_hs = (TextView)rootView.findViewById(R.id.hs);

        calendar = Calendar.getInstance();

        setCurrentTime();

        //初始化RecycleView_memoR并配置其适配器
        recyclerView = (RecyclerView)rootView.findViewById(R.id.RecyclerView_memo);
        memo_recycleAdapter = new memo_recycleAdapter(this,memoItemList);
        recyclerView.setAdapter(memo_recycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        memo_recycleAdapter.setOnItemClickListener(MyItemClickListener);

        return rootView;
    }

    //设置当前时间
    private void setCurrentTime() {
        simpleDateFormat = new SimpleDateFormat("HH:mm");
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        String md = "";
        md += calendar.get(Calendar.MONTH)+1;
        md += "-" + calendar.get(Calendar.DAY_OF_MONTH);
        String TAG = "md";
        if(md != null){
//            Log.e(TAG,md);
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

        //待插入memo的memoDate时间
        memoDate = "" + (calendar.get(Calendar.MONTH)+1) + "-" +
                calendar.get(Calendar.DAY_OF_MONTH) + " " +
                calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                calendar.get(Calendar.MINUTE);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setCurrentTime(); 不能放这里？
    }

    //设置定时提醒功能
    private void setAlarm(String Sdate,String memoInfo) {
        //将获得是的时间由String转为long型
        simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        Date Ddate = null;
        try {
            Ddate = simpleDateFormat.parse(Sdate);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "设置提醒失败", Toast.LENGTH_SHORT).show();
        }
        time = Ddate.getTime();

        NotificationManager manager = (NotificationManager) getContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);

        //android8.0版本及以上，必须设置NotificationChannel！！
        NotificationChannel channel = new NotificationChannel("chanel_id","channel_name",NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableVibration(true);
        channel.enableLights(true);
        manager.createNotificationChannel(channel);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext());
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0,
                new Intent(getContext(),MemoFragment.class),0);
        builder.setSmallIcon(R.drawable.ic_mood_happy)
                .setContentTitle("备忘录:")
                .setContentText(memoInfo)
                .setChannelId("chanel_id")
                .setWhen(time)                          //延迟时间效果不佳，待修改
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setVibrate(new long[] {0,300,500,700});//实现效果：延迟0ms，然后振动300ms。在延迟500ms，接着在振动700ms
        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_INSISTENT;//让声音、振动无限循环，直到用户响应 （取消或者打开）

        manager.notify(1, notification);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //清空所有输入
            case R.id.But_edit_memo_cancel:
//                But_edit_memo_cancel.setText("test");
                EDT_edit_memo_info.setText("");
                break;

            //保存该memo至数据库中，并清空输入
            case R.id.But_edit_memo_ok:
                saveMemo(dbManager);
                EDT_edit_memo_info.setText("");
                break;

            //选择备忘录的时间，初始化TimePickerDialog、DatePickerDialog
            case R.id.RL_date_time_picker:
                calendar.getInstance();
                //获得用户选择的时间日期
                new TimePickerDialog(getActivity(),R.style.ThemeDialog,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String hs = " "+hourOfDay+":"+minute;
                                memoDate += hs;
                                TV_hs.setText(hs);
                            }
                        },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();

                new DatePickerDialog(getActivity(),R.style.ThemeDialog,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String md = ""+(month+1)+"-"+dayOfMonth;
                                memoDate = md;
                                TV_md.setText(md);
                            }
                        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

//                Log.e("memo time",""+calendar.toString());

                break;
            default:
                break;
        }
    }

    //保存数据至DBmemo中，并设置定时提醒
    private void saveMemo(DBManager dbManager){
        //插入至表DBmemo中
        memoInfo = EDT_edit_memo_info.getText().toString();
        dbManager.insertMemo(memoDate, memoInfo);

        //刷新界面
        memoItem item = new memoItem(memoItemList.size(), memoDate, memoInfo);
        memoItemList.add(item);
        memo_recycleAdapter.add(memoItemList.size());

        //设置备忘录的定时提醒
        setAlarm(memoDate, memoInfo);
    }

    //为recycleView中的每个item设置删除memo的响应
    private memo_recycleAdapter.OnItemClickListener MyItemClickListener = new memo_recycleAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            switch (v.getId()) {
                case R.id.IV_memo_item_delete:
                    //从表DBmemo中删除
                    dbManager.delMemo(memoItemList.get(position).getMemoId());
                    //刷新界面
                    memoItemList.remove(position);
                    memo_recycleAdapter.delete(position);
                    Toast.makeText(getActivity(), "删除memo", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    db.close();
                    break;
            }
        }
    };
}
