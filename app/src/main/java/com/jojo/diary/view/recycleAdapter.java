package com.jojo.diary.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jojo.diary.R;

import java.util.List;

public class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.MyViewHolder> {

//    public enum item_type{
//        item1,
//        item2;
//    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView TV_diary_title;

//        TextView title;

        public MyViewHolder(View view) {
            super(view);
            TV_diary_title=(TextView)view.findViewById(R.id.TV_diary_item_title);
//            title = (TextView)view.findViewById(R.id.title);
        }
    }
//    private List<diaryItem> diaryItemList;

//    public recycleAdapter(List<diaryItem> diaryItemList){
//        this.diaryItemList=diaryItemList;
//    }
    private List<String> list;

    public recycleAdapter(List<String> list){
        this.list=list;

    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
//        myViewHolder.TV_diary_title.setText("测试");
        myViewHolder.TV_diary_title.setText(list.get(position));
//        myViewHolder.title.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

//    static int position=list.size();

    public void add(int position){
        list.add(position+":  new");
        notifyItemInserted(position);
    }
    public void delete(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }
}
