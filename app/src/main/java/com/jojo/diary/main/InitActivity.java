package com.jojo.diary.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.jojo.diary.R;

public class InitActivity extends Activity {

    // 闪屏时间
    private static int WAITING_TIME = 2000;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_init);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(InitActivity.this, MainActivity.class);
                startActivity(i);

                finish();
            }
        },WAITING_TIME);
    }
}