package com.jojo.diary.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jojo.diary.R;

import java.util.List;

class DynamicReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast toast = Toast.makeText(context,"动态广播："+intent.getStringExtra("sele"), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,0);
        toast.show();
    }
}

public class viewActivity extends AppCompatActivity implements View.OnClickListener{
    private Button add;
    private Button delete;
    private RecyclerView recyclerView;
    private recycleAdapter recycleAdapter;
    private List<diaryItem> diaryItemList;

    private DynamicReceiver dynamicRecevier;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_page);

        recyclerView = (RecyclerView)findViewById(R.id.RecyclerView_diary);
        recycleAdapter = new recycleAdapter(this,diaryItemList);

        add = (Button)findViewById(R.id.add);
        delete = (Button)findViewById(R.id.delete);
        add.setOnClickListener(this);
        delete.setOnClickListener(this);


        IntentFilter filter = new IntentFilter();
        filter.addAction("hijojo!");
        dynamicRecevier = new DynamicReceiver();
        registerReceiver(dynamicRecevier,filter);
    }
    static int index=0;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                recycleAdapter.add(index);
                index++;

                Intent intent = new Intent();
                intent.setAction("hijojo!");
                intent.putExtra("sele","add!");
                sendBroadcast(intent);
                break;
            case R.id.delete:
                recycleAdapter.add(index);
                index--;

                Intent intent1 = new Intent();
                intent1.setAction("hijojo!");
                intent1.putExtra("sele","delete!");
                sendBroadcast(intent1);
                break;
            default:
                break;
        }
    }
}
