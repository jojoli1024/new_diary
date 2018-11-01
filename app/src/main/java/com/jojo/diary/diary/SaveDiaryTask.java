package com.jojo.diary.diary;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.service.autofill.SaveCallback;

public class SaveDiaryTask extends AsyncTask<Long, Void, Integer> {

    public interface SaveDiaryCallBack{
        void onDiarySaved();
    }

    public final static String TAG = "SaveDiaryTask";
    public final static int RESULT_INSERT_SUCCESSFUL = 1;
    public final static int RESULT_INSERT_ERROR = 2;

    private Context mContext;
    private long time;
    private String title;
    private boolean attachment;
    private DiaryItemHelper diaryItemHelper;
    private SaveDiaryCallBack callBack;

    private ProgressDialog progressDialog;

    public SaveDiaryTask(Context context,
                         long time,
                         String title,
                         boolean attachment,
                         DiaryItemHelper diaryItemHelper,
                         long topicId,
                         SaveDiaryCallBack callBack){

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Saving");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar);

        this.mContext=context;
        this.title=title;
        this.attachment = attachment;
        this.diaryItemHelper = diaryItemHelper;
        this.callBack = callBack;

        progressDialog.show();
    }

    @Override
    protected Integer doInBackground(Long...params){
        int saveResult = RESULT_INSERT_SUCCESSFUL;
        long topicId = params[0];
        try{

        }catch(Exception e){

        }finally {

        }
        return saveResult;

    }


}
