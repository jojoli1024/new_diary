package com.jojo.diary.view;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jojo.diary.R;
import com.jojo.diary.TimeTools;
import com.jojo.diary.db.DBManager;
import com.jojo.diary.db.DBhelper;
import com.jojo.diary.diary.DiaryFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ViewFragment extends Fragment {

    private RecyclerView recyclerView;
    public static recycleAdapter recycleAdapter;
    public static List<diaryItem> diaryItemList;

    private DBhelper dBhelper;
    private SQLiteDatabase db;
    private DBManager dbManager;

    //初始化界面与组件
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //调用数据库
        dBhelper = new DBhelper(getActivity());
        db = dBhelper.getWritableDatabase();
        dbManager = new DBManager(db);

        //从数据库DBdiary表中获得所有diary数据
        diaryItemList = new ArrayList<diaryItem>();
        diaryItemList = dbManager.getDiaryItemList(diaryItemList);

        //设置界面样式
        View rootView = inflater.inflate(R.layout.view_page,container,false);

        //初始化RecycleView和设置其适配器
        recyclerView = (RecyclerView)rootView.findViewById(R.id.RecyclerView_diary);
        recycleAdapter = new recycleAdapter(this,diaryItemList);
        recyclerView.setAdapter(recycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        db.close();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
