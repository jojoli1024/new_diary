package com.jojo.diary.view;

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

import java.util.ArrayList;
import java.util.List;

public class ViewFragment extends Fragment implements View.OnClickListener{
    private Button add;
    private Button delete;
    private RecyclerView recyclerView;
    private recycleAdapter recycleAdapter;
    private List<diaryItem> diaryItemList;
//    private List<String> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_page,container,false);

        diaryItemList = new ArrayList<diaryItem>();
        diaryItemList.add(new diaryItem(0,"在中山的一天",201811010939L));
        diaryItemList.add(new diaryItem(1,"再见吧bug",201811011117L));
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

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.add:
////                add.setText("test:add");
//                recycleAdapter.add(list.size());
//                break;
//            case R.id.delete:
////                delete.setText("test:delete");
//                recycleAdapter.delete(list.size()-1);
//                break;
//            default:
//                break;
//        }
    }
}
