package com.jojo.diary.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jojo.diary.R;
import com.jojo.diary.main.MainActivity;

public class SettingsFragment extends Fragment implements View.OnClickListener{
    private Button But_diaryList, But_memoList;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.settings_page,container,false);

        But_diaryList = (Button)rootView.findViewById(R.id.But_diaryList);
        But_diaryList.setOnClickListener(this);
        But_memoList = (Button)rootView.findViewById(R.id.But_memoList);
        But_memoList.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.But_diaryList:
                MainActivity.gotoPage(0);
                break;
            case R.id.But_memoList:
                MainActivity.gotoPage(2);
                break;
            default :
                break;
        }
    }
}
