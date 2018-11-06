package com.jojo.diary.memo;

import android.app.AlarmManager;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jojo.diary.R;
import com.jojo.diary.db.DBManager;
import com.jojo.diary.db.DBhelper;
import com.jojo.diary.main.MainActivity;
import com.jojo.diary.view.diaryItem;
import com.jojo.diary.view.recycleAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;

public class MemoFragment extends Fragment implements View.OnClickListener{
    private Button But_edit_memo_cancel,But_edit_memo_ok;
    private EditText EDT_edit_memo_info;

    private RelativeLayout memo_page_add_calendar;
    private TextView TV_md,TV_hs;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;

    private String info,date;

    private DBhelper dBhelper;

    private RecyclerView recyclerView;
    private memo_recycleAdapter memo_recycleAdapter;
    private List<memoItem> memoItemList;

    private SQLiteDatabase db;
    private DBManager dbManager;

    private Notification notification;
    private NotificationManager manager;
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    private long time;



    private int year,month,day,hour,minute;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dBhelper = new DBhelper(getActivity());
        db = dBhelper.getWritableDatabase();
        dbManager = new DBManager(db);

        memoItemList = new ArrayList<memoItem>();
        memoItemList = dbManager.getMemoItemList(memoItemList);

        View rootView = inflater.inflate(R.layout.memo_page,container,false);
        But_edit_memo_cancel = (Button)rootView.findViewById(R.id.But_edit_memo_cancel);
        But_edit_memo_cancel.setOnClickListener(this);

        But_edit_memo_ok = (Button)rootView.findViewById(R.id.But_edit_memo_ok);
        But_edit_memo_ok.setOnClickListener(this);

        EDT_edit_memo_info = (EditText)rootView.findViewById(R.id.EDT_edit_memo_content);
        EDT_edit_memo_info.setOnClickListener(this);


//        IV_diary_save = (ImageView)rootView.findViewById(R.id.IV_diary_save);
//        IV_diary_save.setOnClickListener(this);

        memo_page_add_calendar = (RelativeLayout)rootView.findViewById(R.id.RL_date_time_picker);
        memo_page_add_calendar.setOnClickListener(this);

        TV_md = (TextView)rootView.findViewById(R.id.md);
        TV_hs = (TextView)rootView.findViewById(R.id.hs);

        calendar = Calendar.getInstance();

        setCurrentTime();

        recyclerView = (RecyclerView)rootView.findViewById(R.id.RecyclerView_memo);
        memo_recycleAdapter = new memo_recycleAdapter(this,memoItemList);
        recyclerView.setAdapter(memo_recycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        memo_recycleAdapter.setOnItemClickListener(MyItemClickListener);
        return rootView;
    }

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

        date = "" + (calendar.get(Calendar.MONTH)+1) + "-" +
                calendar.get(Calendar.DAY_OF_MONTH) + " " +
                calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                calendar.get(Calendar.MINUTE);

//        setAlarm(md + " " + hs);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setCurrentTime(); 不能放这里？
    }
    private void setAlarm(String Sdate,String memoInfo) {
        simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
//        calendar = Calendar.getInstance();
        Date Ddate = null;
        try {
            Ddate = simpleDateFormat.parse(Sdate);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "设置提醒失败", Toast.LENGTH_SHORT).show();
        }
        time = dateToLong(Ddate);
//        calendar.setTimeInMillis(time);
//        Log.e("alarm time",""+time);
//        Log.e("alarm calendar",""+calendar.getTimeInMillis());
        String info = memoInfo;
