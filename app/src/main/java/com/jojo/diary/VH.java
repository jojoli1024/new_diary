package com.jojo.diary;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//其中的关键点在于通过SparseArray<View>存储item view的控件，
//getView(int id)的功能就是通过id获得对应的View（
//首先在mViews中查询是否存在，如果没有，那么findViewById()并放入mViews中，避免下次再执行findViewById()）

class VH extends RecyclerView.ViewHolder{
    private SparseArray<View> mViews;
    private View mConvertView;

    private VH(View v){
        super(v);
        mConvertView = v;
        mViews = new SparseArray<>();
    }

    public static VH get(ViewGroup parent, int layoutId){
        View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new VH(convertView);
    }

    public <T extends View> T getView(int id){
        View v = mViews.get(id);
        if(v == null){
            v = mConvertView.findViewById(id);
            mViews.put(id, v);
        }
        return (T)v;
    }

    public void setText(int id, String value){
        TextView view = getView(id);
        view.setText(value);
    }
}
