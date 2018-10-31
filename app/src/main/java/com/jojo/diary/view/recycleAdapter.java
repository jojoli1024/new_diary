package com.jojo.diary.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jojo.diary.R;

import java.util.List;

public class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.MyViewHolder> {
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView TV_diary_title;

        //因为删除有可能会删除中间条目，然后会造成角标越界，所以必须整体刷新一下！
        public MyViewHolder(View view) {
            super(view);
            TV_diary_title=(TextView)view.findViewById(R.id.TV_diary_item_title);
//            tv = (TextView) view.findViewById(R.id.add);
//            tv_delete = (TextView) view.findViewById(R.id.delete);
        }
    }
    private List<diaryItem> diaryItemList;
    private Context context;

    public recycleAdapter(Context context,List<diaryItem> diaryItemList){
        this.context=context;
        this.diaryItemList=diaryItemList;
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.view_page,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.TV_diary_title.setText("测试");

    }

    @Override
    public int getItemCount() {
        return diaryItemList.size();
    }

    public void add(int position){
        diaryItemList.add(position,new diaryItem());
    }
    public void delete(int position){
        diaryItemList.remove(position);
    }
}
