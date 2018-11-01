package com.jojo.diary.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jojo.diary.R;

public class dialog_diaryFragment extends DialogFragment implements View.OnClickListener {

    private ScrollView ScrollView_diary_content;
    private RelativeLayout RL_diary_info;
    private ProgressBar PB_diary_item_content_hint;
    private LinearLayout LL_diary_time_information;

    private TextView TV_diary_month, TV_diary_date, TV_diary_day, TV_diary_time;

    private TextView TV_diary_title_content;
    private EditText EDT_diary_title;

    private LinearLayout LL_diary_item_content;
    private ImageView IV_diary_close_dialog,IV_music_puase,IV_music_play;

    private long diaryId;

    public static dialog_diaryFragment newInstance(long diaryId,boolean isEditMode){
        Bundle args = new Bundle();
        dialog_diaryFragment fragment = new dialog_diaryFragment();
        args.putLong("diaryId",diaryId);
        args.putBoolean("isEditMode",isEditMode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        Dialog dialog = new Dialog(getActivity()){
//            //
//        };
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
//                .setTitle("提醒！")
//                .setView(initView())
//                .setPositiveButton(android.R.string.ok,this)
//                .setNegativeButton(android.R.string.cancel,this);
//        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        dialog.getWindow().getDecorView().getBackground().setAlpha(0);
//        return dialog;
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        this.getDialog().setCanceledOnTouchOutside(false);
        View rootView = inflater.inflate(R.layout.dialog_diary_page,container,false);
        ScrollView_diary_content = (ScrollView)rootView.findViewById(R.id.ScrollView_diary_content);

        RL_diary_info = (RelativeLayout) rootView.findViewById(R.id.RL_diary_info);

        LinearLayout LL_diary_edit_bar = (LinearLayout) rootView.findViewById(R.id.LL_diary_edit_bar);

        PB_diary_item_content_hint = (ProgressBar) rootView.findViewById(R.id.PB_diary_item_content_hint);


        TV_diary_month = (TextView) rootView.findViewById(R.id.TV_diary_month);
        TV_diary_date = (TextView) rootView.findViewById(R.id.TV_diary_date);
        TV_diary_day = (TextView) rootView.findViewById(R.id.TV_diary_day);
        TV_diary_time = (TextView) rootView.findViewById(R.id.TV_diary_time);

        LL_diary_item_content = (LinearLayout) rootView.findViewById(R.id.LL_diary_item_content);

        IV_diary_close_dialog = (ImageView) rootView.findViewById(R.id.IV_diary_close_dialog);
        IV_diary_close_dialog.setVisibility(View.VISIBLE);
        IV_diary_close_dialog.setOnClickListener(this);

        IV_music_play = (ImageView) rootView.findViewById(R.id.IV_music_play);
        IV_music_play.setOnClickListener(this);
        IV_music_puase = (ImageView) rootView.findViewById(R.id.IV_music_puase);
        IV_music_puase.setOnClickListener(this);

//        initView(rootView);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TV_diary_date.setText("11");
//        initData();
    }

    public void onStart() {
        super.onStart();
        //Modify dialog size
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.97), (int) (dm.heightPixels * 0.90));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.IV_music_play:
//                IV_music_play.setVisibility(View.INVISIBLE);
//                IV_music_puase.setVisibility(View.VISIBLE);
                break;
            case R.id.IV_music_puase:
//                IV_music_puase.setVisibility(View.INVISIBLE);
//                IV_music_play.setVisibility(View.INVISIBLE);
                break;
            case R.id.IV_diary_close_dialog:
                dismiss();
                break;
            default:
                break;
        }
    }
}