////        Intent intent = new Intent(getContext(), AlarmReceiver.class);
////        intent.setAction("VIDEO_TIMER");
////        PendingIntent sender = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
////        alarmManager = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);
////        alarmManager.set(AlarmManager.RTC_WAKEUP, time, sender);
////        manager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
////        manager.cancel(1023);
////        notification = new Notification();
////        notification.icon = R.drawable.ic_mood_happy;
////        notification.tickerText = "备忘录：";
////        notification.setLatestEventInfo

        NotificationManager manager = (NotificationManager) getContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel channel = new NotificationChannel("chanel_id","channel_name",NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableVibration(true);
        channel.enableLights(true);
        manager.createNotificationChannel(channel);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext());
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0,
                new Intent(getContext(),MemoFragment.class),0);
        builder.setSmallIcon(R.drawable.ic_mood_happy)
                .setContentTitle("备忘录:")
                .setContentText(info)
                .setChannelId("chanel_id")
                .setWhen(time)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setVibrate(new long[] {0,300,500,700});//实现效果：延迟0ms，然后振动300ms。在延迟500ms，接着在振动700ms
        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_INSISTENT;//让声音、振动无限循环，直到用户响应 （取消或者打开）

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this,time);
            }
        };
        manager.notify(1, notification);

//        Intent intent = new Intent(getActivity(),AlarmReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(),0,intent,0);
//        AlarmManager alarmManager = (AlarmManager)getContext().getSystemService(ALARM_SERVICE);
//        alarmManager.set(AlarmManager.RTC_WAKEUP,time,pendingIntent);


//        Notification notify = new Notification.Builder(getContext())
//                .setSmallIcon(R.drawable.ic_mood_happy)
//                .setContentTitle("备忘录:")
//                .setContentText("memoInfo")
//                .setContentIntent(pendingIntent).setNumber(1).getNotification();
//        notify.flags |= Notification.FLAG_AUTO_CANCEL;

//        manager.notify(1, notify);

    }

    public static long dateToLong(Date date) {
        return date.getTime();
    }
    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.But_edit_memo_cancel:
//                But_edit_memo_cancel.setText("test");
                EDT_edit_memo_info.setText("");
                break;
            case R.id.But_edit_memo_ok:
                saveMemo(dbManager);
                EDT_edit_memo_info.setText("");

                break;
            case R.id.EDT_edit_memo_content:
                break;

            case R.id.RL_date_time_picker:
//                Toast.makeText(getActivity(),"calendar successfully!!", Toast.LENGTH_SHORT).show();
                calendar.getInstance();
                new TimePickerDialog(getActivity(),R.style.ThemeDialog,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String hs = " "+hourOfDay+":"+minute;
                                date += hs;
                                TV_hs.setText(hs);
                            }
                        },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();

                new DatePickerDialog(getActivity(),R.style.ThemeDialog,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String md = ""+(month+1)+"-"+dayOfMonth;
                                date = md;
                                TV_md.setText(md);
                            }
                        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

                Log.e("memo time",""+calendar.toString());

                break;
            default:
                break;
        }
    }

    private void saveMemo(DBManager dbManager){
        info = EDT_edit_memo_info.getText().toString();

//        date = "" + (calendar.get(Calendar.MONTH)+1) + "-" +
//                calendar.get(Calendar.DAY_OF_MONTH) + " " +
//                calendar.get(Calendar.HOUR_OF_DAY) + ":" +
//                calendar.get(Calendar.MINUTE);

        dbManager.insertMemo(date, info);

        memoItem item = new memoItem(memoItemList.size(),date,info);
//        Log.e("memoItemList.size()",""+memoItemList.size());
        memoItemList.add(item);
//        memoItemList = new ArrayList<memoItem>();
//        memoItemList = dbManager.getMemoItemList(memoItemList);
//Log.e("memolist size",""+memoItemList.size());
        memo_recycleAdapter.add(memoItemList.size());

        setAlarm(date,info);

//        db.close();
    }

    /**
     * item＋item里的控件点击监听事件
     */
    private memo_recycleAdapter.OnItemClickListener MyItemClickListener = new memo_recycleAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            switch (v.getId()) {
                case R.id.IV_memo_item_delete:
                    dbManager.delMemo(memoItemList.get(position).getMemoId());
                    memoItemList.remove(position);
                    memo_recycleAdapter.delete(position);
                    Toast.makeText(getActivity(), "删除memo", Toast.LENGTH_SHORT).show();
                    memo_recycleAdapter.delete(position);
                    break;
                default:
                    db.close();
                    break;
            }
        }
    };
}
