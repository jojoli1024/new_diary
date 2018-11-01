package com.jojo.diary.diary;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jojo.diary.R;
// implements View.OnClickListener
public class DiaryFragment extends Fragment implements View.OnClickListener {

    private ImageView diary_delete;
    private ImageView diary_save;
    private EditText EDT_diary_title;
    private DiaryItemHelper diaryItemHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.diary_page,container,false);

        diary_delete = (ImageView)rootView.findViewById(R.id.IV_diary_page_delete);
        diary_delete.setOnClickListener(this);
        diary_save = (ImageView)rootView.findViewById(R.id.IV_diary_page_save);
        diary_save.setOnClickListener(this);

        return rootView;
    }


    private void saveDiary(){


//        new SaveDiaryTask(
//                getActivity(),
//                calendar.getTimeInMillis(),
//                EDT_diary_title.getText().toString()
//                Check  attachment
//                diaryItemHelper,
//                getTopicId(),
//                this).execute(getTopicId());
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.IV_diary_page_delete:
//                if (diaryItemHelper.getItemSize())
                break;
            case R.id.IV_diary_page_save:
//                if (diaryItemHelper.getItemSize()>0){
//                    saveDiary();
//                    Toast.makeText(getActivity(),"Save successfully!!", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(getActivity(),"Diary is empty!", Toast.LENGTH_SHORT).show();
//                }
                break;
        }
    }
}

