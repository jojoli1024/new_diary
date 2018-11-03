package com.jojo.diary.view;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
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
import android.widget.TextView;
import android.widget.Toast;

import com.jojo.diary.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

    private MediaPlayer myPlayer;

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
//        myPlayer= new MediaPlayer();
        //权限判断，如果没有权限就请求权限
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initMediaPlayer();//初始化播放器 MediaPlayer
        }
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
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

    public void initMediaPlayer(){
//        try{
//            myPlayer = new MediaPlayer();
//            Uri uri = Uri.parse("http://music.163.com/song/media/outer/url?id=19067286.mp3");
//            myPlayer.setDataSource(getActivity(), uri);
//            myPlayer.prepareAsync();
//            myPlayer.setLooping(true);
//            myPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                public void onPrepared(MediaPlayer player) {
//                    player.start();
//                }
//            });
//
//            myPlayer.start();
//
//        } catch (Exception e){
//            Toast.makeText(getActivity(),"url : player实例化失败！", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//
//        ContentResolver contentResolver = getActivity().getContentResolver();
//        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
//
//        List<String> myMusicData = new ArrayList<String>();
//        while (cursor.moveToNext()){
//            //拿到系统音乐的歌名，歌手名，路径
////            String title = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE));
////            String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
//            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
//
//            myMusicData.add(path);
//        }

       try{
//           myPlayer.create(getActivity(),R.raw.jentlemo);
           //resid的大小是小于1M的
           myPlayer = new MediaPlayer();
//           Uri uri = Uri.parse("android.rescore://com.android.jojo/"+R.raw.jentlemo);
//           Uri uri = Uri.parse(myMusicData.get(0));
//           myPlayer.setDataSource(getActivity(),uri);
           myPlayer.setDataSource("/sdcard/download/jentlemo.mp3");
           myPlayer.prepare();
           myPlayer.setLooping(true);
           myPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
               public void onPrepared(MediaPlayer player) {
                   player.start();
               }
           });
           myPlayer.start();

//           if (myPlayer != null){
//               myPlayer.start();
//           }

           Toast.makeText(getActivity(),"R.raw : player 成功？", Toast.LENGTH_SHORT).show();
       } catch (NullPointerException e){
           Toast.makeText(getActivity(),"R.raw : player 为空！", Toast.LENGTH_SHORT).show();
           e.printStackTrace();
       }
       catch (IOException e){
           Toast.makeText(getActivity(),"R.raw : player prepare问题！", Toast.LENGTH_SHORT).show();
           e.printStackTrace();
       }

    }

    public void onDestroy() {
        super.onDestroy();
        if(myPlayer != null){
//            myPlayer.stop();
            myPlayer.release();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.IV_music_play:
//                IV_music_play.setVisibility(View.INVISIBLE);
//                IV_music_puase.setVisibility(View.VISIBLE);
//                Toast.makeText(getActivity(),"你点了music_play", Toast.LENGTH_SHORT).show();

//                myPlayer.start();

                if(myPlayer!=null && !myPlayer.isPlaying()){
                    try {
                        myPlayer.prepare();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(),"myPlayer doesn't prepare !", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    myPlayer.start();
                    Toast.makeText(getActivity(),"myPlayer start!", Toast.LENGTH_SHORT).show();
                }
                else if (myPlayer == null){
                    Toast.makeText(getActivity(),"myPlayer = null !", Toast.LENGTH_SHORT).show();
                }
                else if(myPlayer.isPlaying()){
                    Toast.makeText(getActivity(),"myPlayer is playing !", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.IV_music_puase:
//                IV_music_puase.setVisibility(View.INVISIBLE);
//                IV_music_play.setVisibility(View.INVISIBLE);
//                Toast.makeText(getActivity(),"你点了music_puase", Toast.LENGTH_SHORT).show();

//                myPlayer.pause();

                if(myPlayer!=null && myPlayer.isPlaying()){
                    myPlayer.pause();
                    Toast.makeText(getActivity(),"myPlayer pause!", Toast.LENGTH_SHORT).show();
                }
                else if (myPlayer == null){
                    Toast.makeText(getActivity(),"myPlayer = null !", Toast.LENGTH_SHORT).show();
                }
                else if(!myPlayer.isPlaying()){
                    Toast.makeText(getActivity(),"myPlayer is not playing !", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.IV_diary_close_dialog:
                onDestroy();
                dismiss();
                break;
            default:
                break;
        }
    }
}
