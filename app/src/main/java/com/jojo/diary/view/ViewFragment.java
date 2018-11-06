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
//    private Button add;
//    private Button delete;
    private RecyclerView recyclerView;
    public static recycleAdapter recycleAdapter;
    public static List<diaryItem> diaryItemList;

    private DBhelper dBhelper;
    private SQLiteDatabase db;
    private DBManager dbManager;

//    private List<String> list;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        "/data/user/0/com.jojo.diary/databases/mydiary.db"
        dBhelper = new DBhelper(getActivity());
        SQLiteDatabase db = dBhelper.getWritableDatabase();
//        db = SQLiteDatabase.openOrCreateDatabase("/data/user/0/com.jojo.diary/databases/mydiary.db",null);
        dbManager = new DBManager(db);
//        dbManager.openDB();

        diaryItemList = new ArrayList<diaryItem>();
        diaryItemList = dbManager.getDiaryItemList(diaryItemList);
//        diaryItemList.add(new diaryItem(0,"在中山的一天","2018-11-01 11:15"));

        View rootView = inflater.inflate(R.layout.view_page,container,false);

//        diaryItemList.add(new diaryItem(1,"再见吧bug","2018-11-04 09:50"));
        //时间需要重新弄过！！！！！

//        diaryItemList.add(new diaryItem(0,"在中山的一天"));
//        diaryItemList.add(new diaryItem(1,"再见吧bug"));
//        list =new ArrayList<String>();
//        for(int i=0;i<10;i++){
//            list.add("add:"+i);
//        }


//        add = (Button)rootView.findViewById(R.id.add);
//        add.setOnClickListener(this);
//        delete = (Button)rootView.findViewById(R.id.delete);
//        delete.setOnClickListener(this);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.RecyclerView_diary);
        recycleAdapter = new recycleAdapter(this,diaryItemList);
        recyclerView.setAdapter(recycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

//        dbManager.closeDB();
        db.close();
        return rootView;
    }

//    public recycleAdapter getRecycleAdapter(){
//        return recycleAdapter;
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {

//        diaryItemList = dbManager.getDiaryItemList(diaryItemList);
//        diaryItemList.add(new diaryItem(0,"在中山的一天","2018-11-01 11:15"));

        super.onStart();
    }

    @Override
    public void onResume() {
//        diaryItemList = dbManager.getDiaryItemList(diaryItemList);
//        diaryItemList.add(new diaryItem(0,"在中山的一天","2018-11-01 11:15"));
//        recycleAdapter.notifyDataSetChanged();

        super.onResume();
    }

}
