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

    //初始化item的各个组件
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        private TextView TV_diary_item_day,TV_diary_item_month;
        private TextView TV_diary_item_time,TV_diary_item_title,TV_diary_item_summary;

        public MyViewHolder(View view) {
            super(view);
            TV_diary_item_day=(TextView)view.findViewById(R.id.TV_diary_item_day);
            TV_diary_item_month=(TextView)view.findViewById(R.id.TV_diary_item_month);
            TV_diary_item_time=(TextView)view.findViewById(R.id.TV_diary_item_time);
            TV_diary_item_title=(TextView)view.findViewById(R.id.TV_diary_item_title);
            TV_diary_item_summary=(TextView)view.findViewById(R.id.TV_diary_item_summary);

            this.itemView.setOnClickListener(this);
            this.itemView.setOnLongClickListener(this);
        }

        //单价item浏览日记
        @Override
        public void onClick(View v) {
            //打开一个日记浏览模式
            boolean isEditMode = false;
            dialog_diaryFragment dialog_diary= dialog_diaryFragment.newInstance(getAdapterPosition(),diaryItemList.get(getAdapterPosition()).getId(),
                    isEditMode);
            dialog_diary.setTargetFragment(viewFragment,0);
            dialog_diary.show(viewFragment.getFragmentManager(),"dialog_diary");
        }

        //长按item编辑日记
        @Override
        public boolean onLongClick(View v) {
            //长按编辑日记
            boolean isEditMode = true;
            dialog_diaryFragment dialog_diary= dialog_diaryFragment.newInstance(getAdapterPosition(),diaryItemList.get(getAdapterPosition()).getId(),
                    isEditMode);
            dialog_diary.setTargetFragment(viewFragment,0);
            dialog_diary.show(viewFragment.getFragmentManager(),"dialog_diary");
            return true;
        }
    }

    private List<diaryItem> diaryItemList;
    private ViewFragment viewFragment;

    //获得、初始化数据
    public recycleAdapter(ViewFragment viewFragment,List<diaryItem> diaryItemList){
        this.viewFragment=viewFragment;
        this.diaryItemList=diaryItemList;

    }

    //设置item样式
    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    //为每个item初始化数据，填充内容
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Date date = diaryItemList.get(position).getCreateDate();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        String day=calendar.get(Calendar.DAY_OF_MONTH)+"";
        String month=(calendar.get(Calendar.MONTH) +1) +"";
        String time=String.valueOf(new SimpleDateFormat("HH:mm").format(calendar.getTime()));

        myViewHolder.TV_diary_item_day.setText(day);
        myViewHolder.TV_diary_item_month.setText(month);
        myViewHolder.TV_diary_item_time.setText(time);

        String title=diaryItemList.get(position).getTitle();
        myViewHolder.TV_diary_item_title.setText(title);

        String summary=diaryItemList.get(position).getSummary();
        myViewHolder.TV_diary_item_summary.setText(summary);
    }

    @Override
    public int getItemCount() {
        return diaryItemList.size();
    }

    ////对外开放的添加、删除的动画效果
    public void add(diaryItem item,int position){
        diaryItemList.add(item);
        notifyItemInserted(position);
    }
    public void delete(int position){
        diaryItemList.remove(position);
        notifyItemRemoved(position);
    }
}
