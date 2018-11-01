package com.jojo.diary.memo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jojo.diary.R;

public class MemoFragment extends Fragment implements View.OnClickListener{
    private Button But_edit_memo_cancel,But_edit_memo_ok;
    private EditText EDT_edit_memo_content;
    private ImageView IV_diary_delete,IV_diary_save;
    private RelativeLayout memo_page_add_calendar;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.memo_page,container,false);
        But_edit_memo_cancel = (Button)rootView.findViewById(R.id.But_edit_memo_cancel);
        But_edit_memo_cancel.setOnClickListener(this);

        But_edit_memo_ok = (Button)rootView.findViewById(R.id.But_edit_memo_ok);
        But_edit_memo_cancel.setOnClickListener(this);

        EDT_edit_memo_content = (EditText)rootView.findViewById(R.id.EDT_edit_memo_content);
        EDT_edit_memo_content.setOnClickListener(this);

        IV_diary_delete = (ImageView)rootView.findViewById(R.id.IV_diary_delete);
        IV_diary_delete.setOnClickListener(this);

        IV_diary_save = (ImageView)rootView.findViewById(R.id.IV_diary_save);
        IV_diary_save.setOnClickListener(this);

        memo_page_add_calendar = (RelativeLayout)rootView.findViewById(R.id.RL_date_time_picker);
        memo_page_add_calendar.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.But_edit_memo_cancel:
//                But_edit_memo_cancel.setText("test");
                break;
            case R.id.But_edit_memo_ok:
                break;
            case R.id.EDT_edit_memo_content:
                break;
            case R.id.IV_diary_delete:
                break;
            case R.id.IV_diary_save:
                break;
            case R.id.RL_date_time_picker:
                Toast.makeText(getActivity(),"calendar successfully!!", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
