package com.jojo.diary.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jojo.diary.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.MyViewHolder> {

//    public enum item_type{
//        item1,
//        item2;
//    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        private TextView TV_diary_item_day,TV_diary_item_month;
        private TextView TV_diary_item_time,TV_diary_item_title,TV_diary_item_summary;
        private ImageView IV_diary_item_weather,IV_diary_item_mood;

//        TextView title;

        public MyViewHolder(View view) {
            super(view);
            TV_diary_item_day=(TextView)view.findViewById(R.id.TV_diary_item_day);
            TV_diary_item_month=(TextView)view.findViewById(R.id.TV_diary_item_month);
            TV_diary_item_time=(TextView)view.findViewById(R.id.TV_diary_item_time);
            TV_diary_item_title=(TextView)view.findViewById(R.id.TV_diary_item_title);
            TV_diary_item_summary=(TextView)view.findViewById(R.id.TV_diary_item_summary);
            IV_diary_item_weather=(ImageView)view.findViewById(R.id.IV_diary_item_weather);
            IV_diary_item_mood=(ImageView)view.findViewById(R.id.IV_diary_item_mood);

//            title = (TextView)view.findViewById(R.id.title);

            this.itemView.setOnClickListener(this);
            this.itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //打开一个日记浏览模式
//            v.findViewById(R.layout.dialog_diary_page);
            boolean isEditMode = false;
            dialog_diaryFragment dialog_diary= dialog_diaryFragment.newInstance(diaryItemList.get(getAdapterPosition()).getId(),
                    isEditMode);
            dialog_diary.setTargetFragment(viewFragment,0);
            dialog_diary.show(viewFragment.getFragmentManager(),"dialog_diary");
//            dialog_diary.show();
            TV_diary_item_summary.setText("你点击了onClick！");
        }

        @Override
        public boolean onLongClick(View v) {
            //长按编辑日记
            TV_diary_item_summary.setText("你点击了onLongClick！");
            return true;
        }
    }
    private List<diaryItem> diaryItemList;
    private ViewFragment viewFragment;

    public recycleAdapter(ViewFragment viewFragment,List<diaryItem> diaryItemList){
        this.viewFragment=viewFragment;
        this.diaryItemList=diaryItemList;
    }
//    private List<String> list;
//
//    public recycleAdapter(List<String> list){
//        this.list=list;
//
//    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Date date = diaryItemList.get(position).getCreateDate();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        String day=calendar.get(Calendar.DAY_OF_MONTH)+"";
        String month=(calendar.get(Calendar.MONTH) +1) +"";
        String time=String.valueOf(new SimpleDateFormat("HH:mm").format(calendar.getTime()));
//        Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
        myViewHolder.TV_diary_item_day.setText(day);
        myViewHolder.TV_diary_item_month.setText(month);
        myViewHolder.TV_diary_item_time.setText(time);
//
        String title=diaryItemList.get(position).getTitle();
        myViewHolder.TV_diary_item_title.setText(title);

        String summary=diaryItemList.get(position).getSummary();
        myViewHolder.TV_diary_item_summary.setText(summary);

//        int weather=diaryItemList.get(position).getWeatherId();
//        myViewHolder.IV_diary_item_weather.setImageResource(weather);
//
//        int mood=diaryItemList.get(position).getMoodId();
//        myViewHolder.IV_diary_item_weather.setImageResource(mood);

//        myViewHolder.title.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return diaryItemList.size();
    }

//    static int position=list.size();

//    public void add(int position){
//        list.add(position+":  new");
//        notifyItemInserted(position);
//    }
//    public void delete(int position){
//        list.remove(position);
//        notifyItemRemoved(position);
//    }
}
