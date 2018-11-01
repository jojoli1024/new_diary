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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jojo.diary.R;
// implements View.OnClickListener
public class DiaryFragment extends Fragment implements View.OnClickListener {

    private ImageView diary_page_delete;
    private ImageView diary_page_save;
    private ImageView diary_page_add_music;
    private RelativeLayout diary_page_add_diary;
    private EditText EDT_diary_title;
    private DiaryItemHelper diaryItemHelper;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.diary_page,container,false);

        diary_page_delete = (ImageView)rootView.findViewById(R.id.IV_diary_page_delete);
        diary_page_delete.setOnClickListener(this);

        diary_page_save = (ImageView)rootView.findViewById(R.id.IV_diary_page_save);
        diary_page_save.setOnClickListener(this);

        diary_page_add_music=(ImageView) rootView.findViewById(R.id.IV_diary_music);
        diary_page_add_music.setOnClickListener(this);

        diary_page_add_diary = (RelativeLayout) rootView.findViewById(R.id.RL_diary_info);
        diary_page_add_diary.setOnClickListener(this);

        return rootView;
    }


//    private void saveDiary(){
//        new SaveDiaryTask(
//                getActivity(),
//                calendar.getTimeInMillis(),
//                EDT_diary_title.getText().toString()
//                Check  attachment
//                diaryItemHelper,
//                getTopicId(),
//                this).execute(getTopicId());
//    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.IV_diary_page_delete:
                Toast.makeText(getActivity(),"delete successfully!!", Toast.LENGTH_SHORT).show();
//                if (diaryItemHelper.getItemSize())
                break;

            case R.id.IV_diary_page_save:
//                if (diaryItemHelper.getItemSize()>0){
////                    saveDiary();
//                    Toast.makeText(getActivity(),"Save successfully!!", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(getActivity(),"Diary is empty!", Toast.LENGTH_SHORT).show();
//                }
                Toast.makeText(getActivity(),"save successfully!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.IV_diary_music:
                Toast.makeText(getActivity(),"music successfully!!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.RL_diary_info:
                Toast.makeText(getActivity(),"calendar successfully!!", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}

